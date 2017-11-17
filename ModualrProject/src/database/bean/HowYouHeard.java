package database.bean;

public class HowYouHeard implements Bean
{
    private String studentId;
    private String means;
    
    public HowYouHeard(){}
    
    public HowYouHeard(String studentId, String means ){
	setStudentId(studentId);
	setMeans(means);
	
    }
    

    public String getStudentId()
    {
        return studentId;
    }

    public void setStudentId(String studentId)
    {
        this.studentId = Bean.removeExtraSpaces( studentId);
    }

    public String getMeans()
    {
        return means;
    }

    public void setMeans(String means)
    {
        this.means = Bean.removeExtraSpaces( means);
    }

    @Override
    public boolean isValid(ValidationType type)
    {
	switch (type)
	{
	    case NEW_BEAN:
	    case EXISTING_BEAN:
		return getMeans() != null && getMeans().length() >0 && 
			getStudentId() != null && getStudentId().length() > 0;
	    default:
		return false;
	}
    }

}
