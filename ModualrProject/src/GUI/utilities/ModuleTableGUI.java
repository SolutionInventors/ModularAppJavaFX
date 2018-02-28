package GUI.utilities;

import javafx.beans.property.SimpleStringProperty;

public class ModuleTableGUI
{
    private final SimpleStringProperty moduleName;
    private final SimpleStringProperty paid;
    private final SimpleStringProperty booked;
    private final SimpleStringProperty result;

    public ModuleTableGUI(String moduleName, String paid, String booked, String result
	   )
    {
	super();
	this.moduleName = new SimpleStringProperty(moduleName);
	this.paid = new SimpleStringProperty(paid);
	this.booked = new SimpleStringProperty(booked);
	this.result = new SimpleStringProperty(result);
    }

    public String getModuleName()
    {
	return moduleName.get();
    }

    public String getPaid()
    {
	return paid.get();
    }

    public String getBooked()
    {
	return booked.get();
    }

  

    public String getResult()
    {
	return result.get();
    }

   

}// end class
