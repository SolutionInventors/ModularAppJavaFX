package GUI.controller;

import java.sql.SQLException;

import javax.tools.ForwardingFileObject;

import database.statistics.ModuleStats;
import database.statistics.StatisticsManager;
import database.statistics.TableStats;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class StatisticsController {
	@FXML private TextField txtSearchBar;
	@FXML private Button btnGo;
	@FXML private ComboBox<?> cmbDateRegistered;
	@FXML private ComboBox<?> cmbCategory;
	@FXML private Label lblUnitsPaid;
	@FXML private Label lblModRegistered;
	@FXML private Label lblModinDatabase;
	@FXML private Label lblModsFailed;
	@FXML private Label lblModsAttended;
	@FXML private Label lblModsPassed;
	@FXML private Label lblGraduatedStu;
	@FXML private Label lblActiveStu;
	@FXML private Label lblStuinDB;
	@FXML private Label lblStuRegisteredthisYear;
	@FXML private Label lblAverageStu;
	@FXML private ComboBox<?> cmbCategory1;
	@FXML private Label lblHigestCerAwarded;
	@FXML private Label lblStuCertified; 
	@FXML private Label lblCerinDB; 
	@FXML private Label lblLeastCerAwarded;
	@FXML private Label lblAverageperStream;
	@FXML private Label lblTotalStreaminDB; 
	@FXML private RadioButton rbBar;
	@FXML private ToggleGroup rt1;
	@FXML private RadioButton rbPie;
	@FXML private BarChart<?, ?> barChart;
	@FXML private CategoryAxis x;
	@FXML private NumberAxis y;
	@FXML private PieChart pieChart;
	
	@FXML private TabPane tabPane;
	@FXML private Tab tabMoudle;
	@FXML private Tab tabStudent;
	@FXML private Tab tabProgram;
	@FXML private Tab tabStream;
	
	private TableStats stat = null ;
	private String[] label;
	private int[] value;
	
    public void initialize()  {
	try{
	    stat = StatisticsManager.retrieveStats();
	    toShowinitaldata();
	}
	catch (SQLException e)	{
	    e.printStackTrace();
	}
	
	tabPane.getSelectionModel().selectedItemProperty()
		.addListener((ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) -> {

		    if (newValue == tabMoudle){
			toShowinitaldata();
			  }
		    else if (newValue == tabStudent)   {
			lblStuinDB.setText(String.valueOf(stat.getTotalNumberOfStudents()));
			lblActiveStu.setText(String.valueOf(stat.getNumberOfActiveStudents()));
			//lblGraduatedStu.setText(String.valueOf(stat.gra));
			lblStuRegisteredthisYear.setText(String.valueOf(stat.getTotalStudentRegisteredThisYear()));
			label = null;
			label = new String[4]; 
			label[0]= "Students in DB"; 
			label[1]="Active Students";
			label[2]= "Graduated Students";
			label[3]= "Registered this year";
			
			value=null;
			value = new int[4];
			value[0]=stat.getTotalNumberOfStudents();
			value[1]=stat.getNumberOfActiveStudents();
			value[2]=stat.getnumberOfModulesAttended();//incorrect
			value[3]=stat.getTotalStudentRegisteredThisYear();
			createChart(label,value);
		    
		    }
		    else if (newValue == tabProgram) {
			lblCerinDB.setText(String.valueOf(stat.getTotalNumberOfCertificates()));
			lblStuCertified.setText(String.valueOf(stat.getNumberOfCertifiedStudents()));
			lblHigestCerAwarded.setText(stat.getHighestAwardedCertificate());
			lblLeastCerAwarded.setText(stat.getLeastCertificateAwarded());
		    }
		    else if (newValue == tabStream) {
			lblTotalStreaminDB.setText(String.valueOf(stat.getNumberOfClasses()));
			lblAverageperStream.setText(String.valueOf(stat.getAverageStudentPerClass()));
		    }
		});

	

    }//end method initialize
    
    
    @SuppressWarnings({
	    "rawtypes", "unchecked"
    })
    private void createChart(String[] chartlabel, int[] chartValue) {
	//import pie chart data
	ObservableList<Data> list = FXCollections.observableArrayList();
			
			for (int i=0; i<chartlabel.length; i++) {
			    list.add(new PieChart.Data(chartlabel[i], chartValue[i]));
			}
		
	
	barChart.getData().clear();
	XYChart.Series set1 =new XYChart.Series<>();
	for (int i = 0; i < chartValue.length; i++){
	    set1.getData().add(new XYChart.Data(chartlabel[i], chartValue[i]));
	}
	barChart.getData().addAll(set1);
	
	pieChart.setData(list);
}//end method create chart
    
    private void toShowinitaldata() {
	lblModinDatabase.setText(String.valueOf(stat.getTotalNumberOfModules()));
	lblModRegistered.setText(String.valueOf(stat.getNumberOfModulesRegistered()));
	lblModsPassed.setText(String.valueOf(stat.getNumberOfModulesPassed()));
	lblModsFailed.setText(String.valueOf(stat.getNumberOfModulesFailed()));
	lblModsAttended.setText(String.valueOf(stat.getnumberOfModulesAttended()));
	label = null;
	label = new String[5]; 
	label[0]= "Total in DB"; 
	label[1]="Registered";
	label[2]= "Attended";
	label[3]= "Passed";
	label[4]= "Failed";
	
	value=null;
	value = new int[5];
	value[0]=stat.getTotalNumberOfModules();
	value[1]=stat.getNumberOfModulesRegistered();
	value[2]=stat.getnumberOfModulesAttended();
	value[3]=stat.getNumberOfModulesPassed();
	value[4]=stat.getNumberOfModulesFailed();
	createChart(label,value);
	
    }

    // Event Listener on RadioButton[#rbBar/#rbPie].onAction
    @FXML
    public void radioSelected(ActionEvent event)
    {
	if (rbPie.isSelected()) {
		barChart.setVisible(false);
		pieChart.setVisible(true);
	    }else {
		pieChart.setVisible(false);
		barChart.setVisible(true);
	    }
    }

}
