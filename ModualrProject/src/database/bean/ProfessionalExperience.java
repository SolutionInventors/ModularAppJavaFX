package database.bean;

import java.sql.Date;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 * @author Oguejiofor Chidiebere
 *
 */
public class ProfessionalExperience implements Bean
{
    private static final long serialVersionUID = -974706030724705056L;
    private String studentId;
    private Date startDate;
    private Date endDate;
    private String jobTitle;
    private String employer;
    private String[] duties;
    
    public ProfessionalExperience(){}
    
    public ProfessionalExperience( String studId, Date startDate, Date endDate, 
	    String jobTitle, String employer, String... duties )
    {
	setStudId( studId);
	setStartDate(startDate);
	setEndDate(endDate);
	setJobTitle(jobTitle);
	setEmployer(employer);
	setDuties(duties);
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
        this.employer = Bean.removeExtraSpaces( employer.toUpperCase());
    }

    @Override
    public boolean isValid(ValidationType type)
    {
	switch (type)
	{
	    case NEW_BEAN:
		return getStudentId() != null  && getStudentId().length() >0 &&  
			checkDate() && getJobTitle() != null;
		
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
		getStartDate().before(getEndDate()) ;
    }

    public String[] getDuties()
    {
	return duties;
    }

    public void setDuties(String[] duties)
    {
	Set<String> set = Arrays.stream(duties )
			.map( duty -> duty = Bean.removeExtraSpaces(duty) )
			.collect(Collectors.toSet()) ;
	this.duties =  set.toArray( new String[set.size()]);
    }
}
