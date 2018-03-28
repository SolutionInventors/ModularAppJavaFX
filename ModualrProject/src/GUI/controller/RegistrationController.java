package GUI.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import GUI.utilities.EduBackTableGUI;
import database.bean.student.Biodata;
import database.bean.student.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import test.TestUtils;
import utils.ValidationType;

public class RegistrationController
{
    @FXML private TextField txttitle;
    @FXML private TextField txtbirthplace;
    @FXML private TextField txtcurAdd;
    @FXML private TextField txtemail;
    @FXML private TextField txtLname;
    @FXML private TextField txtFname;
    @FXML private TextField txtcountry;
    @FXML private TextField txtstate;
    @FXML private TextField txtPerAdd;
    @FXML private TextField txttel;
    @FXML private TextField txtstudentID;
    @FXML private TextField txtreligion; 
    @FXML private TextField txtclass;
    
    @FXML private List<TextField> personalDetail;
    @FXML private List<TextField> eduBack;
    @FXML private List<Tab> tabsArray;
    
    @FXML private ComboBox<String> cbGender;
    @FXML private DatePicker selectDate;
    @FXML private DatePicker dpBeginDate;
    @FXML private DatePicker dpEndDate;
    
    
    @FXML private Button btnCreateStd;
    @FXML private ImageView img;
    
    @FXML private TabPane tabPane;
    @FXML private Tab tabPersonalDetail;
    @FXML private Tab tabedu;
    @FXML private Tab tabexp;
    @FXML private Tab tabHowYouHeard;
  
    @FXML private TableView<EduBackTableGUI> eduBackTable;
    @FXML private TableColumn<EduBackTableGUI, String>name;
    @FXML private TableColumn<EduBackTableGUI, String>startDate;
    @FXML private TableColumn<EduBackTableGUI, String>endDate;
    @FXML private TableColumn<EduBackTableGUI, String>course;
    @FXML private TableColumn<EduBackTableGUI, String>certification;
    
    
    private int tabCurrentIndex = 0;
    private boolean istxtfieldEmpty = false;
    private File imageFile;
    private ObservableList<EduBackTableGUI> list = FXCollections.observableArrayList();
    
    public void initialize() {
	//for modules table per student
	name.setCellValueFactory(new PropertyValueFactory<EduBackTableGUI, String>("name"));
	startDate.setCellValueFactory(new PropertyValueFactory<EduBackTableGUI, String>("startDate"));
	endDate.setCellValueFactory(new PropertyValueFactory<EduBackTableGUI, String>("endDate"));
	course.setCellValueFactory(new PropertyValueFactory<EduBackTableGUI, String>("course"));
	certification.setCellValueFactory(new PropertyValueFactory<EduBackTableGUI, String>("certification"));
	    
		ObservableList<String> filters = FXCollections.observableArrayList("Male","Female");
		cbGender.setItems(filters);
		cbGender.setValue("Male");
	
	
	
	
	
	for (Tab tab : tabsArray)
	{
	    tab.disableProperty().set(true);
	}
	tabPersonalDetail.disableProperty().set(false);
    }
    @FXML public void nextTab(ActionEvent event) {
	switch (tabPane.getSelectionModel().getSelectedIndex()) {
	    case 0:
		for (int i = 0; i < personalDetail.size(); i++){
		    if (personalDetail.get(i).getText().isEmpty()){
			istxtfieldEmpty=true;
			System.out.println("No of the empty txt is " + i);
			System.out.println("Name is "+personalDetail.get(i).getId());
			break;
		    }else {
			istxtfieldEmpty=false;
		    }
		}
		break;
		
	    case 1:
		for (int i = 0; i < 3; i++){
		    if (eduBack.get(i).getText().isEmpty()){
			istxtfieldEmpty=true;
			System.out.println("No of the empty txt is " + i);
			System.out.println("Name is "+eduBack.get(i).getId());
			break;
		    }else {
			istxtfieldEmpty=false;
		    }
		}
		break;
		

	    case 2:
		
		break;
		

	    case 3:
		
		break;
		
		
	}
	
	if (istxtfieldEmpty){
	    System.out.println(istxtfieldEmpty + " I am txt");
	    System.out.println("A filed is empty");
	}
	else{
	    tabsArray.get(tabCurrentIndex).disableProperty().set(true);
	    tabPane.getSelectionModel().select(++tabCurrentIndex);
	    tabsArray.get(tabCurrentIndex).disableProperty().set(false);
	}
	
/*	tabPane.getSelectionModel().selectedItemProperty()
	.addListener((ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) -> {

	    if (newValue == tabPersonalDetail){
		  }
	    else if (newValue == tabedu)   {
		tabPane.getSelectionModel().select(0);
		
	    }
	});*/
    }//end nextTab
    
    @FXML
    public void addtoTable(ActionEvent event) {
	EduBackTableGUI institution = new EduBackTableGUI();
	institution.setName(eduBack.get(3).getText());
	institution.setStartDate(dpBeginDate.getValue().toString());
	institution.setEndDate(dpEndDate.getValue().toString());
	institution.setCourse(eduBack.get(4).getText());
	institution.setCertification(eduBack.get(5).getText());
	for (int i = 3; i < eduBack.size(); i++){
	    eduBack.get(i).clear();
	}
	dpBeginDate.setValue(null);
	dpEndDate.setValue(null);
	
	eduBackTable.getItems().add(institution);
    }
    
    @FXML
    public void removefromTable(ActionEvent event) {
	  ObservableList<EduBackTableGUI> productSelected, allProducts;
	        allProducts = eduBackTable.getItems();
	        productSelected = eduBackTable.getSelectionModel().getSelectedItems();

	        productSelected.forEach(allProducts::remove);
    }
    
    @FXML
    public void previousTab(ActionEvent event) {
	 tabsArray.get(tabCurrentIndex).disableProperty().set(true);
	    tabPane.getSelectionModel().select(--tabCurrentIndex);
	    tabsArray.get(tabCurrentIndex).disableProperty().set(false);
    }
    

    // Event Listener on Button[#btnCreateStd].onAction
    @FXML
    public void createStudent(ActionEvent event) {
	final String studentId = txtstudentID.getText();
	String mail =  txtemail.getText();
	String className = txtclass.getText();
	
	//Date df = new Date

	String firstName = txtFname.getText();
	String lName= txtLname.getText();
	
	Student student = new Student(firstName, lName, studentId, className, mail, imageFile);
	
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
	imageFile = fc.showOpenDialog(null);

	if (imageFile != null)
	{
	    try
	    {
		String localUrl = imageFile.toURI().toURL().toString();
		Image localImage = new Image(localUrl, false);
		img.setImage(localImage);
	    }
	    catch (MalformedURLException e1)
	    {
		e1.printStackTrace();

	    }
	}
    }//end change image
}
