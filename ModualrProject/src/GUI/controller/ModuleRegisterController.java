package GUI.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import GUI.utilities.ModuleRegisterTableGUI;
import database.bean.ModuleRegister;
import database.managers.ModuleRegisterManager;
import database.managers.PaymentManager;
import exception.InvalidAdminException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.ModuleRegisterFilter;
import utils.ValidationType;

public class ModuleRegisterController implements Initializable
{
    
    //add amount per unit
    //number of units
    //total cost for module
    //switch booking and attendance
    //status indicator
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
    @FXML private Button btnRegisterStudent;       
    @FXML private Button btnResult;
    @FXML private Button btnBooking;
    @FXML private Button btnAttendance;
    @FXML private Button btnPayment;
    
    private TextInputDialog dialog;
    private int selection;
    
    //gotten from selection
    private ModuleRegister[] modRegs;
    private int selectedIndex;
    
    private int modRegId;
    private String studId; 
    private String modulename;
    
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
	try {
	    modRegs = ModuleRegisterManager.getRegisteredModules(0);
	}
	catch (SQLException e){
	    e.printStackTrace();
	}
	for (int i = 0; i < modRegs.length; i++){
	    list.add(new ModuleRegisterTableGUI(modRegs[i].getId(), modRegs[i].getStudentId(),
		    modRegs[i].getModuleName()));

	}
	return list;

    }// end get Modules

    // Event Listener on TableView[#moduleRegisterTable].onMouseClicked
    @FXML
    public void getDetails(MouseEvent event)
    {
	selection = moduleRegisterTable.getSelectionModel().getSelectedIndex();
	lblStudentName.setText(modRegs[selection].getStudentName());
	lblPaymentstatus.setText(modRegs[selection].paymentComplete() ? "Completed" : "Incomplete");
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

	
	//used by other methods
	modRegId = modRegs[selection].getId();
	studId = modRegs[selection].getStudentId();
	modulename = modRegs[selection].getModuleName();
    }// end method get details

    @FXML
    private void getFilter(ActionEvent event)
    {
	modRegs = null;
	try
	{
	    switch (selectedIndex){
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
	catch (SQLException e){
	    e.printStackTrace();
	} // search value
	list.clear();
	for (int i = 0; i < modRegs.length; i++){
	    list.add(new ModuleRegisterTableGUI(modRegs[i].getId(), modRegs[i].getStudentId(),
		    modRegs[i].getModuleName()));
	}
	moduleRegisterTable.setItems(list);
    }

    @FXML
    private void updatePayment(ActionEvent event){
	
	if (!moduleRegisterTable.getSelectionModel().isEmpty()){
	   int modregid =  moduleRegisterTable.getSelectionModel().getSelectedItem().getRegisterID();
	
	dialog = new TextInputDialog();
	dialog.setTitle("Update Payment");
	dialog.setHeaderText("Please Input Amount");

	Optional<String> result = dialog.showAndWait();
	String entered = null;
	
	if (result.isPresent()) {

		entered = result.get();
	}
	if (entered != null && !entered.isEmpty())
	{
	    /*double fee = Double.valueOf(entered);
	    Payment payment = new Payment(modregid,fee);*/ //commented by chidi
		try{
		    PaymentManager.makePayment(modregid);//just add the regID directly
		}
		catch (SQLException | InvalidAdminException e){
		    e.printStackTrace();
		}
	
		lblPaymentstatus.setText(modRegs[selection].paymentComplete() ? "Completed" : "Incomplete");
		moduleRegisterTable.setItems(getModuleRegister());
		moduleRegisterTable.getSelectionModel().select(selection);
		getDetails(null);
	
	}
	



	
	
	}

    }
    
    @FXML
    private void updateAttendance(ActionEvent event){
	boolean attendance = modRegs[selection].hasAttended();
	System.out.println("value before running is "+attendance);
	attendance = !attendance;
	
	try{
	    boolean successful = ModuleRegisterManager.setAttendance(modRegId,  attendance);//changed by chidi
	    
	}
	catch (SQLException | InvalidAdminException e){
	    e.printStackTrace();
	    System.out.println("an error occured");
	}
	
	moduleRegisterTable.setItems(getModuleRegister());
	moduleRegisterTable.getSelectionModel().select(selection);
	getDetails(null);
	
	System.out.println("value AFTER running is "+attendance);
	lblAttended.setText(modRegs[selection].hasAttended() ? "Yes" : "No");
	
    }
    
    @FXML
    private void updateBooking(ActionEvent event){
	try{
	    boolean successful  = ModuleRegisterManager.bookModule(modRegId, true); //changed by chidi :-you can change the true to a variable
	 }
	catch (SQLException | InvalidAdminException e)
	{
	    e.printStackTrace();
	}
	moduleRegisterTable.setItems(getModuleRegister());
	moduleRegisterTable.getSelectionModel().select(selection);
	getDetails(null);
	lblBookingStatus.setText(modRegs[selection].hasBooked() ? "Booked" : "Not Booked");
    }
    
    @FXML
    private void updateResult(ActionEvent event){
	try{
	    ModuleRegisterManager.setResultForModule(modRegId, "Pass");
	}
	catch (SQLException | InvalidAdminException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	moduleRegisterTable.setItems(getModuleRegister());
	moduleRegisterTable.getSelectionModel().select(selection);
	getDetails(null);
    }
    
    
    
    @FXML
    private void registerStudent(ActionEvent event){
	Stage window = new Stage();
	window.initModality(Modality.WINDOW_MODAL);
	window.setTitle("Register Student");

	// GridPane with 10px padding around edge
	GridPane grid = new GridPane();
	grid.setPadding(new Insets(10, 10, 10, 10));
	grid.setVgap(8);
	grid.setHgap(10);

	// Name Label - constrains use (child, column, row)
	Label nameLabel = new Label("StudentID:");
	GridPane.setConstraints(nameLabel, 0, 0);

	// Name Input
	TextField txtstudentID = new TextField();
	GridPane.setConstraints(txtstudentID, 1, 0);

	// Password Label
	Label moduleLabel = new Label("Module:");
	GridPane.setConstraints(moduleLabel, 0, 1);

	// Password Input
	TextField txtModuleName = new TextField();
	// passInput.setPromptText("password");
	GridPane.setConstraints(txtModuleName, 1, 1);

	// Login
	Button registerButton = new Button("Register");
	GridPane.setConstraints(registerButton, 1, 2);

	registerButton.setOnMouseClicked(e -> {
	    try
	    {
		ModuleRegister modReg = new ModuleRegister(txtModuleName.getText(), txtstudentID.getText());
		if (ModuleRegisterManager.registerForModule(modReg))
		{
		    System.out.println("Successfully registered Student for module and  "
			    + "also gave updated the dateCreated attribute.");
		    moduleRegisterTable.setItems(getModuleRegister());

		}
		else if (!modReg.isValid(ValidationType.NEW_BEAN))
		    System.err.println("The format of the ModuleRegister was invalid");
		else if (!ModuleRegisterManager.canRegister(modReg)){
		    System.err.println("Unsuccesful ! It possible the module has already been passed");
		    System.err.println("Or it's result is not out yet");
		}
		else{
		    System.out.println(modReg.getStudentId());
		    System.out.println(modReg.getModuleName());
		    System.out.println("Was Unsuccessful for unknown reasons!!!");
		}
	    }
	    catch (SQLException | InvalidAdminException e1)
	    {
		e1.printStackTrace();
	    }

	});
	// Add everything to grid
	grid.getChildren().addAll(nameLabel, txtstudentID, moduleLabel, txtModuleName, registerButton);

	Scene scene = new Scene(grid, 300, 200);
	window.setScene(scene);
	window.show();

    }

    @FXML
    private void keyPressed(KeyEvent event)
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
	    catch (SQLException e)
	    {
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
