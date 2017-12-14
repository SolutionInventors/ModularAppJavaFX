package database.bean.log;

import java.sql.Date;

public class RegisterLog extends Log
{
    private int regId;
    private String studentId;
    private String moduleName;

    public RegisterLog( int regId, String operationType, Date operationDate ,
	    String studId, String modName )
    {
	super( operationDate, operationType );
	setRegId(regId);
	setStudentId(studId);
	setModuleName(modName);
    }

    public int getRegId()
    {
	return regId;
    }
    public void setRegId(int regId)
    {
	this.regId = regId;
    }

    public String getStudentId()
    {
	return studentId;
    }
    public void setStudentId(String studentId)
    {
	this.studentId = studentId;
    }
    public String getModuleName()
    {
	return moduleName;
    }
    public void setModuleName(String moduleName)
    {
	this.moduleName = moduleName;
    }



}
