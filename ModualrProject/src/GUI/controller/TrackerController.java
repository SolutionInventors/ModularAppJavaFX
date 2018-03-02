package GUI.controller;

import java.sql.SQLException;

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
	@FXML private ComboBox<String> cmbFilter;
	
	 public void initialize() {
	     ObservableList<String> filters = FXCollections.observableArrayList("Inserted","Updated","Deleted","All");
	     //cmbFilter.setItems(filters);
	    // cmbFilter.setValue("All");
	     
	     try
	    {
		StudentLog[] studLog =  LogManager.getLog(StudentLog.class,
			TransactionType.INSERT, 0);//TransactionType can take 3 different things
		
	    }
	    catch (SQLException e)
	    {
		// FIXME Auto-generated catch block
		e.printStackTrace();
	    }
	 }
	 

	    @FXML
	    public void itemsSelected(ActionEvent event) {
	    }

}
