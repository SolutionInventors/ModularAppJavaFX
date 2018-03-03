package database.bean.log;

import java.sql.Date;

/**
 * A {@code ModuleRegisterLog} keeps track of the transactions that occur in the
 * {@code ModuleRegister} table. The values in this table are update via triggers
 * assigned to the {@code ModuleRegister} table. 
 * 
 * @author Chidiebere
 * @see database.bean.ModuleRegister
 */
public class ModuleRegisterLog extends Log
{
    private final int REG_ID;
    private final String STUDENT_ID;
    private final String OLD_MODULE_NAME;
    private final String NEW_MODULE_NAME;
    private final boolean OLD_BOOKING_STATUS;
    private final boolean NEW_BOOKING_STATUS;
    private final boolean OLD_ATTENDANCE_STATUS;
    private final boolean NEW_ATTENDANCE_STATUS;
    private final String OLD_RESULT;
    private final String NEW_RESULT;
    private UpdateType updateType; 


    private enum UpdateType{
	BOOKING, RESULT, ATTENDANCE_STATUS; 
    }
    /**
     * Initializes this {@code ModuleRegisterLog} with the required fields
     * @param operationDate the date the transaction took place
     * @param transactionType an {@link TransactionType} that stores the type of transaction
     * that took place
     * @param regId the {@code id} of the {@code ModuleRegister} table
     * @param studId the id card number of the affected student
     * @param oldModName the module name before the transaction was made
     * @param newModName the module nam after the transaction was made
     * @param oldBooking the booking status before the transaction was made
     * @param newBooking the booking staus after the transaction
     * @param oldAttendance the attendance status before the transaction
     * @param newAttendance the attendance status after the transaction 
     * @param oldResult the result before the transaction
     * @param newResult the  result after the transaction
     */
    public ModuleRegisterLog(  Date operationDate ,  TransactionType transactionType,int regId ,
	    String studId, String oldModName, String newModName,
	    boolean oldBooking, boolean newBooking, boolean oldAttendance, 
	    boolean newAttendance, String oldResult, String newResult)
    {
	super( operationDate, transactionType );
	REG_ID = regId;
	STUDENT_ID = studId;
	OLD_MODULE_NAME = oldModName;
	NEW_MODULE_NAME = newModName;
	OLD_BOOKING_STATUS = oldBooking;
	NEW_BOOKING_STATUS = newBooking;
	OLD_ATTENDANCE_STATUS = oldAttendance;
	NEW_ATTENDANCE_STATUS = newAttendance;
	OLD_RESULT = oldResult;
	NEW_RESULT = newResult;
	if(getOperationType()  == TransactionType.UPDATE) setUpdateType(); 
    }

    /**
     * Gets the {@code id} attribute of the {@code ModuleRegister} table
     * @return an {@code int}
     */
    public int getRegId()
    {
	return REG_ID;
    }

    /**
     *  Gets the id card number of the affected Student as a {@code String}
     * @return  a {@code String}
     */ 
    public String getStudentID()
    {
	return STUDENT_ID;
    }

    /**
     * Gets the module name before the transaction was made
     * @return
     */
    public String getOldModuleName()
    {
	return OLD_MODULE_NAME;
    }

    /**
     * Gets the module name after the transaction was made
     * @return
     */
    public String getNewModuleName()
    {
	return NEW_MODULE_NAME;
    }

    /**
     * Gets the booking status before the transaction was made
     * @return {@code boolean} 
     */
    public boolean oldBookingStatus()
    {
	return OLD_BOOKING_STATUS;
    }

    /**
     * Gets the booking status after the transaction was made
     * @return
     */
    public boolean newBookingStatus()
    {
	return NEW_BOOKING_STATUS;
    }

    /**
     * Gets the attendance status before the transaction was made
     * @return
     */
    public boolean oldAttendanceStatus()
    {
	return OLD_ATTENDANCE_STATUS;
    }

    /**
     * Gets the attendance status after the transaction was made
     * @return
     */
    public boolean newAttendanceStatus()
    {
	return NEW_ATTENDANCE_STATUS;
    }

    /**
     * Gets the result before the transaction was made. <em>Should</em> return
     * {@code null} if the transaction is an INSERT.
     * @return a {@code String}
     */
    public String getOldResult()
    {
	return OLD_RESULT;
    }

    /**
     * Gets the result after the transaction was made. <em>Should</em> return
     * {@code null} if the transaction is an DELETE.
     * @return
     */
    public String getNewResult()
    {
	return NEW_RESULT;
    }


    public void setUpdateType(){
	if((!OLD_BOOKING_STATUS && NEW_BOOKING_STATUS) || (OLD_BOOKING_STATUS && !NEW_BOOKING_STATUS) ){
	    updateType = UpdateType.BOOKING; 
	}else if((!OLD_ATTENDANCE_STATUS && NEW_ATTENDANCE_STATUS)|| (OLD_ATTENDANCE_STATUS && !NEW_ATTENDANCE_STATUS)){
	    updateType = UpdateType.ATTENDANCE_STATUS; 
	}else if( OLD_RESULT == null && NEW_RESULT != null ){
	    updateType = UpdateType.RESULT; 
	}
    }
    @Override
    public String logDescription()
    {
	switch(getOperationType()){
	    case INSERT:
		return String.format("Student with id of %s was registered to take %s on %s ", 
			getStudentID(), getNewModuleName(), getDateAsString()); 
	    default:
		switch(updateType){
		    case ATTENDANCE_STATUS:
			return String.format("The attendance status of regID %s was updated from %s to %s on %s", 
				getRegId(), oldAttendanceStatus(), newAttendanceStatus(), getDateAsString()); 

		    case BOOKING:
			return String.format("The booking status of regID %s was updated from %s to %s on %s", 
				getRegId(), oldBookingStatus(), newBookingStatus(),  getDateAsString()); 

		    case RESULT:
			return String.format("The result of regId %s was updated from %s to %s ", REG_ID, getOldResult(), getNewResult()); 
			
		    default:
			return String.format("The regID %s was updated on %s " , REG_ID, getDateAsString()); 

		}

	}
    }



}
