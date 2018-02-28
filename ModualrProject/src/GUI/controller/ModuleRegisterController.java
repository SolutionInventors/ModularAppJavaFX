package GUI.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import GUI.utilities.ModuleRegisterTableGUI;
import database.bean.ModuleRegister;
import database.managers.ModuleRegisterManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import utils.ModuleRegisterFilter;

public class ModuleRegisterController implements Initializable
{
    @FXML private ComboBox<String> cmbCategory;
    @FXML private ImageView studentImage;

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
    @FXML private Label lblSearch;
    
    @FXML private TextField txtSearch;
    @FXML private Button btnGO;
    
    
    private ModuleRegister[] modRegs;
    private int selectedIndex;
    ObservableList<ModuleRegisterTableGUI> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
	// for student table
	registerID.setCellValueFactory(new PropertyValueFactory<ModuleRegisterTableGUI, Integer>("registerID"));
	studentID.setCellValueFactory(new PropertyValueFactory<ModuleRegisterTableGUI, String>("studentID"));
	moduleName.setCellValueFactory(new PropertyValueFactory<ModuleRegisterTableGUI, String>("moduleName"));
	moduleRegisterTable.setItems(getModuleRegister());

	ObservableList<String> filters = FXCollections.observableArrayList("Reg ID", "Student ID", "Module Name",
		"Booked Modules", "Completed Modules", "All");
	cmbCategory.setItems(filters);
    }

    public ObservableList<ModuleRegisterTableGUI> getModuleRegister()
    {

	// ObservableList<ModuleRegisterTableGUI> list =
	// FXCollections.observableArrayList();
	list.clear();
	try
	{
	    modRegs = ModuleRegisterManager.getRegisteredModules(0);
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	}
	for (int i = 0; i < modRegs.length; i++)
	{
	    list.add(new ModuleRegisterTableGUI(modRegs[i].getId(), modRegs[i].getStudentId(),
		    modRegs[i].getModuleName()));

	}
	return list;

    }// end get Modules

    // Event Listener on TableView[#moduleRegisterTable].onMouseClicked
    @FXML
    public void getDetails(MouseEvent event)
    {
	int selection = moduleRegisterTable.getSelectionModel().getSelectedIndex();
	lblStudentName.setText(modRegs[selection].getStudentId());// no method
	lblPaymentstatus.setText(modRegs[selection].paymentComplete() ? "Completed" : "Incomplete");// no method
	lblResult.setText(modRegs[selection].getResult());
	lblBookingStatus.setText(modRegs[selection].hasBooked() ? "Booked" : "Not Booked");
	lblAttended.setText(modRegs[selection].hasAttended() ? "Yes" : "No");
	String localUrl = null;
	try
	{
	    localUrl = modRegs[selection].getStudentImage().toURI().toURL().toString();
	}
	catch (MalformedURLException e)
	{
	    e.printStackTrace();
	}
	Image localImage1 = new Image(localUrl, false);
	studentImage.setImage(localImage1);

    }// end method get details

    @FXML
    private void getFilter(ActionEvent event)
    {
	modRegs = null;
	try
	{
	    switch (selectedIndex)
	    {
		case 0:
		    modRegs = ModuleRegisterManager.search(ModuleRegisterFilter.REG_ID, txtSearch.getText());
		    break;
		case 1:
		    modRegs = ModuleRegisterManager.search(ModuleRegisterFilter.STUDENT_ID, txtSearch.getText());
		    break;
		case 2:
		    modRegs = ModuleRegisterManager.search(ModuleRegisterFilter.MODULE_NAME, txtSearch.getText());
		    break;
		case 3:
		    modRegs = ModuleRegisterManager.getModuleRegisters(ModuleRegisterFilter.BOOKED_MODULES, 0);
		    break;
		case 4:
		    modRegs = ModuleRegisterManager.getModuleRegisters(ModuleRegisterFilter.COMPLETED_MODULES, 0);
		    break;
		default:
		    modRegs = ModuleRegisterManager.getModuleRegisters(ModuleRegisterFilter.ALL, 0);
		    break;
	    }
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	} // search value
	list.clear();
	for (int i = 0; i < modRegs.length; i++)
	{
	    list.add(new ModuleRegisterTableGUI(modRegs[i].getId(), modRegs[i].getStudentId(),
		    modRegs[i].getModuleName()));
	}
	moduleRegisterTable.setItems(list);
    }

    @FXML
    public void itemSelected(ActionEvent event)
    {
	selectedIndex = cmbCategory.getSelectionModel().getSelectedIndex();
	if (selectedIndex <= 2)
	   
	{
	    txtSearch.setVisible(true);
	    lblSearch.setVisible(true);
	    btnGO.setVisible(true);
	}
	else
	{
	    txtSearch.setVisible(false);
	    lblSearch.setVisible(false);
	    btnGO.setVisible(false);
	    try
	    {
		switch (selectedIndex)
		{
		    case 3:
			modRegs = ModuleRegisterManager.getModuleRegisters(ModuleRegisterFilter.BOOKED_MODULES, 0);
			break;
		    case 4:
			modRegs = ModuleRegisterManager.getModuleRegisters(ModuleRegisterFilter.COMPLETED_MODULES, 0);
			break;
		    default:
			modRegs = ModuleRegisterManager.getModuleRegisters(ModuleRegisterFilter.ALL, 0);
			break;
		}
	    }
	    catch (SQLException e){
		e.printStackTrace();
	    }
	    list.clear();
		for (int i = 0; i < modRegs.length; i++)
		{
		    list.add(new ModuleRegisterTableGUI(modRegs[i].getId(), modRegs[i].getStudentId(),
			    modRegs[i].getModuleName()));

		}
		moduleRegisterTable.setItems(list);
	}
    }
}
