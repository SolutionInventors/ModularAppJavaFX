package database.bean.student;

import java.util.Arrays;

import database.bean.Bean;
import exception.InvalidBeanException;
import utils.ValidationType;

/**
 * This object is used to seperate a {@code Student}'s data including his biodata, educational
 * background etc from the actual {@code Student} object taht stores the key details of the
 * student. This object would be returned by StudentManager when the Student data is required
 * @author Chidiebere
 *
 */
public class StudentData implements Bean
{
    /**
     * 
     */
    private static final long serialVersionUID = -4202730559200100870L;
    
    private final String STUDENT_ID;
    private final Biodata BIODATA;
    private final EducationalBackground EDUCATION;
    private final MeanOfDiscovery MEANS_OF_DISCOVERY;
    private final Phone[] PHONE_NUMBERS;
    private final ProfessionalExperience EXPERIENCE;

    public StudentData(  Biodata data, EducationalBackground edu,
	    Phone[] phoneNumbers, ProfessionalExperience experience, 
	    MeanOfDiscovery means ) throws InvalidBeanException
    {
	//Ensures that all the objects are pointing to the same student id
	if( data.getStudentId().equals( edu.getStudentId().equals(experience.getStudentId())) && 
		Arrays.stream(phoneNumbers).allMatch( p-> p.getNumber().equals(edu.getStudentId())))
	{
	    BIODATA = data;
	    EDUCATION = edu;
	    PHONE_NUMBERS = phoneNumbers;
	    EXPERIENCE = experience ;
	    MEANS_OF_DISCOVERY = means;
	    STUDENT_ID = edu.getStudentId();
	}
	throw new InvalidBeanException();

    }

    public Biodata getBiodata()
    {
	return BIODATA;
    }

    public EducationalBackground getEducation()
    {
	return EDUCATION;
    }

    public MeanOfDiscovery getMeansOfDiscovery()
    {
	return MEANS_OF_DISCOVERY;
    }

    public Phone[] getPhoneNumbers()
    {
	return PHONE_NUMBERS;
    }

    public ProfessionalExperience getExperience()
    {
	return EXPERIENCE;
    }

    public String getStudentId()
    {
	return STUDENT_ID;
    }

    @Override
    public boolean isValid(ValidationType type)
    {
	if(  getExperience().isValid(type) && getMeansOfDiscovery().isValid(type) &&
		getBiodata().isValid(type) && getEducation().isValid(type) && 
		Arrays.stream(getPhoneNumbers()).allMatch(p-> p.isValid(type) ) )	
	{
	    return true;
	}
	return false;
    }

}
