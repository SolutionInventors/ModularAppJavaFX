package GUI.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;

import database.bean.Admin;
import database.bean.student.Biodata;
import database.bean.student.Student;
import database.managers.DatabaseManager;
import exception.InvalidAdminException;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import test.TestUtils;
import utils.ValidationType;
import javafx.scene.control.DatePicker;

public class RegistrationController
{
    @FXML
    private TextField txttitle;
    @FXML
    private TextField txtbirthplace;
    @FXML
    private TextField txtcurAdd;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtLname;
    @FXML
    private TextField txtFname;
    @FXML
    private TextField txtcountry;
    @FXML
    private TextField txtstate;
    @FXML
    private TextField txtPerAdd;
    @FXML
    private TextField txttel;
    @FXML private TextField txtstudentID;
    @FXML
    private DatePicker selectDate;
    @FXML
    private TextField txtreligion; 
    @FXML private TextField txtclass;
    @FXML private Button btnCreateStd;
    @FXML private ImageView img;
    
    File file1;

    // Event Listener on Button[#btnCreateStd].onAction
    @FXML
    public void createStudent(ActionEvent event) {
	final String studentId = txtstudentID.getText();
	String mail =  txtemail.getText();
	String className = txtclass.getText();
	

	String firstName = txtFname.getText();
	String lName= txtLname.getText();
	
	Student student = new Student(firstName, lName, studentId, className, mail, file1);
	
	if( student.isValid(ValidationType.NEW_BEAN)) 
	    System.out.println("The Student object is valid" );
	else{
	    System.err.println("The Student is not valid");
	    System.exit(0);
	}

	System.out.println("\n----Creating Biodata object------");
	String state = txtstate.getText();
	String country = txtcountry.getText();
	String currAddr = txtcurAdd.getText();
	String permAddr = txtPerAdd.getText();
	String gender = "M";
	String birthString = TestUtils.getStringInput("Input birth date in the format(dd-mm-yyyy): ");
	//selectDate.getValue().format(formatter)
	String birthPlace = txtbirthplace.getText();
	String religion = txtreligion.getText();
	String title = txtreligion.getText();
	DateFormat df =  new SimpleDateFormat( "dd-mm-yyyy" );
	Date birth = null;
	try{
	    birth = new Date( df.parse( birthString).getTime());
	}
	catch (ParseException e){
	    e.printStackTrace();
	}

	Biodata bio = new Biodata(studentId, state, country, currAddr,
		permAddr, gender, birth, birthPlace, religion, title);
    
	if( bio.isValid(ValidationType.NEW_BEAN)) 	
	    System.out.println("The bio data is valid" );
	else{
	    System.err.println("The bio data object is invalid");
	    System.exit(0);
	}
	
	
	
    }//end method add student

    // Event Listener on ImageView[#img].onMouseClicked
    @FXML
    public void changeImage(MouseEvent event)
    {

	FileChooser fc = new FileChooser();
	FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg",
		"*.png", "*.bmp", "*.gif");

	fc.setInitialDirectory(
		new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Pictures"));
	fc.getExtensionFilters().add(imageFilter);
	file1 = fc.showOpenDialog(null);

	if (file1 != null)
	{
	    try
	    {
		String localUrl = file1.toURI().toURL().toString();
		Image localImage = new Image(localUrl, false);
		img.setImage(localImage);
	    }
	    catch (MalformedURLException e1)
	    {
		e1.printStackTrace();

	    }
	}
    }
}
