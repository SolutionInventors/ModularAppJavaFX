package database.bean.student;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import database.bean.Bean;
import utils.ValidationType;

/**
 * A {@code StudentData} is used to logically group all the informations about a 
 * {@code Student} that is inputed during his registration
 * such as his {@code Biodata}, {@code EducationalBackground}(s), 
 * {@code MeanOfDiscovery}(s), {@code Phone}(s), {@code ProfessionalExperience}(s) and
 * {@code Sponsor}(s). <br>
 * This object makes it possible to retrieve all the information about a {@code Student}
 * in one object <br>
 * This object also make registration of a {@code Student} concise since all the info
 * can be contained in one object.<br>
 * Note that for a {@code StudentData} to be valid, all the attribute must contain
 * the same studentID.
 * @author Oguejiofor Chidiebere
 * @see Student
 */
public class StudentData implements Bean
{

    private static final long serialVersionUID = -4202730559200100870L;
    private final String STUDENT_ID;
    private final String highestQualificationAttained; 
    private final String currentWorkPlace; 
    private final String lastCourseRead; 
    private final String lastInstituteAttended; 
    
    private int yearsWorkingExperience;
    
    private Biodata biodata;

    private EducationalBackground[] education;
    private MeanOfDiscovery[] meansOfDiscovery;
    private Phone[] phoneNumbers;
    private ProfessionalExperience[] experiences;
    private Sponsor[] sponsors;

    /**
     * Initializes this {@code StudentData} by specifying the required data.
     * @param data the {@code Student}'s biodata
     * @param edu an array containing info about the {@code Student}'s 
     * {@code EducationalBackground}
     * @param phoneNumbers an array containing the {@code Phone}s of the student
     * @param experiences an array containing details of the places the
     * {@code Student } has worked.
     */
    public StudentData(String highestQualification, 
	    String currentWorkPlace, String courseRead,
	  
	    int yearsExperience, String lastInstituteAttended,  Biodata data, EducationalBackground[] edu,
	    Phone[] phoneNumbers, ProfessionalExperience[] experiences, 
	    MeanOfDiscovery[] meansOfDis, Sponsor[] sponsors)
    {
	STUDENT_ID = data.getStudentID();
	highestQualificationAttained = highestQualification; 
	this.currentWorkPlace = currentWorkPlace; 
	yearsWorkingExperience = yearsExperience; 
	lastCourseRead = courseRead;
	this.lastInstituteAttended = lastInstituteAttended; 
	
	setBiodata(data);
	setEducation(edu);
	setPhoneNumbers(phoneNumbers);
	setExperiences(experiences);
	setMeansOfDiscovery(meansOfDis);
	setSponsors(sponsors);
    }


    /**
     * This method validates this object by ensuring that its containing
     *  {@code EducationBackground[], MeansOfDiscovery[], Phone[], ProfessionalExperience[] and 
     *  Biodata} attribute all have thesame student id. It also calls the isValid method on all
     *  the {@code Biodata} and all the elements of the {@code EducationBackground[], MeansOfDiscovery[],
     *   Phone[] and ProfessionalExperience[] } that is contained in this object. 
     */
    @Override
    public boolean isValid(ValidationType type)
    {
	return  haveSameID()&& isSponsorValid(type) && isMeansValid(type)  && isBioValid(type) && 
		isEducationValid(type) && areExperiencesValid(type) && isPhoneValid(type);
    }
    
    /**
     * Checks if the {@code Biodata} contained in this object is valid based on a 
     * {@code ValidationType}
     * @see Biodata#isValid(ValidationType)
     * @param type the {@code ValidationType} object.
     * @return
     */
    public boolean isBioValid(ValidationType type)
    {
	return getBiodata().isValid(type);
    }


    /**
     * Validates the {@code Sponsor} array contained in this object by based on the
     * validation type 
     * @see Sponsor#isValid(ValidationType)
     * @param type  the {@code ValidationType} would be validated
     * @return
     */
    public boolean isSponsorValid(ValidationType type)
    {
	return Arrays.stream(getSponsors())
		.allMatch(s-> s.isValid(type));
    }

    /**
     * Validates each element in the {@code Phone} array contained in this object by 
     * based on the validation type 
     * @see Phone#isValid(ValidationType)
     * @param type  the {@code ValidationType} would be validated
     * @return
     */
    public boolean isPhoneValid(ValidationType type)
    {
	return Arrays.stream(getPhoneNumbers())
		.allMatch(phone->phone.isValid(type) );
    }

    /**
     * Checks if each element in the {@code ProfessionalExperience } array contained in this
     * object is valid  based on a {@code ValidationType}
     * @see {@code ProfessionalExperience#isValid(ValidationType)}
     * @param type
     * @return
     */
    public boolean areExperiencesValid(ValidationType type)
    {
	return Arrays.stream(getExperiences())
		.allMatch(exp->exp.isValid(type) );
    }


    public boolean isEducationValid(ValidationType type)
    {
	return Arrays.stream(getEducation())
		.allMatch(edu->edu.isValid(type) );
    }


    /**
     * @return
     */
    public boolean isMeansValid(ValidationType type)
    {
	return Arrays.stream(getMeansOfDiscovery()).allMatch(mean-> mean.isValid(type) );
    }

    public boolean haveSameID(){
	return isNotNull() && 
		Arrays.stream( getEducation()).allMatch(edu-> edu.getStudentId().equals(STUDENT_ID) ) &&
		Arrays.stream(getExperiences()).allMatch(exp-> exp.getStudentId().equals(STUDENT_ID)) &&
		Arrays.stream(getMeansOfDiscovery()).allMatch(m-> m.getStudentId().equals(STUDENT_ID) ) &&
		Arrays.stream(getPhoneNumbers()).allMatch(p->p.getStudentID().equals(STUDENT_ID) );
    }
    /**
     * Checks that none of this object's attributes is {@code null}
     * @return true only if there are no {@code null } values
     */
    private boolean isNotNull()
    {
	return getEducation() != null && getExperiences() != null && getBiodata() != null &&
		getMeansOfDiscovery() != null && getPhoneNumbers() != null && 
		getEducation().length > 0 && getMeansOfDiscovery().length >0 && getPhoneNumbers().length > 0;
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
	List<EducationalBackground> list = 
		Arrays.stream(education)
		.filter(e-> e != null )
		.collect(Collectors.toList());

	this.education = list.toArray(new EducationalBackground[ list.size()]);
    }


    public MeanOfDiscovery[] getMeansOfDiscovery()
    {
	return meansOfDiscovery;
    }


    public void setMeansOfDiscovery(MeanOfDiscovery[] meansOfDiscovery)
    {
	List<MeanOfDiscovery> list = 
		Arrays.stream(meansOfDiscovery)
		.filter(means-> means != null )
		.collect(Collectors.toList());
	this.meansOfDiscovery = list.toArray(new MeanOfDiscovery[ list.size()]);
    }


    public Phone[] getPhoneNumbers()
    {
	return phoneNumbers;
    }


    public void setPhoneNumbers(Phone[] phoneNumbers)
    {
	List<Phone> list = 
		Arrays.stream(phoneNumbers)
		.filter(means-> means != null )
		.collect(Collectors.toList());
	this.phoneNumbers = list.toArray( new Phone[ list.size()] );
    }


    public ProfessionalExperience[] getExperiences()
    {
	return experiences;
    }

    /**
     * This sets the sets the {@code ProfessionalExperience} stored in this object
     * to the argument supplied. It does this by first removing any {@code null} values
     * in its argument before setting the {@code ProfessionalExperience} array attribute
     * @param experiences the profesional experiences of the Student
     */
    public void setExperiences(ProfessionalExperience[] experiences)
    {
	List<ProfessionalExperience> list = 
		Arrays.stream(experiences)
		.filter(exp-> exp != null )
		.collect(Collectors.toList());
	this.experiences = list.toArray( new ProfessionalExperience[ list.size()] );
    }


    public Sponsor[] getSponsors()
    {
	return sponsors;
    }

    public void setSponsors(Sponsor[] sponsors)
    {
	if( sponsors == null )
	    sponsors = new Sponsor[0];
	List<Sponsor> list = 
		Arrays.stream(sponsors)
		.filter(s->s!=null )
		.collect(Collectors.toList());

	this.sponsors = list.toArray(new Sponsor[ list.size()]);
    }


    public int getYearsWorkingExperience()
    {
        return yearsWorkingExperience;
    }


    public void setYearsWorkingExperience(int yearsWorkingExperience)
    {
        this.yearsWorkingExperience = yearsWorkingExperience;
    }


    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }


    public String getStudentID()
    {
        return STUDENT_ID;
    }


    public String getHighestQualificationAttained()
    {
        return highestQualificationAttained;
    }


    public String getCurrentWorkPlace()
    {
        return currentWorkPlace;
    }


    public String getLastCourseRead()
    {
        return lastCourseRead;
    }


    public String getLastInstituteAttended()
    {
	return lastInstituteAttended;
    }

}
