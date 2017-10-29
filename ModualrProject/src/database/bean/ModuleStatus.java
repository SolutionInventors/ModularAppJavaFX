package database.bean;

import exception.InvalidResultException;

/**
 * Objects of this {@code ModuleStatus} represent a single row of the 
 * {@code ModuleStatus } table in the database
 * @author Oguejiofor Chidiebere
 *
 */

public class ModuleStatus  implements Bean
{
    /**Foreign key from the Module table*/
    private int moduleId;
    /**Foreign key from the Student table*/
    private int studentId;
    
    private boolean paymentStatus;
    private boolean bookingStatus;
    private boolean attended;
    private String result;
    
    /**
     * Gets the moduleId of this object
     * @return int
     */
    public int getModuleId()
    {
        return moduleId;
    }
    /**
     * Sets this object's moduleId
     * @param moduleId
     */
    public void setModuleId(int moduleId)
    {
        this.moduleId = moduleId;
    }
    
    /**
     *Gets this object's studentId
     */
    public int getStudentId()
    {
        return studentId;
    }
    
    /**
     * Sets this object's studentId
     * @param moduleId
     */
    public void setStudentId(int studentId)
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
     * Sets the payment status of this ModuleStatus
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
    
    /** Sets the booking status of this object*/
    public void setBookingStatus(boolean bookingStatus)
    {
        this.bookingStatus = bookingStatus;
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
    public void setAttended(boolean attended)
    {
	
        this.attended = attended;
    }
    
    /**
     * Returns eitther PASS or FAIL indicatig that the course was passed or failed
     * 
     * @return String
     */
    public String getResult()
    {
	
        return result;
    }
    
    /**
     * Sets the result of this ModuleStatus 
     * the result must be either "PASS" or "FAIL"
     * @param result
     * @throws InvalidResultException  when the result is neither "pass" nor "fail"
     */
    public void setResult(String result) throws InvalidResultException
    {
	if( result.toLowerCase().equals("pass" ) || 
	    result.toLowerCase().equals("fail") )
	{
	    this.result = result;
	}
	else
	    throw new InvalidResultException("The result must be either pass or fail");
    }
    
    
}
