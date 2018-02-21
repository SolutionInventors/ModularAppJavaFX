package GUI.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class CombineTabsController {
	@FXML private Tab tab1_students;
	@FXML private Tab tab2_Modules;
	@FXML private Tab tab3_ModuleRegister;
	@FXML private Tab tab4_Certificate;
	@FXML private Tab tab5_Statistics;
	@FXML private Tab tab6_Tracker;
	
	@FXML private StudentController tabstudentController;
	@FXML private ModuleController tabmoduleController;
	@FXML private ModuleRegisterController tabmoduleRegisterController;
	@FXML private CertificateController tabCertificateController;
	@FXML private StatisticsController tabStatisticsController;
	@FXML private TrackerController tabTrackerController;
}
