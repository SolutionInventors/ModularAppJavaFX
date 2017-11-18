package database.bean;


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
        this.studentId = Bean.removeExtraSpaces( studentId);
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
