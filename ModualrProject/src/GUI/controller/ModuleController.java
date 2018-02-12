package GUI.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import GUI.utilities.ModuleTabTable;
import database.bean.Module;
import database.managers.ModuleManager;
import exception.InvalidAdminException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ModuleController implements Initializable{
	@FXML private TextField txtSearchBar;
	@FXML private Button btnGo;
	
	@FXML private TableView<ModuleTabTable>moduleTable;
	@FXML private TableColumn<ModuleTabTable, Integer>noofUnits;
	@FXML private TableColumn<ModuleTabTable, String>moduleName; 
	@FXML private TableColumn<ModuleTabTable, String>amount;
	@FXML private TableColumn<ModuleTabTable, String>dateCreated;
	
	
	@FXML private ComboBox cmbDateRegistered;
	@FXML private ComboBox cmbCategory;
	@FXML private Label lblStudentName;
	@FXML private ImageView imgStudentImage;
	@FXML private TextField txtCost;
	@FXML
	private TextField txtPaid;
	@FXML
	private TextField txtDateCreated;
	@FXML
	private Label lblBooked;
	@FXML
	private Label lblRegistered;
	@FXML
	private Label lblAttended;
	@FXML
	private Label lblPassed;
	@FXML
	private Label lblFailed;
	
	@FXML private Label  lbldateCreated;
	@FXML private Label lblAmount;
	//@FXML private Label 

	  Module[] modules = null;
	
	// Event Listener on TableView[#studentTable].onMouseClicked
	@FXML
	public void getDetails(MouseEvent event) {
	    int selection = moduleTable.getSelectionModel().getSelectedIndex();
	    ModuleTabTable mod = (ModuleTabTable)moduleTable.getSelectionModel().getSelectedItem();
	    lbldateCreated.setText(modules[selection].getDateCreated().toString());
	    lblBooked.setText(String.valueOf(Math.round(Math.random()*100)));
	    lblAmount.setText(String.valueOf(modules[selection].getAmountPerUnit()));
	    lblRegistered.setText(String.valueOf(Math.round(Math.random()*100)));
	    lblAttended.setText(String.valueOf(Math.round(Math.random()*100)));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    	noofUnits.setCellValueFactory(new PropertyValueFactory<ModuleTabTable, Integer>("noofUnits"));
		moduleName.setCellValueFactory(new PropertyValueFactory<ModuleTabTable, String>("moduleName"));
		dateCreated.setCellValueFactory(new PropertyValueFactory<ModuleTabTable, String>("dateCreated"));
		amount.setCellValueFactory(new PropertyValueFactory<ModuleTabTable, String>("amount"));
		moduleTable.setItems(getModules());
	}//end initialize
	
	public ObservableList<ModuleTabTable> getModules() {
	  
	    ObservableList<ModuleTabTable> list = FXCollections.observableArrayList();
	    try {
		modules = ModuleManager.getModules( 0 );
	    }
	    catch (SQLException | InvalidAdminException e)
	    {
		e.printStackTrace();
	    }
		for ( int i = 0 ; i < modules.length ; i++ ){
		    list.add(new  ModuleTabTable(modules[i].getNumberOfUnits(),modules[i].getName(),String.valueOf(modules[i].getAmountPerUnit()),String.valueOf(modules[i].getDateCreated())));
		 
		}
		return list;
		
	}
}
