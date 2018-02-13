package GUI.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import GUI.utilities.ModuleRegisterTableGUI;
import database.bean.ModuleRegister;
import database.managers.ModuleRegisterManager;
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
import javafx.scene.input.MouseEvent;

public class ModuleRegisterController implements Initializable{
	@FXML private TextField txtSearchBar;
	@FXML private Button btnGo;
	@FXML private ComboBox<?> cmbDateRegistered;
	@FXML private ComboBox<?> cmbCategory;
	@FXML private TableView<ModuleRegisterTableGUI> moduleRegisterTable;
	
	@FXML private TableColumn<ModuleRegisterTableGUI, Integer> registerID;
	@FXML private TableColumn<ModuleRegisterTableGUI, String> studentID;
	@FXML private TableColumn<ModuleRegisterTableGUI, String> moduleName;
	
	@FXML private Label lblPaymentstatus;
	@FXML private Label lblMoudleName;
	@FXML private Label lblStudentName;
	@FXML private Label lblResult;
	@FXML private Label lblBookingStatus;
	@FXML private Label lblAttended;
	private ModuleRegister[] modRegs;
	
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1){
		//for student table
		registerID.setCellValueFactory(new PropertyValueFactory<ModuleRegisterTableGUI, Integer>("registerID"));
		studentID.setCellValueFactory(new PropertyValueFactory<ModuleRegisterTableGUI, String>("studentID"));
		moduleName.setCellValueFactory(new PropertyValueFactory<ModuleRegisterTableGUI, String>("moduleName"));
		moduleRegisterTable.setItems(getModuleRegister());
		
	    }
	    
	    public ObservableList<ModuleRegisterTableGUI> getModuleRegister() {
		  
		    ObservableList<ModuleRegisterTableGUI> list = FXCollections.observableArrayList();
		    try {
			modRegs = ModuleRegisterManager.getRegisteredModules(0);
		    }
		    catch (SQLException e)
		    {
			e.printStackTrace();
		    }
			for ( int i = 0 ; i < modRegs.length ; i++ ){
			    list.add(new  ModuleRegisterTableGUI(modRegs[i].getId(),modRegs[i].getStudentId(),modRegs[i].getModuleName()));
			 
			}
			System.out.println(list);
			return list;
			
		}//end get Modules

	 // Event Listener on TableView[#moduleRegisterTable].onMouseClicked
		@FXML
		public void getDetails(MouseEvent event) {
		    int selection = moduleRegisterTable.getSelectionModel().getSelectedIndex();
		    lblStudentName.setText(modRegs[selection].getStudentId());//no method
		    lblPaymentstatus.setText(modRegs[selection].paymentComplete()?"Completed":"Incomplete");//no method
		    lblResult.setText(modRegs[selection].getResult());
		 //   lblAttended.setText(modRegs[selection].hasAttended()?"Attended":"Not Attended");//returns null
		    lblBookingStatus.setText(modRegs[selection].hasBooked()?"Booked":"Not Booked");	
		   
		}//end method get details
}
