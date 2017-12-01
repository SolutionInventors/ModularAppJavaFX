package database.bean.student;

import java.util.Arrays;

import database.bean.Bean;
import exception.InvalidBeanException;
import utils.ValidationType;

/**
 * This object is used to seperate a {@code Student}'s data including his biodata, educational
 * background etc from the actual {@code Student} object that stores the key details of the
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
    private Biodata biodata;

    private EducationalBackground[] education;
    private MeanOfDiscovery[] meansOfDiscovery;
    private Phone[] phoneNumbers;
    private ProfessionalExperience[] experiences;

    /**
     * 
     * @param data
     * @param edu
     * @param phoneNumbers
     * @param experiences
     */
    public StudentData(  Biodata data, EducationalBackground[] edu,
	    Phone[] phoneNumbers, ProfessionalExperience[] experiences)
    {
	setBiodata(data);
	setEducation(edu);
	setPhoneNumbers(phoneNumbers);
	setExperiences(experiences);
    }


    /**
     * This method validates this object by ensuring that its containing
     *  {@code EducationBackground[], MeansOfDiscovery[], Phone[], ProfessionalExperience[] and 
     *  Biodata} attruibute all have thesame student id. It also calls the isValid method on all
     *  the {@code Biodata} and all the elements of the {@code EducationBackground[], MeansOfDiscovery[],
     *   Phone[] and ProfessionalExperience[] } that is contained in this object. 
     */
    @Override
    public boolean isValid(ValidationType type)
    {
	/*This method validates this object by ensuring that all its instances have
	 * the same student id attribute and that they are all valid
	 */

	final String studentId = getBiodata().getStudentId();
	if( studentId == null || studentId.length() < 1 ) return false;
	switch (type)
	{

	    case NEW_BEAN:
		return Arrays.stream(getEducation()).allMatch(edu-> edu.equals(studentId) && edu.isValid(ValidationType.NEW_BEAN)) && 
			Arrays.stream(getExperiences()).allMatch(exp-> exp.equals(studentId) && exp.isValid(ValidationType.NEW_BEAN)) && 
			Arrays.stream(getMeansOfDiscovery()).allMatch(means-> means.equals(studentId) && means.isValid(ValidationType.NEW_BEAN)) && 
			Arrays.stream(getPhoneNumbers()).allMatch(phone-> phone.equals(studentId) && phone.isValid( ValidationType.NEW_BEAN)); 

	    case EXISTING_BEAN:
		return Arrays.stream(getEducation()).allMatch(edu-> edu.equals(studentId) && edu.isValid(ValidationType.EXISTING_BEAN)) && 
			Arrays.stream(getExperiences()).allMatch(exp-> exp.equals(studentId) && exp.isValid(ValidationType.EXISTING_BEAN)) && 
			Arrays.stream(getMeansOfDiscovery()).allMatch(means-> means.equals(studentId) && means.isValid(ValidationType.EXISTING_BEAN)) && 
			Arrays.stream(getPhoneNumbers()).allMatch(phone-> phone.equals(studentId) && phone.isValid( ValidationType.EXISTING_BEAN)); 


	}
	return false;
    }


    public Biodata getBiodata()
    {
	return biodata;
    }


    public void setBiodata(Biodata biodata)
    {
	this.biodata = biodata;
    }


    public EducationalBackground[] getEducation()
    {
	return education;
    }


    public void setEducation(EducationalBackground[] education)
    {
	this.education = education;
    }


    public MeanOfDiscovery[] getMeansOfDiscovery()
    {
	return meansOfDiscovery;
    }


    public void setMeansOfDiscovery(MeanOfDiscovery[] meansOfDiscovery)
    {
	this.meansOfDiscovery = meansOfDiscovery;
    }


    public Phone[] getPhoneNumbers()
    {
	return phoneNumbers;
    }


    public void setPhoneNumbers(Phone[] phoneNumbers)
    {
	this.phoneNumbers = phoneNumbers;
    }


    public ProfessionalExperience[] getExperiences()
    {
	return experiences;
    }


    public void setExperiences(ProfessionalExperience[] experiences)
    {
	this.experiences = experiences;
    }

}
