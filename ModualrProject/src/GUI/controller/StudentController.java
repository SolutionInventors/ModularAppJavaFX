package GUI.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import GUI.utilities.ModuleTableGUI;
import GUI.utilities.Paths;
import GUI.utilities.StudentTableGUI;
import database.bean.Admin;
import database.bean.student.Student;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import database.managers.StudentManager;
import database.statistics.StatisticsManager;
import database.statistics.StudentModuleStats;
import database.statistics.StudentStats;
import exception.InvalidAdminException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StudentController implements Initializable
{
    
    @FXML private TextField txtSearchBar;
    @FXML private Button btnUpdate;
    @FXML private Button btnAddStudent;
    
    @FXML private ComboBox<String> cmbCategory;
    @FXML private Label lblStudentName;
    @FXML private Label lblisActive;
    @FXML private Label lbldateRegistered;
    @FXML private Label lblcertissused;
    @FXML private Label lblemail;
    @FXML private ImageView imgStudentImage;
    
    @FXML PieChart pieChart;

    @FXML private TableView<StudentTableGUI>studentTable;
    @FXML private TableColumn<StudentTableGUI, String>id;
    @FXML private TableColumn<StudentTableGUI, String>firstName;
    @FXML private TableColumn<StudentTableGUI, String>lastName;
    @FXML private TableColumn<StudentTableGUI, String>className;
    
    @FXML private TableView<ModuleTableGUI> moduleTable;
    @FXML private TableColumn<ModuleTableGUI, String>module;
    @FXML private TableColumn<ModuleTableGUI, String>paid;
    @FXML private TableColumn<ModuleTableGUI, String>booked;
    @FXML private TableColumn<ModuleTableGUI, String>result;
    

    // for class manipulation
    private Student[] students;
    StudentStats studStats;
    ObservableList<StudentTableGUI> studentList = FXCollections.observableArrayList();



    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
	//for student table
	id.setCellValueFactory(new PropertyValueFactory<StudentTableGUI, String>("id"));
	firstName.setCellValueFactory(new PropertyValueFactory<StudentTableGUI, String>("firstName"));
	lastName.setCellValueFactory(new PropertyValueFactory<StudentTableGUI, String>("lastName"));
	className.setCellValueFactory(new PropertyValueFactory<StudentTableGUI, String>("className"));
	studentTable.setItems(getstudents());

	//for modules table per student
	module.setCellValueFactory(new PropertyValueFactory<ModuleTableGUI, String>("moduleName"));
	paid.setCellValueFactory(new PropertyValueFactory<ModuleTableGUI, String>("paid"));
	booked.setCellValueFactory(new PropertyValueFactory<ModuleTableGUI, String>("booked"));
	result.setCellValueFactory(new PropertyValueFactory<ModuleTableGUI, String>("result"));
    
	ObservableList<String> filters = FXCollections.observableArrayList("Active","Inactive","All");
	cmbCategory.setItems(filters);
	cmbCategory.setValue("All");
	
	FilteredList<StudentTableGUI> filteredData = new FilteredList<>(studentList,e->true);
	txtSearchBar.setOnKeyReleased(e ->{
	    txtSearchBar.textProperty().addListener((observableValue, oldValue, newValue) ->{
		filteredData.setPredicate((Predicate<? super StudentTableGUI>)studentList ->{
		    if(newValue == null || newValue.isEmpty()) {
			return true;
		    }
		    String lowerCaseFilter = newValue.toLowerCase();
		    if (studentList.getId().toLowerCase().contains(newValue))
		    {
			return true;
		    }else if(studentList.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
			return true;
		    }else if(studentList.getLastName().toLowerCase().contains(lowerCaseFilter)) {
			return true;
		    }
		    return false;
		   
		});
		
	    });
	    SortedList<StudentTableGUI> sortedData = new SortedList<>(filteredData);
	    sortedData.comparatorProperty().bind(studentTable.comparatorProperty());
	    studentTable.setItems(sortedData);
	});
    }
    @FXML
    private void update(ActionEvent event) {
	
    }
    @FXML
    private void regStudent(ActionEvent event) throws IOException {
	Stage regInfo = new Stage();
	regInfo.initModality(Modality.APPLICATION_MODAL);
	Parent root = FXMLLoader.load(getClass().getResource(Paths.viewpath + "Registration.fxml"));
	Scene scene = new Scene(root);
	regInfo.setResizable(false);
	regInfo.setScene(scene);
	regInfo.sizeToScene();
	regInfo.setTitle("Student Info");
	regInfo.show();
    }
    
    
    public void getuser() 
    {
	int selection = studentTable.getSelectionModel().getSelectedIndex();
	lblisActive.setText(students[selection].isActive() ? "Yes" : "No");
	lbldateRegistered.setText(String.valueOf(students[selection].getDateAdmitted()));
	lblcertissused.setText(students[selection].getCertificateIssued());
	lblemail.setText(students[selection].getEmailAddress());

	String localUrl = null;
	try
	{
	    localUrl = students[selection].getImage().toURI().toURL().toString();
	    
	}
	catch (MalformedURLException e)
	{
	    e.printStackTrace();
	}
	Image localImage1 = new Image(localUrl, false);
	imgStudentImage.setImage(localImage1);
	
	moduleTable.setItems(getModuleforStudent(students[selection].getIdCardNumber()));
	createChart(students[selection].getIdCardNumber());

    }// end method get user
    
    
    private ObservableList<StudentTableGUI> getstudents(){
	// Step 1 :Create current Admin
	//should be removed later on after login screen has been created
	Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat");
	DatabaseManager.setCurrentAdmin(currentAdmin);

	// Step 2: call getStudents method
	
	try{
	    students = StudentManager.getStudents(0);

	    for (int i = 0; i < students.length; i++)
	    {
		studentList.add(new StudentTableGUI(students[i].getIdCardNumber(), students[i].getFirstName(),
			students[i].getLastName(), students[i].getModClassName()));

	    }
	}
	catch (SQLException e){
	    e.printStackTrace();
	}
	finally{
	    // very important! Close the connection when the user closes the app
	    ConnectionManager.close();
	}return studentList;

    }// end method get student
    
    private ObservableList<ModuleTableGUI> getModuleforStudent(String id){
	StudentModuleStats[]  studMods = null ; 
	 Student student = new Student(id);
	 try
	{
	    studMods = StatisticsManager.retrieveStudentModuleStats(
	    	    student.getIdCardNumber());
	    /*System.out.println("success");
	    System.out.println(student.getIdCardNumber());
	    System.out.println(studMods);*/
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	} 
	 
	 ObservableList<ModuleTableGUI> moduleList = FXCollections.observableArrayList();
	if (studMods != null) {
	  for (int i = 0; i < studMods.length; i++) {
	      moduleList.add(new ModuleTableGUI(studMods[i].getModuleName(), String.valueOf(studMods[i].getAmountPaid()), String.valueOf(studMods[i].hasBooked()), studMods[i].getResult()));
	  }}
		
	return moduleList;
	
    }

    private void createChart(String id) {
	    StudentStats studStats = null; 
	    Student student = new Student(id);
	    try
	    {
		studStats = StatisticsManager.retrieveStats(student);
	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
	    }
		//import piechart data
		ObservableList<Data> list = FXCollections.observableArrayList(
				new PieChart.Data("Modules Registered", studStats.getModuleRegistered()),
				new PieChart.Data("Modules Paid", studStats.getModulePaid()),
				new PieChart.Data("Modules Booked", studStats.getModuleBooked()),
				new PieChart.Data("Modules Attended", studStats.getModuleAttended()),
				new PieChart.Data("Modules Passed", studStats.getModulePassed()),
				new PieChart.Data("Modules Failed:", studStats.getModuleFailed())
				);
		pieChart.setData(list);
	}//end method create chart

    /**
     * Updates the table based on value selected
     */
    @FXML
    private void combochange() {
	try{
	switch (cmbCategory.getSelectionModel().getSelectedIndex())
	{
	    case 0:
		students= StudentManager.getStudents(true, 0);
		break;
	    case 1:
		students= StudentManager.getStudents(false, 0);
		break;
	    default:
		//students = StudentManager.getStudents(isActive, 0);
		break;
	}
	studentList.clear();
	 for (int i = 0; i < students.length; i++)
	    {
		studentList.add(new StudentTableGUI(students[i].getIdCardNumber(), students[i].getFirstName(),
			students[i].getLastName(), students[i].getModClassName()));

	    }
	 studentTable.setItems(studentList);
	 
	}
	catch (SQLException | InvalidAdminException e){
	    e.printStackTrace();
	}
    }
}//end class
