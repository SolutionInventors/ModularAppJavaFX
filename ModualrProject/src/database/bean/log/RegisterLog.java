package database.bean.log;

import java.sql.Date;

public class RegisterLog implements Log
{
    private int regId;
    private LogType operationType;
    private Date dateOfOpertion; 
    private String studentId;
    private String moduleName;
    
    public RegisterLog( int regId, String operationType, Date operationDate ,
	    String studId, String modName )
    {
	setRegId(regId);
	setOperationType(LogType.getLogType(operationType));
	setDateOfOpertion(operationDate);
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
    public LogType getOperationType()
    {
        return operationType;
    }
    public void setOperationType(LogType operationType)
    {
        this.operationType = operationType;
    }
    public Date getDateOfOpertion()
    {
        return dateOfOpertion;
    }
    public void setDateOfOpertion(Date dateOfOpertion)
    {
        this.dateOfOpertion = dateOfOpertion;
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
