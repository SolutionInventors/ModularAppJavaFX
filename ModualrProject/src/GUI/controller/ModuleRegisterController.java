package GUI.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class ModuleRegisterController {
	@FXML private TextField txtSearchBar;
	@FXML private Button btnGo;
	@FXML private ComboBox<?> cmbDateRegistered;
	@FXML private ComboBox<?> cmbCategory;
	@FXML private TableView<?> moduleRegisterTable;
	@FXML private TableColumn<?, ?> registerID;
	@FXML private TableColumn<?, ?> studentID;
	@FXML
	private TableColumn<?, ?> moduleName;
	@FXML
	private Label lblPaymentstatus;
	@FXML
	private Label lblMoudleName;
	@FXML
	private Label lblStudentName;
	@FXML
	private Label lblResult;
	@FXML
	private Label lblBookingStatus;
	@FXML
	private Label lblAmountPaid;

	// Event Listener on TableView[#moduleRegisterTable].onMouseClicked
	@FXML
	public void getDetails(MouseEvent event) {
	}
}
