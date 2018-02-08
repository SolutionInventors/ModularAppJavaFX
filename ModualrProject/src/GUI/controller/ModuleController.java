package GUI.controller;

import java.net.URL;
import java.util.ResourceBundle;

import GUI.utilities.Module;
import GUI.utilities.StudentTableGUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class ModuleController implements Initializable{
	@FXML private TextField txtSearchBar;
	@FXML private Button btnGo;
	
	@FXML private TableView<Module>moduleTable;
	@FXML private TableColumn<Module, Integer>noofUnits;
	@FXML private TableColumn<Module, String>moduleName;
	
	@FXML private ComboBox cmbDateRegistered;
	@FXML private ComboBox cmbCategory;
	@FXML private Label lblStudentName;
	@FXML private ImageView imgStudentImage;
	@FXML private TextField txtmoduleName;
	@FXML private TextField txtCost;
	@FXML
	private TextField txtPaid;
	@FXML
	private TextField txtDateCreated;
	@FXML
	private TextField txtNoofUnits;
	@FXML
	private Label lblBooked;
	@FXML
	private Label lblPaid;
	@FXML
	private Label lblRegistered;
	@FXML
	private Label lblAttended;
	@FXML
	private Label lblPassed;
	@FXML
	private Label lblFailed;

	public ObservableList<Module> list = FXCollections.observableArrayList(
		new Module(10,"Electrical Installations"),
		new Module(3,"Digital Tech"),
		new Module(8,"MMF"),
		new Module(11,"basic Elect"),
		new Module(12,"Ethics")			
		);
	
	
	
	// Event Listener on TableView[#studentTable].onMouseClicked
	@FXML
	public void getDetails(MouseEvent event) {
	    Module mod = (Module)moduleTable.getSelectionModel().getSelectedItem();
	    txtmoduleName.setText(String.valueOf(mod.getModuleName()));
	    txtNoofUnits.setText(String.valueOf(mod.getNoofUnits()));
	    txtCost.setText("800");
	    lblBooked.setText(String.valueOf(Math.round(Math.random()*100)));
	    lblPaid.setText(String.valueOf(Math.round(Math.random()*100)));
	    lblRegistered.setText(String.valueOf(Math.round(Math.random()*100)));
	    lblAttended.setText(String.valueOf(Math.round(Math.random()*100)));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    noofUnits.setCellValueFactory(new PropertyValueFactory<Module, Integer>("noofUnits"));
		moduleName.setCellValueFactory(new PropertyValueFactory<Module, String>("moduleName"));
		moduleTable.setItems(list);
	}
}
