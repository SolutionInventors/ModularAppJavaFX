package GUI.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import GUI.utilities.AspStudentTableGUI;
import GUI.utilities.Paths;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AspiringStudentsController implements Initializable{
	@FXML private TextField txtSearchBar;
	@FXML private Button btnGo;
	@FXML private Button btnRegisterStudent;
	
	@FXML private ComboBox<?> cmbCategory;
	@FXML private TableView<AspStudentTableGUI> aspStudentTable;
	@FXML private TableColumn<AspStudentTableGUI,String> sn;
	@FXML private TableColumn<AspStudentTableGUI,String> firstName;
	@FXML private TableColumn<AspStudentTableGUI,String> lastName;
	@FXML private Label lblStudentName;
	@FXML private ImageView imgStudentImage;

	// Event Listener on TableView[#aspStudentTable].onMouseClicked
	@FXML
	public void getuser(MouseEvent event) {
	  
	}

	

	@FXML private void studentDetails(ActionEvent event) throws IOException {
	    	Stage regInfo = new Stage();
		regInfo.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource(Paths.viewpath + "VerifyStudent.fxml"));
		Scene scene = new Scene(root);
		//helpStage.getIcons().add(new Image(logoURL));
		regInfo.setResizable(false);
		regInfo.setScene(scene);
		regInfo.sizeToScene();
		regInfo.setTitle("Aspiring Students info");
		regInfo.show();
	    }
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	  //for student table
		sn.setCellValueFactory(new PropertyValueFactory<AspStudentTableGUI, String>("sn"));
		firstName.setCellValueFactory(new PropertyValueFactory<AspStudentTableGUI, String>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<AspStudentTableGUI, String>("lastName"));
		aspStudentTable.setItems(getstudents());
	}
	
	
	
	 private ObservableList<AspStudentTableGUI> getstudents(){

	     ObservableList<AspStudentTableGUI> aspStudentList = FXCollections.observableArrayList();
	     Student[] students;//to be changed to asp student
	     
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
			aspStudentList.add(new AspStudentTableGUI(String.valueOf(i+1), students[i].getFirstName(),
				students[i].getLastName()));

		    }
		}
		catch (SQLException | InvalidAdminException e){
		    e.printStackTrace();
		}
		finally{
		    // very important! Close the connection when the user closes the app
		    ConnectionManager.close();
		}return aspStudentList;

	    }// end method get student
}
