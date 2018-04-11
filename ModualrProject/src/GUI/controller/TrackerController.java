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
	@FXML private ComboBox<String> cmbStudentLog;
	private CertificateRegisterLog[] certRegLog;

    public void initialize()
    {
	ObservableList<String> filters = FXCollections.observableArrayList("Inserted", "Updated", "Deleted", "All");
	cmbStudentLog.setItems(filters);
	cmbStudentLog.setValue("All");

	try {
	    CertificateRegisterLog[] certRegLog = LogManager.getLog(CertificateRegisterLog.class, TransactionType.NONE,
		    0);// TransactionType can take 3 different things

	    txtAreaStudentLog.setWrapText(true);
	    Arrays.stream(certRegLog).forEach(log -> {
		txtAreaStudentLog.setText(txtAreaStudentLog.getText() + "- " + log + "\n\n");
	    });

	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
    }

    @FXML
    public void itemsSelectedfrmStudent(ActionEvent event)
    {
	try
	{
	    switch (cmbStudentLog.getSelectionModel().getSelectedIndex())
	    {
		case 0:
		    txtAreaStudentLog.clear();
		  certRegLog = LogManager.getLog(CertificateRegisterLog.class, TransactionType.INSERT,
			    0);// TransactionType can take 3 different things

		    txtAreaStudentLog.setWrapText(true);
		    Arrays.stream(certRegLog).forEach(log -> {
			txtAreaStudentLog.setText(txtAreaStudentLog.getText() + "- " + log + "\n\n");
		    });
		    break;
		case 1:
		    txtAreaStudentLog.clear();
		   certRegLog = LogManager.getLog(CertificateRegisterLog.class, TransactionType.UPDATE,
			    0);// TransactionType can take 3 different things

		    txtAreaStudentLog.setWrapText(true);
		    Arrays.stream(certRegLog).forEach(log -> {
			txtAreaStudentLog.setText(txtAreaStudentLog.getText() + "- " + log + "\n\n");
		    });
		    break;
		case 2:
		    txtAreaStudentLog.clear();
		    certRegLog = LogManager.getLog(CertificateRegisterLog.class, TransactionType.DELETE,
			    0);// TransactionType can take 3 different things

		    txtAreaStudentLog.setWrapText(true);
		    Arrays.stream(certRegLog).forEach(log -> {
			txtAreaStudentLog.setText(txtAreaStudentLog.getText() + "- " + log + "\n\n");
		    });
		    break;
		case 3:
		    txtAreaStudentLog.clear();
		    certRegLog = LogManager.getLog(CertificateRegisterLog.class, TransactionType.NONE,
			    0);// TransactionType can take 3 different things

		    txtAreaStudentLog.setWrapText(true);
		    Arrays.stream(certRegLog).forEach(log -> {
			txtAreaStudentLog.setText(txtAreaStudentLog.getText() + "- " + log + "\n\n");
		    });
		    break;
		    
	    }// end switch
	    

	}//end try
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
    }

    @FXML
    public void itemsSelectedfrmmodule(ActionEvent event)
    {
    }

    @FXML
    public void itemsSelectedfrmClass(ActionEvent event)
    {
    }

    @FXML
    public void itemsSelectedfrmReg(ActionEvent event)
    {
    }

}
