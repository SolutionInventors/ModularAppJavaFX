package GUI.utilities;

import javafx.beans.property.SimpleStringProperty;

public class ModuleTableGUI
{
    private final SimpleStringProperty module;
    private final SimpleStringProperty paid;
    private final SimpleStringProperty booked;
    private final SimpleStringProperty unitAttended;
    private final SimpleStringProperty completed;
    private final SimpleStringProperty pass;
    private final SimpleStringProperty fail;

    public ModuleTableGUI(String module, String paid, String booked, String unitAttended, String completed, String pass,
	    String fail)
    {
	super();
	this.module = new SimpleStringProperty(module);
	this.paid = new SimpleStringProperty(paid);
	this.booked = new SimpleStringProperty(booked);
	this.unitAttended = new SimpleStringProperty(unitAttended);
	this.completed = new SimpleStringProperty(completed);
	this.pass = new SimpleStringProperty(pass);
	this.fail = new SimpleStringProperty(fail);
    }

    public String getModule()
    {
	return module.get();
    }

    public String getPaid()
    {
	return paid.get();
    }

    public String getBooked()
    {
	return booked.get();
    }

    public String getUnitAttended()
    {
	return unitAttended.get();

    }

    public String getCompleted()
    {
	return completed.get();
    }

    public String getPass()
    {
	return pass.get();
    }

    public String getFail()
    {
	return fail.get();
    }

}// end class
