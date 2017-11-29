package test;

import database.bean.ProfessionalExperience;

public class ExperienceTest
{

    public static void main(String[] args)
    {
	String studId = TestUtils.getStringInput("Input Student Id: ");
	ProfessionalExperience experience = new 
		ProfessionalExperience(studId, startDate, endDate, jobTitle, employer, qualification)
    }

}
