package database.bean.student;

import database.bean.Bean;
import utils.ValidationType;

/**
 * This class represents a single entity of the {@code MeanOfDiscovery } table in
 * the database. This table stores informations of how {@code Student} got to
 * know about the program this may be from a website, a newspaper etc.
 * <br>
 * This table stores those informations and returns them via its get methods
 * @author Oguejifor Chidiebere
 *
 */
public class MeanOfDiscovery implements Bean
{
    
    private static final long serialVersionUID = 5966524786874151452L;
    
    private String studentId;
    private String means;
    
    public MeanOfDiscovery(){}
    
    /**
     * Initializes this object by setting the student id card number 
     * and the means in which the {@code Student } got to know about the
     *  modular program
     * @param studentId the Student id card number
     * @param means the means in which the student heard of the program
     * This maybe from a newspaper, the school website etc.
     */  
    public MeanOfDiscovery(String studentId, String means ){
	setStudentId(studentId);
	setMeans(means);
	
    }
    

    /**
     * Gets the Student id card number stored in this object 
     * @return a {@code  String	 } containing the Student id 
     */
    public String getStudentId()
    {
        return studentId;
    }

    /**
     * Removes extra spaces and uses the resulting {@code String } to set the
     * sutdent id card number contained in this object
     * @param studentId the {@code String} to be used to set the id
     */
    public void setStudentId(String studentId)
    {
        this.studentId = Bean.removeExtraSpaces( studentId).toUpperCase();
    }

    /**
     * Gets the means in which the {@code Student} heard about the program
     * @return a {@code String} containing the means in which the student
     * heard about the program.
     */
    public String getMeans()
    {
        return means;
    }

    /**
     * Removes any extra spaces in its argument and uses it to set the
     * means in which the {@code Student} heard about the program. This maybe
     * a newsletter, a website, from facebook etc.
     * @param means
     */
    public void setMeans(String means)
    {
        this.means = Bean.capitalizeWords( means);
    }

    /**
     * Checks that the format of this {@code MeansOfDiscovery} is valid by
     *  checking that the studentId and {@code means} attributes are not  
     *  empty {@code String}s. If the 
     * {@code ValidationType } is {@code ValidationType.EXISTING_BEAN} then only the
     * {@code studentId} is checked
     */
    @Override
    public boolean isValid(ValidationType type)
    {
	switch (type)
	{
	    case NEW_BEAN:
		return getStudentId()!= null && getStudentId().length() > 0;
	    case EXISTING_BEAN:
		return getMeans() != null && getMeans().length() >0 && 
			getStudentId() != null && getStudentId().length() > 0;
	}
	return false;
    }

}
