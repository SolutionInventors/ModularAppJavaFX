package database.statistics;

/**
 * This object contains statistics about a {@code Student} including the 
 * number of modules he has registered for, paid for, attended, passed and failed.
 * This object contains a protected constructor thus it can
 * only be gotten from  {@link StatisticsManager#retrieveStats(database.bean.Bean)}
 * 
 * @author Oguejifor Chidiebere
 *
 */
public class StudentStats implements Statistics
{
    private final int MODULE_REGISTERED;
    private final int MODULE_PAID;
    private final int MODULE_ATTENDED;
    private final int MODULE_PASSED;
    private final int MODULE_FAILED;
    
    /**
     * Creates this object by specifying all its attributes
     * @param modReg the number of modules the {@code Student} has registered for
     * @param modPaid the number of modules the {@code Student}  paid for
     * @param modAttended the number of modules the {@code Student} has attended
     * @param modPassed the number of {@code Module}s the {@code Student} has passed
     * @param modFailed the number of {@code Module}s the {@code Student} has failed
     */
    public StudentStats(int modReg, int modPaid, int modAttended, 
	    int modPassed, int modFailed)
    {
	MODULE_REGISTERED = modReg;
	MODULE_PAID = modPaid;
	MODULE_ATTENDED = modAttended;
	MODULE_PASSED = modPassed;
	MODULE_FAILED = modFailed;
    }

    public int getModuleRegistered()
    {
        return MODULE_REGISTERED;
    }

    public int getModulePaid()
    {
        return MODULE_PAID;
    }

    public int getModuleAttended()
    {
        return MODULE_ATTENDED;
    }

    public int getModulePassed()
    {
        return MODULE_PASSED;
    }

    public int getModuleFailed()
    {
        return MODULE_FAILED;
    }

  

}
