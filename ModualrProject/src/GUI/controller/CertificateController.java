package GUI.controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.chart.PieChart;

import javafx.scene.control.TextField;

import javafx.scene.control.ListView;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class CertificateController {
	@FXML
	private TextField txtSearchBar;
	@FXML
	private Button btnGo;
	@FXML
	private ComboBox<?> cmbDateRegistered;
	@FXML
	private ComboBox<?> cmbCategory;
	@FXML
	private TableView<?> certificateTable;
	@FXML
	private TableColumn<?, ?> id;
	@FXML
	private TableColumn<?, ?> name;
	@FXML
	private TableColumn<?, ?> dateCreated;
	@FXML
	private PieChart pieChart;
	@FXML
	private ListView<?> lvMoudlelist;
	@FXML
	private Label lblRequired;
	@FXML
	private Label lblIssuable;
	@FXML
	private Label lblissused;

	// Event Listener on TableView[#certificateTable].onMouseClicked
	@FXML
	public void getDetails(MouseEvent event) {
	}
}
