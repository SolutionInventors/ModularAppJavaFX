package GUI.utilities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ModuleRegisterTableGUI
{
    //private final SimpleStringProperty module;
    private final SimpleStringProperty studentID;
    private final SimpleIntegerProperty registerID;
    private final SimpleStringProperty moduleName;

    public ModuleRegisterTableGUI(Integer registerID, String string, String moduleName )
    {
	super();
	this.registerID = new SimpleIntegerProperty(registerID);
	this.studentID = new SimpleStringProperty(string);
	this.moduleName = new SimpleStringProperty(moduleName);
    }

  
    public Integer getRegisterID(){
	return registerID.get();
    }

    public String getStudentID() {
	return studentID.get();
    }

    public String getModuleName() {
  	return moduleName.get();
      }




   

}// end class
