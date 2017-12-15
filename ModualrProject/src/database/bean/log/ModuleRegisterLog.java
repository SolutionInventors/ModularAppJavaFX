package database.bean.log;

import java.sql.Date;

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

    public ModuleRegisterLog(  Date operationDate ,  String operationType,int regId ,
	    String studId, String oldModName, String newModName,
	    boolean oldBooking, boolean newBooking, boolean oldAttendance, 
	    boolean newAttendance, String oldResult, String newResult)
    {
	super( operationDate, operationType );
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
	
    }


    public int getRegId()
    {
	return REG_ID;
    }

    public String getStudentID()
    {
	return STUDENT_ID;
    }

    public String getOldModuleName()
    {
	return OLD_MODULE_NAME;
    }

    public String getNewModuleName()
    {
	return NEW_MODULE_NAME;
    }

    public boolean oldBookingStatus()
    {
	return OLD_BOOKING_STATUS;
    }

    public boolean newBookingStatus()
    {
	return NEW_BOOKING_STATUS;
    }

    public boolean oldAttendanceStatus()
    {
	return OLD_ATTENDANCE_STATUS;
    }

    public boolean newAttendanceStatus()
    {
	return NEW_ATTENDANCE_STATUS;
    }

    public String getOldResult()
    {
	return OLD_RESULT;
    }

    public String getNewResult()
    {
	return NEW_RESULT;
    }



}
