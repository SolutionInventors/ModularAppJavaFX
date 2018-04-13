package GUI.controller;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import database.bean.log.CertificateLog;
import database.bean.log.CertificateRegisterLog;
import database.bean.log.ModularClassLog;
import database.bean.log.ModuleLog;
import database.bean.log.ModuleRegisterLog;
import database.bean.log.PaymentLog;
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
	@FXML private TextArea txtAreaPayment;
	@FXML private TextArea txtAreaCertificate;
	@FXML private TextArea txtAreaCertRegistration;
	@FXML private TextArea txtAreaModuleRegister;
	
	@FXML private List<ComboBox<String>> cmbArray;
	 
	private StudentLog[] studentLog;
	private ModuleLog[] moduleLog;
	private PaymentLog[] payLog;
	private ModularClassLog[] classLog ; 
	private ModuleRegisterLog[] regLog ; 
	private CertificateLog[] certLog ;
	private CertificateRegisterLog[] certRegLog;
	
    public void initialize()
    {
	ObservableList<String> filters = FXCollections.observableArrayList("Inserted", "Updated", "Deleted", "All");
	for (ComboBox<String> cmb : cmbArray){
	    cmb.setItems(filters);
	    cmb.setValue("All");
	}

	try {
	    studentLog = LogManager.getLog(StudentLog.class, TransactionType.ALL, 0);
	    moduleLog = LogManager.getLog(ModuleLog.class, TransactionType.ALL, 0);
	    payLog = LogManager.getLog(PaymentLog.class, TransactionType.ALL, 0); ;
	    classLog = LogManager.getLog(ModularClassLog.class, TransactionType.ALL, 0); 
	    regLog = LogManager.getLog(ModuleRegisterLog.class, TransactionType.ALL, 0); 
	    certLog = LogManager.getLog(CertificateLog.class, TransactionType.ALL, 0); ; 
	    certRegLog = LogManager.getLog(CertificateRegisterLog.class, TransactionType.ALL,0);// TransactionType can take 3 different things
	    
	    txtAreaStudentLog.setWrapText(true);
	    Arrays.stream(studentLog).forEach(log -> {
		txtAreaStudentLog.setText(txtAreaStudentLog.getText() + "- " + log + "\n\n");
	    });
	    
	    txtAreaModuleLog.setWrapText(true);
	    Arrays.stream(moduleLog).forEach(log -> {
		txtAreaModuleLog.setText(txtAreaModuleLog.getText() + "- " + log + "\n\n");
	    });
	    
	    txtAreaPayment.setWrapText(true);
	    Arrays.stream(payLog).forEach(log -> {
		txtAreaPayment.setText(txtAreaPayment.getText() + "- " + log + "\n\n");
	    });
	    
	    txtAreaClassLog.setWrapText(true);
	    Arrays.stream(classLog).forEach(log -> {
		txtAreaClassLog.setText(txtAreaClassLog.getText() + "- " + log + "\n\n");
	    });
	    
	    txtAreaCertificate.setWrapText(true);
	    Arrays.stream(certLog).forEach(log -> {
		txtAreaCertificate.setText(txtAreaCertificate.getText() + "- " + log + "\n\n");
	    });
	    
	    txtAreaCertRegistration.setWrapText(true);
	    Arrays.stream(certRegLog).forEach(log -> {
		txtAreaCertRegistration.setText(txtAreaCertRegistration.getText() + "- " + log + "\n\n");
	    });
	    
	    txtAreaModuleRegister.setWrapText(true);
	    Arrays.stream(regLog).forEach(log -> {
		txtAreaModuleRegister.setText(txtAreaModuleRegister.getText() + "- " + log + "\n\n");
	    });
	    
	    

	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
    }

    @FXML
    public void itemsSelectedfrmStudent(ActionEvent event) {
	try{
	    switch (cmbArray.get(0).getSelectionModel().getSelectedIndex()) {
		case 0:
		    studentLog = LogManager.getLog(StudentLog.class, TransactionType.INSERT,0);// TransactionType can take 3 different things
		    break;
		case 1:
		    studentLog = LogManager.getLog(StudentLog.class, TransactionType.UPDATE,0);// TransactionType can take 3 different things
		    break;
		case 2:
		    studentLog = LogManager.getLog(StudentLog.class, TransactionType.DELETE, 0);// TransactionType can take 3 different things
		    break;
		case 3:
		    studentLog = LogManager.getLog(StudentLog.class, TransactionType.ALL, 0);
		    break;
	    }// end switch
	    txtAreaStudentLog.clear();
	    Arrays.stream(studentLog).forEach(log -> {txtAreaStudentLog.setText(txtAreaStudentLog.getText() + "- " + log + "\n\n"); });
	}//end try
	catch (SQLException e){
	    e.printStackTrace();
	}
    }//end itemsSelectedfrmStudent

    @FXML
    public void itemsSelectedfrmmodule(ActionEvent event) {
	try{
	    switch (cmbArray.get(1).getSelectionModel().getSelectedIndex()) {
		case 0:
		    moduleLog = LogManager.getLog(ModuleLog.class, TransactionType.INSERT,0);// TransactionType can take 3 different things
		    break;
		case 1:
		    moduleLog = LogManager.getLog(ModuleLog.class, TransactionType.UPDATE,0);// TransactionType can take 3 different things
		    break;
		case 2:
		    moduleLog = LogManager.getLog(ModuleLog.class, TransactionType.DELETE, 0);// TransactionType can take 3 different things
		    break;
		case 3:
		    moduleLog = LogManager.getLog(ModuleLog.class, TransactionType.ALL, 0);
		    break;
	    }// end switch
	    txtAreaModuleLog.clear();
	    Arrays.stream(moduleLog).forEach(log -> {txtAreaModuleLog.setText(txtAreaModuleLog.getText() + "- " + log + "\n\n"); });
	}//end try
	catch (SQLException e){
	    e.printStackTrace();
	}
    }

    @FXML
    public void itemsSelectedfrmClass(ActionEvent event)
    {
	try{
	    switch (cmbArray.get(3).getSelectionModel().getSelectedIndex()) {
		case 0:
		    classLog = LogManager.getLog(ModularClassLog.class, TransactionType.INSERT,0);// TransactionType can take 3 different things
		    break;
		case 1:
		    classLog = LogManager.getLog(ModularClassLog.class, TransactionType.UPDATE,0);// TransactionType can take 3 different things
		    break;
		case 2:
		    classLog = LogManager.getLog(ModularClassLog.class, TransactionType.DELETE, 0);// TransactionType can take 3 different things
		    break;
		case 3:
		    classLog = LogManager.getLog(ModularClassLog.class, TransactionType.ALL, 0);
		    break;
	    }// end switch
	    txtAreaClassLog.clear();
	    Arrays.stream(classLog).forEach(log -> {txtAreaClassLog.setText(txtAreaClassLog.getText() + "- " + log + "\n\n"); });
	}//end try
	catch (SQLException e){
	    e.printStackTrace();
	}
	
    }
    
    @FXML
    public void itemsSelectedfrmPayment(ActionEvent event)
    {
	try{
	    switch (cmbArray.get(2).getSelectionModel().getSelectedIndex()) {
		case 0:
		    payLog = LogManager.getLog(PaymentLog.class, TransactionType.INSERT,0);// TransactionType can take 3 different things
		    break;
		case 1:
		    payLog = LogManager.getLog(PaymentLog.class, TransactionType.UPDATE,0);// TransactionType can take 3 different things
		    break;
		case 2:
		    payLog = LogManager.getLog(PaymentLog.class, TransactionType.DELETE, 0);// TransactionType can take 3 different things
		    break;
		case 3:
		    payLog = LogManager.getLog(PaymentLog.class, TransactionType.ALL, 0);
		    break;
	    }// end switch
	    txtAreaPayment.clear();
	    Arrays.stream(payLog).forEach(log -> {txtAreaPayment.setText(txtAreaPayment.getText() + "- " + log + "\n\n"); });
	}//end try
	catch (SQLException e){
	    e.printStackTrace();
	}
	
    }
    @FXML
    public void itemsSelectedfrmReg(ActionEvent event) {
	try{
	    switch (cmbArray.get(5).getSelectionModel().getSelectedIndex()) {
		case 0:
		  certRegLog = LogManager.getLog(CertificateRegisterLog.class, TransactionType.INSERT,0);// TransactionType can take 3 different things
		    break;
		case 1:
		   certRegLog = LogManager.getLog(CertificateRegisterLog.class, TransactionType.UPDATE,0);// TransactionType can take 3 different things
		    break;
		case 2:
		    certRegLog = LogManager.getLog(CertificateRegisterLog.class, TransactionType.DELETE, 0);// TransactionType can take 3 different things
		    break;
		case 3:
		    certRegLog = LogManager.getLog(CertificateRegisterLog.class, TransactionType.ALL, 0);// TransactionType can take 3 different things
		    break;
	    }// end switch
	    txtAreaCertRegistration.clear();
	    Arrays.stream(certRegLog).forEach(log -> {txtAreaCertRegistration.setText(txtAreaCertRegistration.getText() + "- " + log + "\n\n"); });
	}//end try
	catch (SQLException e){
	    e.printStackTrace();
	}
    }
    public void itemsSelectedfrmCert(ActionEvent event) {
	try{
	    switch (cmbArray.get(4).getSelectionModel().getSelectedIndex()) {
		case 0:
		    certLog = LogManager.getLog(CertificateLog.class, TransactionType.INSERT,0);// TransactionType can take 3 different things
		    break;
		case 1:
		    certLog = LogManager.getLog(CertificateLog.class, TransactionType.UPDATE,0);// TransactionType can take 3 different things
		    break;
		case 2:
		    certLog = LogManager.getLog(CertificateLog.class, TransactionType.DELETE, 0);// TransactionType can take 3 different things
		    break;
		case 3:
		    certLog = LogManager.getLog(CertificateLog.class, TransactionType.ALL, 0);// TransactionType can take 3 different things
		    break;
	    }// end switch
	    txtAreaCertificate.clear();
	    Arrays.stream(certLog).forEach(log -> {txtAreaCertificate.setText(txtAreaCertificate.getText() + "- " + log + "\n\n"); });
	}//end try
	catch (SQLException e){
	    e.printStackTrace();
	}
    }

    public void itemsSelectedfrmModReg(ActionEvent event) {
    try{
	    switch (cmbArray.get(6).getSelectionModel().getSelectedIndex()) {
		case 0:
		    regLog = LogManager.getLog(ModuleRegisterLog.class, TransactionType.INSERT,0);// TransactionType can take 3 different things
		    break;
		case 1:
		    regLog = LogManager.getLog(ModuleRegisterLog.class, TransactionType.UPDATE,0);// TransactionType can take 3 different things
		    break;
		case 2:
		    regLog = LogManager.getLog(ModuleRegisterLog.class, TransactionType.DELETE, 0);// TransactionType can take 3 different things
		    break;
		case 3:
		    regLog = LogManager.getLog(ModuleRegisterLog.class, TransactionType.ALL, 0);// TransactionType can take 3 different things
		    break;
	    }// end switch  
	    txtAreaModuleRegister.clear();
	    Arrays.stream(regLog).forEach(log -> {txtAreaModuleRegister.setText(txtAreaModuleRegister.getText() + "- " + log + "\n\n"); });
	}//end try
	catch (SQLException e){
	    e.printStackTrace();
	}
    
    }
}
