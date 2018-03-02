package database.statistics;

import java.sql.Date;

/**
 * This object contains statistics about the objects in the database. 
 * It contains informations like total Modules in database, total
 * @author Chidiebere
 *
 */
public class TableStats
{
    private final int totalNumberOfModules;
    private final int numberOfModulesRegistered;
    private final int numberOfModulesAttended;
    private final int numberOfModulesPassed;
    private final int numberOfModulesFailed;
    
   
    private final int totalNumberOfStudents;
    private final int numberOfActiveStudents;
    private final int numberOfInactiveStudents;
    private final int numberOfCertifiedStudents;
    private final int totalStudentRegisteredThisYear;
    private final Date firstStudentRegistration; 
    private final Date lastStudentRegistration; 
    
    private final int totalNumberOfCertificates;
    private final String highestCertificateAwarded;
    private final String leastCertificateAwarded; 
    
    private final double averageStudentPerClass; 
    private final int totalNumberOfClass;

    private final String[][] individualCertStats; 
    
    
    
    /**
     * Initialises this {@code TableStats} object with the required data
     * @param totalMods total modules in the database
     * @param totalModRegistered total modules registered
     * @param modAttended total modules attended
     * @param modPassed total modules passed
     * @param modFailed total modules failed
     * @param totalStudents total students in the database
     * @param totalActiveStud total active students
     * @param totalInactiveStud total inactive students
     * @param totalCertifiedStudents total certified students
     * @param totalStudentRegisteredThisYear total students that wrere registered this year
     * @param firstStudentReg the {@code Date } in which the first student was registered
     * @param lastStudentRegistration the {@code Date} in which the last student was registered
     * @param totalCertificates the total {@code Certificate}s in th database
     * @param higestAwardedCertificate the highest Certificates awarded
     * @param leastCertAwarded the least {@code Certificate } awarded
     * @param aveStudentPerClass a {@code double} containing the average student per class
     * @param totalClass the total number of classes in the database
     * @param certsTable this is a multi dimensional {@code String } array that
     * displays the certificate name and the number of times it has been issued
     * 
     */
    protected TableStats( //mod stats
	    int totalMods, int totalModRegistered, int modAttended, 
	    int modPassed, int modFailed, 
	    //student stats
	    int totalStudents, int totalActiveStud,int totalInactiveStud, 
	    int totalCertifiedStudents,int totalStudentRegisteredThisYear, Date firstStudentReg, 
	    Date lastStudentRegistration, 
	    //cert stats
	    int totalCertificates, String higestAwardedCertificate,String leastCertAwarded, 
	    //class stats
	    double aveStudentPerClass, int totalClass, 
	    String[][] certsTable)
    {
	//setting Module stats
	totalNumberOfModules = totalMods;
	numberOfModulesRegistered = totalModRegistered;
	numberOfModulesAttended = modAttended;
	numberOfModulesPassed  =  modPassed;
	numberOfModulesFailed = modFailed;
	
	//setting student datas
	totalNumberOfStudents = totalStudents;
	numberOfActiveStudents = totalActiveStud;
	numberOfInactiveStudents = totalInactiveStud;
	numberOfCertifiedStudents = totalCertifiedStudents;
	this.totalStudentRegisteredThisYear = totalStudentRegisteredThisYear; 
	firstStudentRegistration =  firstStudentReg;
	this.lastStudentRegistration = lastStudentRegistration; 
	individualCertStats = certsTable;
		
	//setting cert stats; 
	totalNumberOfCertificates = totalCertificates;
	highestCertificateAwarded = higestAwardedCertificate;
	leastCertificateAwarded = leastCertAwarded; 
	
	//setting class stats
	averageStudentPerClass = aveStudentPerClass; 
	totalNumberOfClass = totalClass;
    }


    /**
     * Returns the total modules in the database 
     * @return
     */
    public int getTotalNumberOfModules()
    {
        return totalNumberOfModules;
    }

    /**
     * Gets the total modules that module registration that have
     * been done by {@code Student}s
     * @return an {@code int} 
     */
    public int getNumberOfModulesRegistered()
    {
        return numberOfModulesRegistered;
    }

    
    /**
     * Gets the total Modules that {@code Student}s have attended in the database
     * @return
     */
    public int getnumberOfModulesAttended()
    {
        return numberOfModulesAttended;
    }


    /**
     * Gets the number of registered {@code ModuleTabTable}s that the {@code Student}s have passed
     * @return an {@code int} representing the total modules passed
     */
    public int getNumberOfModulesPassed()
    {
        return numberOfModulesPassed;
    }


    /**
     * Gets the total number of registered {@code ModuleTabTable}s that the {@code Student}s 
     * have failed
     * @return an{@code int}
     */
    public int getNumberOfModulesFailed()
    {
        return numberOfModulesFailed;
    }

    /**
     * Returns the total number of Students in the database
     * @return 
     */
    public int getTotalNumberOfStudents()
    {
        return totalNumberOfStudents;
    }

    /**
     * Gets the number of active students in the database
     * @return
     */
    public int getNumberOfActiveStudents()
    {
        return numberOfActiveStudents;
    }

    /**
     * Gets the number of inactive students in the database
     * @return
     */
    public int getNumberOfInactiveStudents()
    {
        return numberOfInactiveStudents;
    }

    /**
     * Gets the number of {@code Student}s  in the database that have been certified
     * @return
     */
    public int getNumberOfCertifiedStudents()
    {
        return numberOfCertifiedStudents;
    }

    /**
     * Gets the total number of certificates that exists in the ptogram
     * @return
     */
    public int getTotalNumberOfCertificates()
    {
        return totalNumberOfCertificates;
    }
    
    /**
     * Gets a {@code String } containing the highest certificate that has been
     * awarded in the program
     * @return a {@code String}
     */
    public String getHighestAwardedCertificate()
    {
        return highestCertificateAwarded;
    }


    public int getNumberOfClasses()
    {
	return totalNumberOfClass;
    }
    
    
    public String toString(){
	return String.format("Highest Cerificate Awarded: %s\nTotal Number of Students: %s\n"
		+ "Number of active students: %s\n"
		+ "Number of student certified: %s\nNumber of classes: %s\n"
		+ "Number of inactive students: %s\nNumber of modules attended: %s\n"
		+ "Number of Modules Failed: %s\nNumber of Modules Passed: %s\n"
		+ "Nunmber of Modules Registered: %s\nNumber of Certificates: %s\n" + 
		"Total Student Registered this Year: %s\n " +
		"Least Certificate Awareded: %s ", 
		getHighestAwardedCertificate() ,
		getTotalNumberOfStudents(), 
		getNumberOfActiveStudents(), 
		getNumberOfCertifiedStudents(), 
		getNumberOfClasses(), 
		getNumberOfInactiveStudents(), 
		getnumberOfModulesAttended(), 
		getNumberOfModulesFailed(), 
		getNumberOfModulesPassed(), 
		getNumberOfModulesRegistered(), 
		getTotalNumberOfCertificates(), 
		getTotalStudentRegisteredThisYear(), 
		getLeastCertificateAwarded());
    }


    public int getTotalStudentRegisteredThisYear()
    {
	return totalStudentRegisteredThisYear;
    }


    public Date getFirstStudentRegistration()
    {
	return firstStudentRegistration;
    }


    public Date getLastStudentRegistration()
    {
	return lastStudentRegistration;
    }


    public String getLeastCertificateAwarded()
    {
	return leastCertificateAwarded;
    }


    public double getAverageStudentPerClass()
    {
	return averageStudentPerClass;
    }


    public String[][] getIndividualCertStats()
    {
	return individualCertStats;
    }
}
