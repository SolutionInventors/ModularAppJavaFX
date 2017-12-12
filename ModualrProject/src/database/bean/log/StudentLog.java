package database.bean.log;

import java.sql.Date;

public class StudentLog implements Log
{
    private String studentId;
    private LogType operationType;
    private Date dateOfOperation;
   
    public StudentLog( String studId, String operationType, Date operationDate ){
	setStudentId(studId);
	setOperationType( LogType.getLogType( operationType) );
	setDateOfOperation(operationDate);
    }

    public String getStudentId()
    {
        return studentId;
    }

    public void setStudentId(String studentId)
    {
        this.studentId = studentId;
    }

    public LogType getOperationType()
    {
        return operationType;
    }

    public void setOperationType(LogType operationType)
    {
        this.operationType = operationType;
    }

    public Date getDateOfOperation()
    {
        return dateOfOperation;
    }

    public void setDateOfOperation(Date dateOfOperation)
    {
        this.dateOfOperation = dateOfOperation;
    }

}
