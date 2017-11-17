package database.bean;

import java.sql.Date;

public class EducationalBackground implements Bean
{
    private Date beginDate;
    private Date endDate;
    private String institution;
    private String courseRead;
    private String qualification;
    
    public EducationalBackground(){}
    
    public EducationalBackground( Date begin, Date end, String institution,
	    String course, String qualification)
    {
	setBeginDate(begin);
	setEndDate(end);
	setInstitution(institution);
	setCourseRead(course);
	setQualification(qualification);
    }
    
    public Date getBeginDate()
    {
        return beginDate;
    }
    public void setBeginDate(Date beginDate)
    {
        this.beginDate = beginDate;
    }
    public Date getEndDate()
    {
        return endDate;
    }
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }
    public String getInstitution()
    {
        return institution;
    }
    public void setInstitution(String institution)
    {
        this.institution = institution;
    }
    public String getCourseRead()
    {
        return courseRead;
    }
    public void setCourseRead(String courseRead)
    {
        this.courseRead = courseRead;
    }
    public String getQualification()
    {
        return qualification;
    }
    public void setQualification(String qualification)
    {
        this.qualification = qualification;
    }
    
    @Override
    public boolean isValid(ValidationType type)
    {
	// TODO Auto-generated method stub
	return false;
    } 

}
