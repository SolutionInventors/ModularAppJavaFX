package database.bean.student;

import java.sql.Date;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import database.bean.Bean;
import utils.ValidationType;

/**
 * A {@code ProfessionalExperience} object represents a single row in the
 * {@code ProfessionalExperience} table in the database. This table is used to 
 * recored data about the previous companies that the {@code student} has worked
 * for. The key informations like the 
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

    /**
     * Initializes this {@code ProfessionalExperience} object with the 
     * required fields. This constructor simply calls the setters for each of the
     * data that is inputed.
     * @param studId the {@code student id} of the student that is being referenced
     * @param startDate the date in which the student was employed or began the job
     * @param endDate the date the student  left the job
     * @param jobTitle the job title eg {@code intern, Manager, technician} etc.
     * @param employer the name of the company that the student worked in.
     * @param duties
     */
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

    /**
     * Sets the studentId by first removing any extra spaces
     */
    public  void setStudId(String studId)
    {
	studentId =  Bean.removeExtraSpaces( studId.toUpperCase() );
    }

    /**
     * Gets the studentID attribute referenced in this object.
     * @return
     */
    public String getStudentId(){
	return studentId;
    }
    /**
     * Gets the date the job started
     * @return 
     */
    public Date getStartDate()
    {
	return startDate;
    }

    /**
     * Sets the date the job started
     * @param startDate
     */
    public void setStartDate(Date startDate)
    {
	this.startDate = startDate;
    }


    /**
     * Gets the  date the job ended
     * @return
     */
    public Date getEndDate()
    {
	return endDate;
    }

    /**
     * Sets the date the job ended
     * @param endDate
     */
    public void setEndDate(Date endDate)
    {
	this.endDate = endDate;
    }

    /**
     * Sets the job title of in the job
     * @return
     */
    public String getJobTitle()
    {
	return jobTitle;
    }

    /**
     * Sets the job title in the job
     * @param jobTitle
     */
    public void setJobTitle(String jobTitle)
    {
	this.jobTitle = Bean.capitalizeWords( jobTitle);
    }

    /**
     * Gets the name of the company where the student worked
     * @return a {@code String}
     */
    public String getEmployer()
    {
	return employer;
    }

    /**
     * Sets the employer where the student worked
     * @param employer
     */
    public void setEmployer(String employer)
    {
	this.employer = Bean.capitalizeWords( employer.toUpperCase());
    }

    /**
     * Checks if this object is valid. If the {@code ValidationType } is 
     * {@code ValidationType.NEW_BEAN} then validates the studentID,  checks that
     * the object contains at least one duty and ensures that validates the 
     * dates contained in this object. Validates only the {@code studentID} if
     * the argument is {@code ValidationType.EXISTING_BEAN}. <br>
     * It does these validation via calls to  Student#validateStudentID(String), 
     * {@link #checkDate()} and  {@link #validateJobTitle} 
     * {@link #validateDuties}
     */
    @Override
    public boolean isValid(ValidationType type)
    {
	switch (type)
	{
	    case NEW_BEAN:
		return Student.validateStudentID(getStudentId()) &&checkDate() && 
			validateJobTitle() && getDuties() != null &&
			getDuties().length >0;
	    case EXISTING_BEAN:
		return Student.validateStudentID(getStudentId());
	}
	return false;
    }

    /**
     * Returns {@code true } if the job title is not an empty {@code String}
     * @return
     */
    public boolean validateJobTitle(){
	return getJobTitle() != null && getJobTitle().length() >0;
    }

    /**
     * Checks that the begin date is before the end date and that both dates 
     * are before the current date in the database.
     * @return {@code true} if the dates are valid
     */
    public boolean checkDate()
    {
	return Bean.validateDate(getStartDate(), getEndDate());
    }

    /**
     * Gets a {@code String} array containing the differenct roles that the
     * student played
     * @return a {@code String} array containing the different duties.roles that
     * the student played during the job.
     */
    public String[] getDuties()
    {
	return duties;
    }

    /**
     * Sets the duties by removing any duplicate {@code String} in the array passed
     * as argument.
     * @param duties
     */
    public void setDuties(String[] duties)
    {
	Set<String> set = 
		Arrays.stream(duties )
		.map( duty -> duty = Bean.removeExtraSpaces(duty) )
		.collect(Collectors.toSet()) ;
	this.duties =  set.toArray( new String[set.size()]);
    }
}
