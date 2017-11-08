package database.bean;

import java.sql.Date;

/**
 * Objects of this {@code ModuleRegister} represent a single row of the 
 * {@code ModuleRegister } table in the database
 * @author Oguejiofor Chidiebere
 *
 */

public class ModuleRegister  implements Bean
{
    
    private int id;
    /**Foreign key from the Module table*/
    private String moduleName;
    /**Foreign key from the Student table*/
    private String studentId;
    
    private boolean paymentStatus;
    private boolean bookingStatus;
    private boolean attended;
    private String result;
    
    private Date dateRegistered;
    
    public ModuleRegister(){}
    
    public ModuleRegister(  int id , String moduleId, String studentId){
	setId( id );
	setModuleName(moduleId);
	setStudentId(studentId);
	
    }
    
    public ModuleRegister( int id , String moduleName , String studentId, boolean paid,
	    boolean booked, boolean attended, String result )
    {
	this( id, moduleName, studentId);
	setPaymentStatus( paid );
	setBookingStatus(booked);
	setAttended(attended);
	setResult(result);
    }
    /**
     * Gets the moduleId of this object
     * @return int
     */
    public String getModuleName()
    {
        return moduleName;
    }
    /**
     * Sets this object's moduleId
     * @param moduleName
     */
    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }
    
    /**
     *Gets this object's studentId
     */
    public String getStudentId()
    {
        return studentId;
    }
    
    /**
     * Sets this object's studentId
     * @param moduleId
     */
    public void setStudentId(String studentId)
    {
        this.studentId = studentId;
    }
    
    /**
     * Checks if the the current module has been paid for
     * @return true when module has been paid for
     */
    public boolean hasPaid()
    {
        return paymentStatus;
    }
    
    /**
     * Sets the payment status of this ModuleRegister
     * @param paymentStatus
     */
    public void setPaymentStatus(boolean paymentStatus)
    {
	
        this.paymentStatus = paymentStatus;
    }
    
    /**
     * Returns true when the student has booked for the {@code Module} representend
     * by this object's moduleId
     * @return boolean
     */
    public boolean hasBooked()
    {
        return bookingStatus;
    }
    
    /** Sets the booking status of this object. Note that
     * the payment statsu must be {@code true }  before bookingStatus can
     * be true.<br>This method sets the bookingStatus to {@code false} if the
     * paymentStatus is {@code false }
     * 
     */
    public void setBookingStatus(boolean bookingStatus)
    {
	this.bookingStatus = hasPaid() ? bookingStatus : false;
    }
    
    /**Returns true when the student( with this object's studentId )
     * has paid for the module represented
     * by this object's moduleId
     * @return
     */
    public boolean hasAttended()
    {
        return attended;
    }
    
    /** Sets the attended attribute of this object. Note that
     * this object's booking status must be {@code true }  before this {@code ModuleRegister}
     * attended attribute  can be {@code true}
     * be true.<br>
     * The attended would be set to {@code false } if the attended bookingStatus is {@code false}
     * 
     */
    public void setAttended(boolean attended)
    {
        this.attended = hasBooked() ? attended : false;
    }
    
    /**
     * Returns either PASS or FAIL indicates that the course was passed or failed
     * 
     * @return String
     */
    public String getResult()
    {
	
        return result;
    }
    
    /**
     * Sets the result of this ModuleRegister 
     * the result must be either "PASS" or "FAIL". <br>
     *  Sets the result to null if the argument is invalid
     * @param result
    */
    public void setResult(String result) 
    {
	if( result.toLowerCase().equals("pass" ) || 
	    result.toLowerCase().equals("fail") )
	{
	    this.result = result;
	}
	else
	   this.result = null;
    }
    public int getId()
    {
	return id;
    }
    public void setId(int id)
    {
	this.id = id;
    }

    
    public Date getDateRegistered()
    {
	return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered)
    {
	this.dateRegistered = dateRegistered;
    }

    public static boolean isValid(ModuleRegister status)
    {
	if( status.getStudentId() !=  null && 
		status.getStudentId() != null 	)
	    return true;
	return false;
    }
 
}
