package database.bean.student;

public class AspiringStudentData
{
    private  String StudentID;
    
    private EducationalBackground[] education;
    private MeanOfDiscovery[] meansOfDiscovery;
    private Phone[] phoneNumbers;
    private ProfessionalExperience[] experiences;
    private Sponsor[] sponsors;
    
    
    public String getStudentID()
    {
        return StudentID;
    }
    public void setStudentID(String studentID)
    {
        StudentID = studentID;
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
    public Sponsor[] getSponsors()
    {
        return sponsors;
    }
    public void setSponsors(Sponsor[] sponsors)
    {
        this.sponsors = sponsors;
    }
    
    
}
