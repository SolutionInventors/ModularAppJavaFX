package database.bean.student;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import database.bean.Bean;
import utils.ValidationType;

/**
 * This class represents a single row of the {@code EducationalBackground} table 
 * which stores the educational background of each {@code Student} in the 
 * database. The studentId attribute of this table is a foreign key.<br>
 * This class contains two constructors one with no arguments and another that 
 * declares all the required data for an {@code EducationalBackground} to be
 * added to the database.<br>
 * A {@code Student } may have multiple records in the {@code EducationalBackground}
 * table with the studentId pointing to the {@code Student } table. This is required
 * when registering a new {@code Student} into the program.
 * 
 * @see Student
 * @author Oguejiofor Chidiebere
 *
 */
public class EducationalBackground implements Bean
{
    /**Foreign key from the {@code Student} table*/
    private String studentId;
    private Date beginDate;
    private Date endDate;
    private String institution;
    private String courseRead;
    private String qualification;

    /**This is used to validate the the inputed dates are greater than 1917*/
    private static final Date TEST_DATE = getTestDate();

    /**
     * Initialises this object with all the required attributes set to {@code null}
     * @author Oguejiofor Chidiebere
     */
    public EducationalBackground(){}


    /**
     * Initialises this object by specifying the value of some data. 
     * Once this object is created it can be inserted into the database
     * or used to update an existing row in the {@code EducationBackground} table.
     * 
     * @author Oguejiofor Chidiebere
     * @param studId the student id card number
     * @param begin a {@link java.sql.Date} object containing the Date the student 
     * entered the specified institute
     * @param end a {@link java.sql.Date} object containing the Date the student 
     * left the specified institute
     * @param institution the institutiion that the {@code Student } attended
     * @param course the {@code Course} the {@code Student} read 
     * @param qualification the certificate the {@code Student } was issued.
     */
    public EducationalBackground( String studId, Date begin, Date end, 
	    String institution, String course, String qualification)
    {
	setStudentId(studId);
	setBeginDate(begin);
	setEndDate(end);
	setInstitution(institution);
	setCourseRead(course);
	setQualification(qualification);



    }

    /**
     * Gets a {@link java.sql.Date} object that contains the Date the {@code Student}
     * entered the institute stored in this object's  institution attribute
     * @return a {@link java.sql.Date}
     */
    public Date getBeginDate()
    {
	return beginDate;
    }

    /**
     * Sets the begin date stored in this object
     * @param beginDate a {@link java.sql.Date} containing the Date the {@code Student}
     * entered the institute
     */
    public void setBeginDate(Date beginDate)
    {
	this.beginDate = beginDate;
    }

    /**
     * Gets a {@link java.sql.Date} object that contains the date the {@code Student}
     * left the institute stored in this object's  institution attribute
     * @return a {@link java.sql.Date}
     */

    public Date getEndDate()
    {
	return endDate;
    }

    /**
     * Sets the end date stored in this object
     * @param endDate a {@link java.sql.Date} containing the Date the {@code Student}
     * entered the institute
     */

    public void setEndDate(Date endDate)
    {
	this.endDate = endDate;
    }

    /**
     * Gets the institution the {@code Student} had studied in as a {@code String}
     * 
     * @return a {@code String} containing the name of the institution
     */
    public String getInstitution()
    {
	return institution;
    }

    /**
     * Removes the {@code String } passed to it as its argument and uses it 
     * to set the institution attended by the {@code Student}.
     * If the argument is an empty {@code String } the {@code institution } 
     * attribute is set to {@code null }
     * @param institution a {@code String} containing the institution name.
     */
    public void setInstitution(String institute)
    {
	this.institution = institute != null && institute.length() > 0 ?
		Bean.removeExtraSpaces( institute): null ;
    }

    /**
     * Gets the course the {@code Student } read in the specified institute.
     * @return a {@code String}
     */
    public String getCourseRead()
    {
	return courseRead;
    }

    /**
     * Removes any extra spaces in its argument and uses it to set the 
     * course read by the {@code Student }
     * @param courseRead
     */
    public void setCourseRead(String courseRead)
    {
	this.courseRead = courseRead.length() > 0 ?
		Bean.removeExtraSpaces(courseRead ): null ;;
    }

    /**
     * Gets the qualification/certificate that the {@code Student} received from
     * the institute contained in this object
     * @return
     */
    public String getQualification()
    {
	return qualification;
    }

    /**
     * Removes the extra spaces in its argument and uses the resulting {@code String }
     * to set the qualificcation received from the {@code Student } in the institute
     * stored in the {@code institution} attribute of this object
     * @param qualification
     */
    public void setQualification(String qualification)
    {
	this.qualification =  qualification.length() > 0 ?
		Bean.removeExtraSpaces( qualification) : null ;
    }

    /**
     * Gets the student id card number that is being referenced by this object
     * @return a {@code String }
     */
    public String getStudentId()
    {
	return studentId;
    }

    /**
     * Removes the extra spaces in its argument and uses the resulting {@code String }
     * to set the student id card number of this object
     * @param qualification
     * @author Oguejiofor Chidiebere 
     */
    public void setStudentId(String studentId)
    {
	this.studentId = studentId.length() > 0 ?
		Bean.removeExtraSpaces( studentId) : null ;
    } 

    /**
     * Checks if this object is valid based on the value of this {@link ValidationType}
     * @see Bean.isValid
     * @return {@code true} if this object is valid
     */
    @Override
    public boolean isValid(ValidationType type)
    {
	if( TEST_DATE != null ){
	    return getStudentId() != null && getBeginDate() !=null  && 
		    getEndDate() != null && getCourseRead()!= null && 
		    getInstitution() != null && getBeginDate().before(getEndDate()) && 
		    TEST_DATE.before(getBeginDate()) &&  TEST_DATE.before(getEndDate()) ;
	}
	 return getStudentId() != null && getBeginDate() !=null  && 
		 getEndDate() != null && getCourseRead()!= null &&  
		 getInstitution() != null && getBeginDate().before(getEndDate()) ;

    }
    
    
    private static Date getTestDate()
    {
	DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
	try
	{
	    return  new Date( df.parse( "01-01-1917").getTime());
	}
	catch (ParseException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null ;
    }
    
}
