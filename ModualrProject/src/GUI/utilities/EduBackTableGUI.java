package GUI.utilities;

import javafx.beans.property.SimpleStringProperty;

public class EduBackTableGUI{
    
    private SimpleStringProperty name;
    private  SimpleStringProperty startDate;
    private  SimpleStringProperty endDate;
    private  SimpleStringProperty course;
    private  SimpleStringProperty certification;

    public EduBackTableGUI() {
	this.name = new SimpleStringProperty("");
	this.startDate = new SimpleStringProperty("");
	this.endDate = new SimpleStringProperty("");
	this.course = new SimpleStringProperty("");
	this.certification = new SimpleStringProperty("");
    }
    
    public EduBackTableGUI(String name, String startDate, String endDate, String course,String certification) {
	super();
	this.name = new SimpleStringProperty(name);
	this.startDate = new SimpleStringProperty(startDate);
	this.endDate = new SimpleStringProperty(endDate);
	this.course = new SimpleStringProperty(course);
	this.certification = new SimpleStringProperty(certification);
    }

    public String getName()
    {
	return name.get();
    }

    public String getStartDate()
    {
	return startDate.get();
    }

    public String getEndDate()
    {
	return endDate.get();
    }

    public String getCourse()
    {
	return course.get();
    }

    public String getCertification()
    {
	return certification.get();
    }
   
    
    
    public void setName(String value)
    {
	this.name = new SimpleStringProperty(value);
	
    }

    public void setStartDate(String value)
    {
	this.startDate = new SimpleStringProperty(value);
	
    }

    public void setEndDate(String value)
    {
	this.endDate = new SimpleStringProperty(value);
	
    }

    public void setCourse(String value)
    {
	this.course = new SimpleStringProperty(value);
	
    }

    public void setCertification(String value)
    {
	this.certification = new SimpleStringProperty(value);
    }

}// end class
