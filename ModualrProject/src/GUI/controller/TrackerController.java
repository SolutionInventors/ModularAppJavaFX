package GUI.controller;

import java.sql.SQLException;
import java.util.Arrays;

import database.bean.log.CertificateRegisterLog;
import database.bean.log.ModularClassLog;
import database.bean.log.StudentLog;
import database.bean.log.TransactionType;
import database.managers.LogManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class TrackerController {
	@FXML private TextArea txtAreaStudentLog;
	@FXML private TextArea txtAreaModuleLog;
	@FXML private TextArea txtAreaClassLog;
	@FXML private TextArea txtAreaRegistrationLog;
	@FXML private ComboBox<String> cmbFilter;
	
	 public void initialize() {
	     ObservableList<String> filters = FXCollections.observableArrayList("Inserted","Updated","Deleted","All");
	     //cmbFilter.setItems(filters);
	    // cmbFilter.setValue("All");
	     
	     try
	    {
		 CertificateRegisterLog[] certRegLog = 
				LogManager.getLog(CertificateRegisterLog.class, 
				TransactionType.NONE, 0);//TransactionType can take 3 different things
		
		
		txtAreaStudentLog.setWrapText(true);
		Arrays.stream(certRegLog).forEach( log ->{
		    txtAreaStudentLog.setText(txtAreaStudentLog.getText() + "- " + log + "\n\n");
		});
		
		
		
		/*ModularClassLog[] modularClassLog = 
			LogManager.getLog(ModularClassLog.class, 
			TransactionType.NONE, 0);
		txtAreaStudentLog.setWrapText(true);
		Arrays.stream(modularClassLog).forEach( log ->{
		    txtAreaStudentLog.setText(txtAreaStudentLog.getText() + " -Description: " + log);
		});*/
		
	    }
	    catch (SQLException e)  {
		e.printStackTrace();
	    }
	 }
	 

	    @FXML
	    public void itemsSelectedfrmStudent(ActionEvent event) {
	    }
	    @FXML
	    public void itemsSelectedfrmmodule(ActionEvent event) {
	    }
	    @FXML
	    public void itemsSelectedfrmClass(ActionEvent event) {
	    }
	    @FXML
	    public void itemsSelectedfrmReg(ActionEvent event) {
	    }

}
