package database.bean.student;

import database.bean.Bean;

/**
 * A {@code JobResponsibility} object contains a single entity in the 
 * {@code JobResponsibility} table in the database. This table stores the 
 * different roles that a {@code Student} played during a particular job. The
 * {@code JobResponsibility} table contains a foreign key that links it to the
 * {@code ProfessionalExperience} table. Note that a single entity in the 
 * {@code ProfessionalExperience} must contain at least one {@code JobResponsibility}
 * before it can be inserted into the database.<br>
 * Each {@code JobResponsibility} has a unique {@code id } attribute that is 
 * auto- incrementing in the database and can be used to reference a
 *  {@code JobResponsibilty}
 * @see ProfessionalExperience 
 * @author Chidiebere
 *
 */
public  class JobResponsibility
{
    private String role;
    private int expId;
    private int id;

    /**
     * Initializes this {@code JobResponsibility} by specifying the foreign key to
     * {@code ProfessionalExperience} table and the role that the {@code Student}
     * played while on the job
     * @param expId the link to {@code }
     * @param role
     */
    public JobResponsibility( int expId, String role ){
	setExpId(expId);
	setRole(role);
    }
    
    /**
     * Gets the role that was performed by the {@code Student} in the specified 
     * job.
     * @return a {@code String}
     */
    public String getRole()
    {
	return role;
    }
    
    /**
     * Sets the role that was played by the student in this {@code JobResponsibilty}. 
     * Unnecessary spaces is first removed from the string before it is used
     * to set the attribute.
     * @param role the role played by the student as {@code String}
     */ 
    public void setRole(String role)
    {
	this.role = Bean.removeExtraSpaces(role);
    }

    /**
     * Gets the experience id attribute that is the foreign key to the 
     * {@link ProfessionalExperience} table
     * @return a {@code int}
     */
    public int getExpId()
    {
	return expId;
    }
    /**
     * Sets the expId of this {@code JobResponsibility}
     * @param expId
     */
    public void setExpId(int expId)
    {
	this.expId = expId;
    }
    
    /**
     * Gets the {@code id} attribute of this object. The {@code id } is the primary 
     * key if the {@code JobResponsibility} table 
     * @return an {@code int}
     */
    public int getId()
    {
	return id;
    }
    /**
     * Sets the {@code id} in this {@code JobResponsibility} table
     * @param id
     */
    public void setId(int id)
    {
	this.id = id;
    }

}