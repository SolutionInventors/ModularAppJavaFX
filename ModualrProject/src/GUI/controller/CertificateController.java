package GUI.controller;

import java.io.IOException;
import java.sql.SQLException;

import GUI.utilities.custom.InputPair;
import GUI.utilities.custom.TestVBox;
import database.bean.Certificate;
import database.managers.CertificateManager;
import database.managers.CertificateRegisterManager;
import database.statistics.CertificateStats;
import database.statistics.StatisticsManager;
import exception.InvalidAdminException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.OrderBy;

public class CertificateController {
	@FXML private ListView<String> lvMoudlelist;
	@FXML private Label lblRequired;
	@FXML private Label lblissused;
	@FXML private ListView<String> lvCertificateList;

	public void initialize() {
	    setuplistview();
	   
	}
	private void setuplistview()
	{
	    try {
	    
		Certificate[] certs = CertificateManager.getCertificates(0, OrderBy.DATE_CREATED_ASC);
		for (Certificate certificate : certs) {
		   
		    lvCertificateList.getItems().add(certificate.getName());
		} 
	    }
	    catch (SQLException | InvalidAdminException e){
		e.printStackTrace();
	    }
	}
	    @FXML
	    void getDetails(MouseEvent event) {
		String selected = lvCertificateList.getSelectionModel().getSelectedItem();
		Certificate cert = new Certificate(selected);
		try{
		    CertificateStats certStats = StatisticsManager.retrieveStats(cert);
		    lblRequired.setText(String.valueOf(certStats.getModulesRequired()));
		    lblissused.setText(String.valueOf(certStats.getStudentIssued()));
		    //certStats.
		   String moduleList[] =  CertificateRegisterManager.getModulesRequired(selected);
		   lvMoudlelist.getItems().clear();
		   for (String module : moduleList)
		{
		       lvMoudlelist.getItems().add(module);
		}
		}
		catch (SQLException e)
		{
		    e.printStackTrace();
		}
	    }

	    @FXML void updateCertificate(ActionEvent e) throws IOException{
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/GUI/views/ModuleSelection.fxml").openStream());
		ModuleSelectorController ch = (ModuleSelectorController) loader.getController();
		ch.setCertName(lvCertificateList.getSelectionModel().getSelectedItem().toString());
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Select Module");
		//primaryStage.showAndWait();
		// setuplistview();
		
	    
	    }

	   private void show(String certName) throws IOException{
	  		Stage primaryStage = new Stage();
	  		FXMLLoader loader = new FXMLLoader();
	  		Pane root = loader.load(getClass().getResource("/GUI/views/ModuleSelection.fxml").openStream());
	  		ModuleSelectorController ch = (ModuleSelectorController) loader.getController();
	  		ch.setCertName(certName);
	  		
	  		Scene scene = new Scene(root);
	  		primaryStage.setScene(scene);
	  		primaryStage.show();
	  		primaryStage.setTitle("Select Module");
	  		
	  		primaryStage.setOnHiding( event -> { lvCertificateList.getItems().clear(); setuplistview();} );
	  		
	  		
	  	    
	  	    }
	    @FXML void addCertficate(ActionEvent ev) {
		Stage window = new Stage();
		window.setTitle("Add New Certificate");
		Button button;

		InputPair[] pairs =
		{
			new InputPair("Certificate Name:")
		};

		button = new Button();
		button.setText("Add");
		TestVBox layout = new TestVBox(button, pairs);

		Scene scene = new Scene(layout, 300, 250);
		window.setScene(scene);
		window.show();

		button.setOnMouseClicked(e -> {
		 
		  Certificate cert = new Certificate(layout.getValue("Certificate Name:"));
		  try
		{
		    boolean succeful = CertificateManager.createCertificate(cert);
		    if (succeful)
		    {
			window.close();
			show(cert.getName());
		    }
		}
		catch (SQLException | InvalidAdminException | IOException e1)
		{
		    e1.printStackTrace();
		}
		  
		});
		
	    }

	    @FXML void deleteCertficate(ActionEvent eee) {
		Certificate toDelete = new Certificate(lvCertificateList.getSelectionModel().getSelectedItem().toString());
		try
		{
		    CertificateManager.delete(toDelete);
		    lvCertificateList.getItems().clear();
		    setuplistview();
		}
		catch (SQLException | InvalidAdminException e)
		{
		    e.printStackTrace();
		}
	    }

}
