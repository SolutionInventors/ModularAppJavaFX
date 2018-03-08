package GUI.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import GUI.utilities.ModuleTabTable;
import GUI.utilities.custom.InputPair;
import GUI.utilities.custom.TestVBox;
import database.bean.Module;
import database.managers.ModuleManager;
import database.statistics.ModuleStats;
import database.statistics.StatisticsManager;
import exception.InvalidAdminException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import test.TestUtils;
import utils.BeanType;

public class ModuleController implements Initializable{
	@FXML private TextField txtSearchBar;
	@FXML private Button btnGo;
	
	@FXML private TableView<ModuleTabTable>moduleTable;
	@FXML private TableColumn<ModuleTabTable, Integer>noofUnits;
	@FXML private TableColumn<ModuleTabTable, String>moduleName; 
	@FXML private TableColumn<ModuleTabTable, String>amount;
	@FXML private TableColumn<ModuleTabTable, String>dateCreated;
	
	
	@FXML private ComboBox<?> cmbDateRegistered;
	@FXML private ComboBox<?> cmbCategory;
	@FXML private Label lblStudentName;
	@FXML private ImageView imgStudentImage;
	@FXML private TextField txtCost;
	
	@FXML private Label lblBooked;
	@FXML private Label lblRegistered;
	@FXML private Label lblPaid;
	@FXML private Label lblAttended;
	@FXML private Label lblPassed;
	@FXML private Label lblFailed;
	
	@FXML private RadioButton rbPie	;
	@FXML private RadioButton rbBar;
	
	@FXML PieChart pieChart;
	@FXML BarChart<?, ?> barChart;
	@FXML private CategoryAxis x;
	@FXML private NumberAxis y;
	
	//@FXML private Label 

	  private Module[] modules = null;
	  private ModuleStats modStats =null;
	
	// Event Listener on TableView[#studentTable].onMouseClicked
	@FXML
	public void getDetails(MouseEvent event) {
	    int selection = moduleTable.getSelectionModel().getSelectedIndex();
	   
	    try{
		modStats = StatisticsManager.retrieveStats(new Module(modules[selection].getName()));
	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
	    }
	    lblRegistered.setText(String.valueOf(modStats.getNumRegistered()));
	    lblPaid.setText(String.valueOf(modStats.getNumPaid()));
	    lblBooked.setText(String.valueOf(modStats.getNumBooked()));
	    lblAttended.setText(String.valueOf(modStats.getNumAttended()));
	    lblPassed.setText(String.valueOf(modStats.getNumPassed()));
	    lblFailed.setText(String.valueOf(modStats.getNumFailed()));
	    
	    createChart(modStats);
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
		
	}//end get Modules
	@FXML public void radioSelected(ActionEvent event) {
	    if (rbPie.isSelected()) {
		barChart.setVisible(false);
		pieChart.setVisible(true);
	    }else {
		pieChart.setVisible(false);
		barChart.setVisible(true);
	    }
	}
	
	@FXML public void addModule(ActionEvent event) {
	    	Stage window = new Stage();
	    	window.setTitle("Title of the Window");
		Button button;
	    	
		InputPair[] pairs = {
			new InputPair("Name:"),
			new InputPair("Amount per Unit:"),
			new InputPair("Number of Unit:")
		};
		
		
		button = new Button();
		button.setText("Add");
		TestVBox layout = new TestVBox(button,pairs);
		
		Scene scene = new Scene(layout, 300, 250);
		window.setScene(scene);
		window.show();
		
		button.setOnMouseClicked(e -> {
		    
		    String name = layout.getValue("Name:");
		    int numberOfUnits = Integer.valueOf((layout.getValue("Number of Unit:")));
		    double amount = Double.valueOf(layout.getValue("Amount per Unit:"));
		    Module newModule = new Module(name, numberOfUnits, amount);
		    
		    try
		    {
			if( ModuleManager.addNewModule(newModule) ) {
			System.out.println("Successfully created a new module and "
				+ "also  updated the dateCreated attribute.");
			moduleTable.setItems(getModules());
			}
			else
			{
			System.out.println("Was Unsuccessful for unknown reasons!!!");
			}
		    }
		    catch (SQLException | InvalidAdminException e1)
		    {
			e1.printStackTrace();
		    }
		});
		
		
	}
	@SuppressWarnings({
		"rawtypes", "unchecked"
	})
	private void createChart(ModuleStats modStats) {
		//import pie chart data
		ObservableList<Data> list = FXCollections.observableArrayList(
				new PieChart.Data("Registered", modStats.getNumRegistered()),
				new PieChart.Data("Paid", modStats.getNumPaid()),
				new PieChart.Data("Booked", modStats.getNumBooked()),
				new PieChart.Data("Attended", modStats.getNumAttended()),
				new PieChart.Data("Passed", modStats.getNumPassed()),
				new PieChart.Data("Failed", modStats.getNumFailed())
				);
		
		barChart.getData().clear();
		XYChart.Series set1 =new XYChart.Series<>();
		set1.getData().add(new XYChart.Data("Registered", modStats.getNumRegistered()));
		set1.getData().add(new XYChart.Data("Paid", modStats.getNumPaid()));
		set1.getData().add(new XYChart.Data("Booked", modStats.getNumBooked()));
		set1.getData().add(new XYChart.Data("Attended", modStats.getNumAttended()));
		set1.getData().add(new XYChart.Data("Passed", modStats.getNumPassed()));
		set1.getData().add(new XYChart.Data("Failed", modStats.getNumFailed()));
		barChart.getData().addAll(set1);
		
		pieChart.setData(list);
	}//end method create chart
}
