package database.bean;

import java.io.File;
import java.sql.Date;

import database.bean.student.Student;
import utils.ValidationType;

/**
 * Objects of this {@code ModuleRegister} represent a single row of the 
 * {@code ModuleRegister } table in the database. The {@code ModuleRegister} table
 * is used to keep track of the modules that a {@code Student} has registered for.
 * It contains informations like the module that was  registered for, the student's Id ,
 * the result of the module  if the module is complete, 
 * the booking status, the date the module was registered and so on.<br>
 * This id of a {@code ModuleRegister} is used as a foreign key in the {@link Payment}  
 * table to store the payment data of the {@code ModuleRegister}<br>
 *  Although this object contains a paymentStatus that is {@code true} if
 * the payment is complete, the {@link Payment} object contains information
 * of the payment that has been made for this object
 *
 * This class contains three constructors
 * 
 * @author Oguejiofor Chidiebere
 *@see ModuleTabTable 
 *@see Payment 
 */

public class ModuleRegister  implements Bean
{

    private static final long serialVersionUID = 8474237887023643531L;

    /**This is an auto-incrementing primary key of this object*/
    private int id;

    /**Foreign key from the ModuleTabTable table*/
    private String moduleName;
    
    /**Foreign key from the Student table*/
    private String studentId;

    private File studentImage;//stored in Student table but used here
    
    private boolean paymentStatus;
    private boolean bookingStatus;
    private boolean attended;
    private String result;

    private double totalPriceForModule;
    private Date dateRegistered;

    public ModuleRegister(){}

    /**
     * Initializes this object with the moduleId and studentId. {@code ModuleRegister}s 
     * created with this
     * constructor can be added to the database. 
     * @param moduleId the foreign key from the {@code ModuleTabTable } table
     * @param studentId the foreing key from the {@code Student} table
     */
    public ModuleRegister(  String moduleId, String studentId){
	setModuleName(moduleId);
	setStudentId(studentId);

    }

    /**
     * 
     * @param moduleName
     * @param studentId
       @param booked
     * @param attended
     * @param result
     */
    public ModuleRegister(File image,  String moduleName , String studentId,
	    boolean booked, boolean attended, double totalPrice, String result )
    {
	this(  moduleName, studentId);
	setStudentImage(image);
	setBookingStatus(booked);
	setAttended(attended);
	setResult(result);
	setTotalPriceForModule(totalPrice);
    }
    /**
     * Gets the module name of stored in this object
     * @return a {@code String } containing the module name of this object
     */
    public String getModuleName()
    {
	return moduleName;
    }
    /**
     * Sets the module name of this object. It does this by removinging the empty space
     * in its argument and then capitalizing the first letter of the words in the 
     * resulting {@code String}
     * @param moduleName the {@code String} to be used to set the module name
     */
    public void setModuleName(String moduleName)
    {
	this.moduleName = Bean.capitalizeWords( moduleName);
    }

    /**
     *Gets the Student  id_card_number stored in this object as a {@code String}
     *@return a {@code String} containing student id 
     */
    public String getStudentId()
    {
	return studentId;
    }

    /**
     * Sets this object's studentId by removing any unwanted space its argument
     * @param moduleId
     */
    public void setStudentId(String studentId)
    {
	this.studentId = Bean.removeExtraSpaces( studentId.toUpperCase());
    }

    /**
     *Checks if this {@code ModuleRegister } has been paid for. This information
     *must be retrieved from the database before you can confirm its accuracy .
     *You can retrieve a {@code ModuleRegister} via call to 
     *{@link database.managers.ModuleRegisterManager#getModRegById(int) } 
     * @return true when module has been paid for
     */
    public boolean paymentComplete()
    {
	return paymentStatus;
    }

    /**
     * Sets the payment status of this ModuleRegister. This should only be used 
     * by the ModuleTabTable
     * @param paymentStatus
     */
    public void setPaymentStatus(boolean paymentStatus)
    {
	this.paymentStatus = paymentStatus;
    }

    /**
     * Returns true when the student has booked for the {@code ModuleTabTable} representend
     * by this object's moduleId
     * @return boolean
     */
    public boolean hasBooked()
    {
	return bookingStatus;
    }

    /** Sets the booking status of this object.
     */
    public void setBookingStatus(boolean bookingStatus)
    {
	this.bookingStatus = bookingStatus ;
    }

    /**Returns true when the student( with this object's studentId )
     * has paid for the module represented
     * by this object's moduleId
     * @return
     */
    public boolean hasAttended()
    {
	return attended;
    }

    /** Sets the attended attribute of this object. Note that
     * The attended would be set to {@code false } if the attended bookingStatus is {@code false}
     * 
     */
    public void setAttended(boolean attended)
    {
	this.attended = attended ;
    }

    /**
     * Returns either PASS or FAIL indicates that the course was passed or failed
     * @return String
     */
    public String getResult()
    {

	return result == null ? "NO RESULT" : result;
    }

    /**
     * Sets the result of this ModuleRegister 
     * the result must be either "PASS" or "FAIL". <br>
     * If a wrong value is passed, does nothingS
     * @param result
     */
    public void setResult(String result) 
    {
	if( result != null && ( result.toLowerCase().equals("pass" ) || 
		result.toLowerCase().equals("fail") ))
	{
	    this.result = result.toUpperCase();
	}
	
    }

    /**
     * Gets the id of this object. This value is used in the {@code Payment} table. 
     * The return value is the primary key of this table
     * @return an {@code int} representing the id of this object. 
     */ 
    public int getId()
    {
	return id;
    }

    /**
     * Sets the id attribute of this {@code ModuleRegister}. Note that this is 
     * auto-generated and thus should be retrieved from the database
     * @param id
     */
    public void setId(int id)
    {
	this.id = id;
    }

    /**
     * Gets the date the student was registered for the module contained in this
     * {@code ModuleRegiser}
     * @return
     */
    public Date getDateRegistered()
    {
	return dateRegistered;
    }

    /**
     * Sets the date that the registration was made. This retrieved only from the
     * database managers. <br>
     * Note that when transactions are performed, this information is updated
     * @param dateRegistered
     */
    public void setDateRegistered(Date dateRegistered)
    {
	this.dateRegistered = dateRegistered;
    }

    /**
     * Validates the format of the StudentId and module name contained in
     * this {@code ModuleRegister}. It does this by checking that the studentId
     * and moduleName {@code String} length is greater than zero 
     * @return {@code true} if the validation was successful 
     */
    public  boolean isValid(ValidationType type)
    {
	return validateStudentId() && validateModuleName();
    }

    /**
     * Checks that the module name is valid by ensuring that it contains only alphabets
     * Note that an empty {@code String} is considered invalid
     * @return
     */
    public boolean validateModuleName()
    {
	String name = getModuleName();
	return name != null && Bean.containsEitherAlphaNum(name) && 
		name.substring(0,1).matches("[A-Za-z]");
    }

    /**
     * Checks that the studentId is valid vial call to 
     * {@link database.bean.student.Student#validateStudentID(String)}
     */
    public boolean validateStudentId()
    {
	return Student.validateStudentID(getStudentId());
    }

    public double getTotalPriceForModule()
    {
        return totalPriceForModule;
    }

    public void setTotalPriceForModule(double totalPriceForModule)
    {
        this.totalPriceForModule = totalPriceForModule;
    }

    public File getStudentImage()
    {
	return studentImage;
    }

    public void setStudentImage(File studentImage)
    {
	this.studentImage = studentImage;
    }

}
