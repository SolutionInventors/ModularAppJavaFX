package database.bean;

import java.sql.Date;

/**
 * 
 * @author Oguejiofor Chidiebere
 *
 */
public class ProfessionalExperience implements Bean
{
    private String studentId;
    private Date startDate;
    private Date endDate;
    private String jobTitle;
    private String employer;
    private String responsibility;

    public ProfessionalExperience(){}
    
    public ProfessionalExperience( String studId, Date startDate, Date endDate, 
	    String jobTitle, String employer, String responsibility  )
    {
	setStudId( studId);
	setStartDate(startDate);
	setEndDate(endDate);
	setJobTitle(jobTitle);
	setEmployer(employer);
	setResponsibility(responsibility);
    }
    
    public  void setStudId(String studId)
    {
	studentId =  Bean.removeExtraSpaces( studId );
    }

    public String getStudentId(){
	return studentId;
    }
    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }



    public Date getEndDate()
    {
        return endDate;
    }



    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }



    public String getJobTitle()
    {
        return jobTitle;
    }



    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = Bean.removeExtraSpaces( jobTitle);
    }



    public String getEmployer()
    {
        return employer;
    }



    public void setEmployer(String employer)
    {
        this.employer = Bean.removeExtraSpaces( employer);
    }



    public String getResponsibility()
    {
        return responsibility;
    }



    public void setResponsibility(String responsibility)
    {
        this.responsibility = Bean.removeExtraSpaces( responsibility);
    }

    @Override
    public boolean isValid(ValidationType type)
    {
	switch (type)
	{
	    case NEW_BEAN:
		return getStudentId() != null  && 
		getStudentId().length() >0 &&  checkDate() && 
		getResponsibility() != null && getJobTitle() != null;
		
	    case EXISTING_BEAN:
		return getStudentId() != null && getStudentId().length() >0;
	    default:
		break;

	}
	return false;
    }

    private boolean checkDate()
    {
	return getStartDate() != null && getEndDate() != null && 
		getStartDate().compareTo(getEndDate()) >0 ;
    }

    
}
