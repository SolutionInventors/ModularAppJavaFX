package database.statistics;

public class CertificateStats implements Statistics
{
    private final int STUDENT_ISSUED;
    private final int MODULES_REQUIRED;
    
    /**
     * 
     * @param statIssued
     * @param modRequired
     */
    protected CertificateStats( int statIssued, int modRequired ){
	STUDENT_ISSUED = statIssued;
	MODULES_REQUIRED =  modRequired;
    }

    public int getStudentIssued()
    {
        return STUDENT_ISSUED;
    }

    public int getModulesRequired()
    {
        return MODULES_REQUIRED;
    }
    
    public String toString(){
	return String.format("Students issued: %s\n"
		+ "Modules Required: %d", getStudentIssued(), 
		getModulesRequired());
    }
}
