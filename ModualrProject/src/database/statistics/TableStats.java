package database.statistics;

/**
 * This object contains statistics about the objects in the database. 
 * It contains informations like total Modules in database, total
 * @author Chidiebere
 *
 */
public class TableStats
{
    private final int TOTAL_NUMBER_OF_MODULES;
    private final int NUMBER_OF_MOD_REGISTERED;
    private final int NUMBER_OF_MOD_ATTENDED;
    private final int NUMBER_OF_MOD_PASSED;
    private final int NUMBER_OF_MOD_FAILED;
    
    private final int TOTAL_NUMBER_OF_STUDENTS;
    private final int NUMBER_OF_ACTIVE_STUDENTS;
    private final int NUMBER_OF_INACTIVE_STUDENTS;
    private final int NUMBER_OF_CERTIFIED_STUDENTS;
    
    private final int TOTAL_CERTIFICATES;
    private final String HIGHEST_AWARED_CERTIFICATE;
    
    private final int TOTAL_NUMBER_OF_CLASS;

    /**
     * Creates this object with data containing statistics of the database tables
     * @param totalMods the total number of modules in the database
     * @param totalModRegistered the total modules that students have registered for
     * @param modAttended total modules that have been attended
     * @param modPassed total modules that students have passed
     * @param modFailed total modules that students have failed
     * @param totalStudents total students in the database
     * @param totalActiveStud total active students in the database
     * @param totalInactiveStud total inactive students in the database
     * @param totalCertifiedStudents total certified students in the datbase
     * @param totalCertificates total certificates in the database
     * @param higestAwardedCertificate the highest certificate that has been issued 
     * @param totalClass the total number of classes that are in the database
     */
    protected TableStats( int totalMods, int totalModRegistered, int modAttended, 
	    int modPassed, int modFailed, int totalStudents, int totalActiveStud,
	    int totalInactiveStud, int totalCertifiedStudents, int totalCertificates, 
	    String higestAwardedCertificate, int totalClass)
    {
	TOTAL_NUMBER_OF_MODULES = totalMods;
	NUMBER_OF_MOD_REGISTERED = totalModRegistered;
	NUMBER_OF_MOD_ATTENDED = modAttended;
	NUMBER_OF_MOD_PASSED  =  modPassed;
	NUMBER_OF_MOD_FAILED = modFailed;
	
	TOTAL_NUMBER_OF_STUDENTS = totalStudents;
	NUMBER_OF_ACTIVE_STUDENTS = totalActiveStud;
	NUMBER_OF_INACTIVE_STUDENTS = totalInactiveStud;
	NUMBER_OF_CERTIFIED_STUDENTS = totalCertifiedStudents;
	
	TOTAL_CERTIFICATES = totalCertificates;
	HIGHEST_AWARED_CERTIFICATE = higestAwardedCertificate;
	
	TOTAL_NUMBER_OF_CLASS = totalClass;
    }


    /**
     * Returns the total modules in the database 
     * @return
     */
    public int getTotalNumberOfModules()
    {
        return TOTAL_NUMBER_OF_MODULES;
    }

    /**
     * Gets the total modules that module registration that have
     * been done by {@code Student}s
     * @return an {@code int} 
     */
    public int getNumberOfModulesRegistered()
    {
        return NUMBER_OF_MOD_REGISTERED;
    }

    
    /**
     * Gets the total Modules that {@code Student}s have attended in the database
     * @return
     */
    public int getnumberOfModulesAttended()
    {
        return NUMBER_OF_MOD_ATTENDED;
    }


    /**
     * Gets the number of registered {@code ModuleTabTable}s that the {@code Student}s have passed
     * @return an {@code int} representing the total modules passed
     */
    public int getNumberOfModulesPassed()
    {
        return NUMBER_OF_MOD_PASSED;
    }


    /**
     * Gets the total number of registered {@code ModuleTabTable}s that the {@code Student}s 
     * have failed
     * @return an{@code int}
     */
    public int getNumberOfModulesFailed()
    {
        return NUMBER_OF_MOD_FAILED;
    }

    /**
     * Returns the total number of Students in the database
     * @return 
     */
    public int getTotalNumberOfStudents()
    {
        return TOTAL_NUMBER_OF_STUDENTS;
    }

    /**
     * Gets the number of active students in the database
     * @return
     */
    public int getNumberOfActiveStudents()
    {
        return NUMBER_OF_ACTIVE_STUDENTS;
    }

    /**
     * Gets the number of inactive students in the database
     * @return
     */
    public int getNumberOfInactiveStudents()
    {
        return NUMBER_OF_INACTIVE_STUDENTS;
    }

    /**
     * Gets the number of {@code Student}s  in the database that have been certified
     * @return
     */
    public int getNumberOfCertifiedStudents()
    {
        return NUMBER_OF_CERTIFIED_STUDENTS;
    }

    /**
     * Gets the total number of certificates that exists in the ptogram
     * @return
     */
    public int getTotalNumberOfCertificates()
    {
        return TOTAL_CERTIFICATES;
    }
    
    /**
     * Gets a {@code String } containing the highest certificate that has been
     * awarded in the program
     * @return a {@code String}
     */
    public String getHighestAwardedCertificate()
    {
        return HIGHEST_AWARED_CERTIFICATE;
    }


    public int getNumberOfClasses()
    {
	return TOTAL_NUMBER_OF_CLASS;
    }
    
    
    public String toString(){
	return String.format("Highest Cerificate Awarded: %s\nTotal Number of Students: %s\n"
		+ "Number of active students: %s\n"
		+ "Number of student certified: %s\nNumber of classes: %s\n"
		+ "Number of inactive students: %s\nNumber of modules attended: %s\n"
		+ "NUmber of Modules Failed: %s\nNumber of Modules Passed: %s\n"
		+ "Nunmber of Modules Registered: %s\nNumber of Certificates: %s\n", 
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
		getTotalNumberOfCertificates());
    }
}
