package database.bean.log;

import java.sql.Date;

public class StudentLog extends Log
{
    private String studentId;
   
    public StudentLog( String studId, String operationType, Date operationDate ){
	super( operationDate, operationType );
	setStudentId(studId);
	
    }

    public String getStudentId()
    {
        return studentId;
    }

    public void setStudentId(String studentId)
    {
        this.studentId = studentId;
    }


}
