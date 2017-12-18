package database.bean.student;

import java.sql.Date;

import database.bean.Bean;
import utils.ValidationType;

/**
 * This class represents a single row of the {@code EducationalBackground} table 
 * which stores the educational background of each {@code Student} in the 
 * database. The studentID attribute of this table is a foreign key.<br>
 * This class contains two constructors one with no arguments and another that 
 * declares all the required data for an {@code EducationalBackground} to be
 * added to the database.<br>
 * A {@code Student } may have multiple records in the {@code EducationalBackground}
 * table with the studentID pointing to the {@code Student } table. This is required
 * when registering a new {@code Student} into the program.<br>
 * Note that the Institution, BeginDate and StudentId attributs form a composite key for this table.
 * This means that a StudentId, BeginDate and Institution must be unique for each row in the
 * table. This ensures that a information for about one place is not inputed 
 * twice.
 * 
 * @see Student
 * @author Oguejiofor Chidiebere
 *
 */
public class EducationalBackground implements Bean
{

    private static final long serialVersionUID = -3682869931501233968L;
    /**Foreign key from the {@code Student} table. The first composite kwy*/
    private String studentID;

    private Date beginDate;

    private Date endDate;
    private String institution;
    private String courseRead;
    private String qualification;

    
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
     * Gets a {@link Date} object that contains the Date the {@code Student}
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
	this.institution = Bean.capitalizeWords( institute);
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
     * Removes any extra spaces in its argument, capitalises each word and
     * then uses the resulting {@code String} to set the course that
     * the student referenced by this object read
     * @param courseRead
     */
    public void setCourseRead(String courseRead)
    {
	this.courseRead = Bean.capitalizeWords(courseRead );

    }

    /**
     * Gets the qualification/certificate that the {@code Student} received from
     * the institute contained in this object
     * @return a {@code String} containing the qualification of the student
     */
    public String getQualification()
    {
	return qualification;
    }

    /**
     * Capitalizes each word in its  argument and uses the resulting {@code String }
     * to set the qualificcation received from the {@code Student } in the institute
     * stored in the {@code institution} attribute of this object
     * @param qualification
     */
    public void setQualification(String qualification)
    {
	this.qualification =  Bean.capitalizeWords( qualification);
    }

    /**
     * Gets the student id card number that is being referenced by this object
     * @return a {@code String }
     */
    public String getStudentId()
    {
	return studentID;
    }

    /**
     * Removes the extra spaces in its argument and uses the resulting {@code String }
     * to set the student id card number of this object
     * @param studentID the id card number of the student
     * @author Oguejiofor Chidiebere 
     */
    public void setStudentId(String studentID)
    {
	this.studentID = Bean.removeExtraSpaces( studentID).toUpperCase();
    } 

    /**
     * Checks if this object is valid by checking that the {@code studentId, courseRead and
     * institution } are not empty {@code String}. Also ensures that the dates contained
     * in this object are valid 
     * @see #validateDate()
     * @return {@code true} if this object is valid
     */
    @Override
    public boolean isValid(ValidationType type)
    {
	return getStudentId().length() >0 &&  getCourseRead().length() >0 && 
		getInstitution().length() >0  && validateDate() ;
    }

    /**
     * Validates the dates contained in this object by checking that the 
     * {@code beginDate} is before the {@code endDate} and also that neither
     * dates is greater the current date in the database.
     * @return {@code true} if the dates are valid
     */
    public boolean validateDate()
    {
	return Bean.validateDate(getBeginDate(), getEndDate());
	
    }


}
