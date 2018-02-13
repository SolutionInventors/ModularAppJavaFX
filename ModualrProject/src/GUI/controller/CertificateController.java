package GUI.controller;

import java.sql.SQLException;

import database.bean.Certificate;
import database.managers.CertificateManager;
import database.statistics.CertificateStats;
import database.statistics.StatisticsManager;
import exception.InvalidAdminException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import utils.OrderBy;

public class CertificateController {
	@FXML private ListView<?> lvMoudlelist;
	@FXML private Label lblRequired;
	@FXML private Label lblissused;
	@FXML private ListView<String> lvCertificateList;

	public void initialize() {
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
		}
		catch (SQLException e)
		{
		    e.printStackTrace();
		}
	    }
}
