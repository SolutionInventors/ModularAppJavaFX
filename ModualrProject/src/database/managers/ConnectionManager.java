
package database.managers;

import java.security.Security;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.net.ssl.internal.ssl.Provider;

import database.bean.student.Student;

/**
 * This class contains static methods that would be used to establish 
 * a connection to the MySql database. <br>
 * It uses the 
 * 
 * @author Oguejiofor Chidiebere
 *
 */
public final class ConnectionManager
{
    private static final String MYSQLURL = "jdbc:mysql://localhost/modularappdatabase";
    private static final String USERNAME = "iitModularAppAdmin";
    private static final String PASSWORD = "O97G9JN>G=F6O?DHLM86";

    private static  Date currentDate = null ;
    private static ConnectionManager instance = null ;
    private ConnectionManager(){}

    private static Connection conn = null;

    /**
     * Gets the only instance of the {@code ConnectionManager} or creates
     * it if none exists.
     * @return
     */
    public static ConnectionManager getInstance() {
	if ( instance == null )
	{
	    instance =  new ConnectionManager();
	}

	return instance;
    }


    /**
     * This method can be used to send a mail to a specified email address.
     * If an error occurs, this method returns {@code false}.
     * @param recipient the recipient that the email is being sent to
     * @param body the subject of the mail
     * @param subject the title of the email.
     * @return {@code true } if the mail was sent successfully else false
     */
    public static boolean sendMail( String[] recipient, String body, String subject){
	String host="smtp.gmail.com";  
	final String user="iitmodular@gmail.com"; 
	final String password="rN'QD'uduopowT'8W?R7";  

	boolean sessionDebug = false;

	try {

	    //Get the session object  
	    Properties props = new Properties();
	    props.put( "mail.smtp.starttls.enable", "true");

	    props.put("mail.smtp.host",host);  
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");  
	    props.put( "mail.smtp.starttls.required", "true");
	    Security.addProvider(new Provider() );

	    Session mailSession = Session.getDefaultInstance(props, null ); 
	    mailSession.setDebug(sessionDebug);

	    //Compose the message  
	    List<InternetAddress>  addresses =
		    Arrays.stream(recipient)
		    .map(elem -> {
			try
			{
			    return new InternetAddress(elem);
			}
			catch (AddressException e)
			{
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
			return null;
		    })
		    .filter(elem-> elem != null )
		    .collect(Collectors.toList()); 

	    InternetAddress[] addrArray = 
		    addresses.toArray(new InternetAddress[addresses.size()]);

	    MimeMessage message = new MimeMessage(mailSession);  
	    message.setFrom(new InternetAddress(user));  

	    message.addRecipients(Message.RecipientType.TO,addrArray);  
	    message.setSubject(subject);  
	    message.setSentDate( new  java.util.Date());
	    message.setText( body);  

	    //send the message  
	    Transport transport = mailSession.getTransport("smtp");
	    transport.connect(host, user, password);

	    transport.sendMessage(message, message.getAllRecipients());

	    transport.close();

	} 
	catch (MessagingException e) { 
	    e.printStackTrace();
	    return false;
	}  
	return true;

    }
    /**
     * This creates a connection between the java application and the database. It
     * also sets the static currentDate attribute of the {@code DatabaseManager} to
     * the date in the database. Returns {@code true } if the connection 
     * was established.
     * @return
     */
    protected static boolean openConnection()
    {
	try
	{
	    conn = DriverManager.getConnection(MYSQLURL, USERNAME, PASSWORD );

	    ResultSet result = null;
	    try( Statement stmt = conn.createStatement();)
	    {
		result = stmt.executeQuery("SELECT NOW();");
		if( result.next()){
		    currentDate =  result.getDate(1);
		    initialiseStudentFile(); 
		    System.out.println( "Connected" );
		    return true;
		}
	    }
	    finally{
		if( result!= null ) result.close();
	    }
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
	return false;
    }



    private static void initialiseStudentFile()
    {
	String sql = "SELECT DefaultImage FROM resources "; 

	ResultSet result = null;
	try(PreparedStatement stmt = DatabaseManager.getPreparedStatement(sql);){
	    result = stmt.executeQuery(); 
	    if(result.next()){
		Student.setDefaultImage(result.getBinaryStream(1));
	    }
	   
	}
	catch (SQLException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}finally{
	    if( result!=null)
		try
		{
		    result.close();
		}
		catch (SQLException e)
		{
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}



    }


    /**THis gets the only {@code Connection } object in this project*/
    public Connection getConnection() 
    {
	if( conn == null ){
	    if( openConnection() )
		return conn;
	    return null;
	}

	return conn;
    }

    /**
     * Returns a java.sql.Date object that contains the current date in the database 
     * @return {@code java.sql.Date} object that contains the current date or {@code null } if
     * an error occured while processing retrieving the information.
     */
    public static Date getCurrentDate() {
	if( currentDate  == null && conn == null ){
	    openConnection();
	    close();
	}
	return currentDate;
    }
    /** This closes the Connection object and sets the static currentDate attribute
     * of the DatabaseManager class to null
     * 
     */
    public static void close() 
    {
	try
	{
	    if( conn != null )
		conn.close();
	    System.out.println("Disconnected!!");
	    conn = null;
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}

    }
}
