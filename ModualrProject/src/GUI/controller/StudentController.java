package GUI.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import GUI.utilities.ModuleTableGUI;
import GUI.utilities.StudentTableGUI;
import database.bean.Admin;
import database.bean.student.Student;
import database.managers.ConnectionManager;
import database.managers.DatabaseManager;
import database.managers.StudentManager;
import exception.InvalidAdminException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class StudentController implements Initializable
{
    
    @FXML private TextField txtSearchBar;
    @FXML private Button btnGo;
    @FXML private ComboBox<?> cmbDateRegistered;
    @FXML private ComboBox<?> cmbCategory;
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
    @FXML private TableColumn<ModuleTableGUI, String>unitAttended;
    @FXML private TableColumn<ModuleTableGUI, String>completed;
    @FXML private TableColumn<ModuleTableGUI, String>pass;
    @FXML private TableColumn<ModuleTableGUI, String>fail;
    
    //private ObservableList<Student> list;

    // for class mainpulation
    private Student[] students;



    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
	//for student table
	id.setCellValueFactory(new PropertyValueFactory<StudentTableGUI, String>("id"));
	firstName.setCellValueFactory(new PropertyValueFactory<StudentTableGUI, String>("firstName"));
	lastName.setCellValueFactory(new PropertyValueFactory<StudentTableGUI, String>("lastName"));
	className.setCellValueFactory(new PropertyValueFactory<StudentTableGUI, String>("className"));
	studentTable.setItems(getstudents());
	
	//for modules table per student
	module.setCellValueFactory(new PropertyValueFactory<ModuleTableGUI, String>("module"));
	paid.setCellValueFactory(new PropertyValueFactory<ModuleTableGUI, String>("paid"));
	booked.setCellValueFactory(new PropertyValueFactory<ModuleTableGUI, String>("booked"));
	unitAttended.setCellValueFactory(new PropertyValueFactory<ModuleTableGUI, String>("unitAttended"));
	completed.setCellValueFactory(new PropertyValueFactory<ModuleTableGUI, String>("completed"));
	pass.setCellValueFactory(new PropertyValueFactory<ModuleTableGUI, String>("pass"));
	fail.setCellValueFactory(new PropertyValueFactory<ModuleTableGUI, String>("fail"));
    }

    public void getuser() throws MalformedURLException
    {
	int selection = studentTable.getSelectionModel().getSelectedIndex();
	lblisActive.setText(students[selection].isActive() ? "Yes" : "No");
	lbldateRegistered.setText(String.valueOf(students[selection].getDateAdmitted()));
	lblcertissused.setText(students[selection].getCertificateIssued());
	lblemail.setText(students[selection].getEmailAddress());
	// imgStudentImage.setImage(new Image(students[selection].getImage()));

	String localUrl = students[selection].getImage().toURI().toURL().toString();
	Image localImage1 = new Image(localUrl, false);
	imgStudentImage.setImage(localImage1);
	
	moduleTable.setItems(getModuleforStudent());
	createChart();

    }// end method get user
    
    
    private ObservableList<StudentTableGUI> getstudents(){

	ObservableList<StudentTableGUI> studentList = FXCollections.observableArrayList();

	// Step 1 :Create current Admin
	//should be removed later on after login screen has been created
	Admin currentAdmin = new Admin("Chidi", "OguejioforTheGreat");
	DatabaseManager.setCurrentAdmin(currentAdmin);

	// Step 2: call getStudents method
	boolean isActive = true;
	try{
	    students = StudentManager.getStudents(isActive, 0);

	    for (int i = 0; i < students.length; i++)
	    {
		studentList.add(new StudentTableGUI(students[i].getIdCardNumber(), students[i].getFirstName(),
			students[i].getLastName(), students[i].getModClassName()));

	    }
	}
	catch (SQLException | InvalidAdminException e){
	    e.printStackTrace();
	}
	finally{
	    // very important! Close the connection when the user closes the app
	    ConnectionManager.close();
	}return studentList;

    }// end method get student
    
    private ObservableList<ModuleTableGUI> getModuleforStudent(){
	ObservableList<ModuleTableGUI> moduleList = FXCollections.observableArrayList();
	moduleList.add(new ModuleTableGUI("mech fittings", "Yes", "Yes", "4", "No", "Yes", ""));
	moduleList.add(new ModuleTableGUI("Digital Tech", "Yes", "No", "4", "Yes", "", "Yes"));
	moduleList.add(new ModuleTableGUI("CAC", "No", "Yes", "3", "No", "Yes", ""));
	
	return moduleList;
	
    }

	private void createChart() {
		//import piechart data
		ObservableList<Data> list = FXCollections.observableArrayList(
				new PieChart.Data("basic elect", Math.round(Math.random()*10)),
				new PieChart.Data("MMF", Math.round(Math.random()*10)),
				new PieChart.Data("electronic", Math.round(Math.random()*10)),
				new PieChart.Data("ethics", Math.round(Math.random()*10)),
				new PieChart.Data("others", Math.round(Math.random()*10))
				);
		pieChart.setData(list);
	}

}//end class
