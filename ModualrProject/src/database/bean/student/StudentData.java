package database.bean.student;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import database.bean.Bean;
import utils.ValidationType;

/**
 * This object is used to seperate a {@code Student}'s data including his biodata, educational
 * background etc from the actual {@code Student} object that stores the key details of a
 * {@code Student}. <br>
 * This object is composed of {@link Biodata}, an array of {@link EducationalBackgoround}, an array fo
 * {@link MeansOfDiscovery}, an array of {@link Phone} and an array of {@link ProfessionalExperience}.<br>
 * The set methods for the arra attruibutes of this object removes any null values from its argument
 * before it is set.
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
    private Sponsor[] sponsors;
    /**
     * 
     * @param data
     * @param edu
     * @param phoneNumbers
     * @param experiences
     */
    public StudentData(  Biodata data, EducationalBackground[] edu,
	    Phone[] phoneNumbers, ProfessionalExperience[] experiences, 
	    MeanOfDiscovery[] meansOfDis, Sponsor[] sponsors)
    {
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

//	This ensures that all the attributes of this object have the same studentId and that the length of the
//	array attributes are valid
	
	boolean allSame =  areArraysValid() && Arrays.stream( getEducation()).allMatch(edu-> edu.getStudentId().equals(studentId) ) &&
			   Arrays.stream(getExperiences()).allMatch(exp-> exp.getStudentId().equals(studentId)) &&
			   Arrays.stream(getMeansOfDiscovery()).allMatch(m-> m.getStudentId().equals(studentId) ) &&
			   Arrays.stream(getPhoneNumbers()).allMatch(p->p.getStudentId().equals(studentId) );
	
	
	if( studentId == null || studentId.length() < 1 ) return false;
	switch (type)
	{

	    case NEW_BEAN:
		boolean isvalMean = Arrays.stream(getMeansOfDiscovery()).allMatch(mean-> mean.isValid(ValidationType.NEW_BEAN) );
		boolean isvalEdu = Arrays.stream(getEducation()).allMatch(edu->edu.isValid(ValidationType.NEW_BEAN) ) ;
		boolean isValExp = Arrays.stream(getExperiences()).allMatch(exp->exp.isValid(ValidationType.NEW_BEAN) ) ;
		boolean phoneVal = Arrays.stream(getPhoneNumbers()).allMatch(phone->phone.isValid(ValidationType.NEW_BEAN) );
		boolean isSponsVal = Arrays.stream(getSponsors()).allMatch(s-> s.isValid(ValidationType.NEW_BEAN));
		
		return  isSponsVal && allSame && isvalEdu && isvalMean && isValExp && phoneVal;
			 
	    case EXISTING_BEAN:
		return allSame && Arrays.stream(getEducation()).allMatch(edu->edu.isValid(ValidationType.EXISTING_BEAN) ) &&
		Arrays.stream(getExperiences()).allMatch(exp->exp.isValid(ValidationType.EXISTING_BEAN) ) &&
		Arrays.stream(getMeansOfDiscovery()).allMatch(mean-> mean.isValid(ValidationType.EXISTING_BEAN) ) &&
		Arrays.stream(getPhoneNumbers()).allMatch(phone->phone.isValid(ValidationType.EXISTING_BEAN) );
		

	}
	return false;
    }

    /**
     * Checks that none of this object's attributes is {@code null}
     * @return true only if there are no {@code null } values
     */
    private boolean areArraysValid()
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

}
