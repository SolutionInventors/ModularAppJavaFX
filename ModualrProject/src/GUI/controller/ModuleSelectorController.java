package GUI.controller;

import java.sql.SQLException;

import database.managers.CertificateRegisterManager;
import exception.InvalidAdminException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ModuleSelectorController {
	@FXML private ListView<String> listModulesinCert;
	@FXML private ListView<String> listModulesNotinCert;
	@FXML private Button btnsendLeft;
	@FXML private Button btnsendRight;
	@FXML private Label lblModuleName;
	
	private String certName;
	//private String certName = "Electromecnics";
	// For Tie Breaker Selection
	private ObservableList<String> modulesinCert = FXCollections.observableArrayList();
	private final ObservableList<String> moudlesnotinCert = FXCollections.observableArrayList();
		
	public void setCertName(String name) {
	    certName = name;
	    lblModuleName.setText(certName); 
	   
	    String modulesincertsting[] = null;
	    String modulesnotincertsting[] = null;
	    try{
		 modulesincertsting = CertificateRegisterManager.getModules(certName, true);
		 modulesnotincertsting= CertificateRegisterManager.getModules(certName, false);
	    }
	    catch (SQLException e)   {
		e.printStackTrace();
	    }//get certificate name from previous class
	    
	    for (int i = 0; i < modulesincertsting.length; i++)  {
		modulesinCert.add(modulesincertsting[i]);
	    }
	    for (int i = 0; i < modulesnotincertsting.length; i++)  {
		moudlesnotinCert.add(modulesnotincertsting[i]);
	    }
	    listModulesinCert.setItems(modulesinCert);
	    listModulesNotinCert.setItems(moudlesnotinCert);
	  
	
	}

	@FXML public void next(ActionEvent event) {
	
	    try {
		CertificateRegisterManager.clearAllModules(certName);
	    }
	    catch (SQLException | InvalidAdminException e1) {
		e1.printStackTrace();
	    }
	    String[] selectedMoudles = new String[modulesinCert.size()];
	    for (int i = 0; i < modulesinCert.size(); i++){
		selectedMoudles[i] = modulesinCert.get(i);
	    }
	
	    try {
		CertificateRegisterManager.addMultipleModules(certName, selectedMoudles);
	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
	    }
	}
	
	@FXML
	public void sendRight(ActionEvent event) {

		String potential = listModulesinCert.getSelectionModel().getSelectedItem();
		if (potential != null) {
		    listModulesinCert.getSelectionModel().clearSelection();
		    modulesinCert.remove(potential);
		    moudlesnotinCert.add(0, potential);
			// selectedBreaker.add(potential);
		}
	}

	@FXML
	public void sendLeft(ActionEvent event) {
		String selectedModule = listModulesNotinCert.getSelectionModel().getSelectedItem();
		if (selectedModule != null) {
		    listModulesNotinCert.getSelectionModel().clearSelection();
		    moudlesnotinCert.remove(selectedModule);
		    modulesinCert.add(selectedModule);
		}
	};
	
	
	public static String toTitleCase(String input) {
	    StringBuilder titleCase = new StringBuilder();
	    boolean nextTitleCase = true;

	    for (char c : input.toCharArray()) {
	        if (Character.isSpaceChar(c)) {
	            nextTitleCase = true;
	        } else if (nextTitleCase) {
	            c = Character.toTitleCase(c);
	            nextTitleCase = false;
	        }

	        titleCase.append(c);
	    }

	    return titleCase.toString();
	}
}
