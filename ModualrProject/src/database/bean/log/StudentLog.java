package database.bean.log;

public class StudentLog
{
    private String studentId;
    private LogType operationType;
    
    public StudentLog()
    {
	// TODO Auto-generated constructor stub
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

}
