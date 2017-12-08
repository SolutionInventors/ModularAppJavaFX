package database.statistics;

/**
 * This object contains information about  a particular {@code Module }. It contains
 * one constructor
 * @author Chidiebere
 *
 */
public class ModuleStats implements Statistics
{
    private final int NUM_REGISTERED;
    private final int NUM_PAID;
    private final int NUM_BOOKED;
    private final int NUM_ATTENDED;
    private final int NUM_PASSED;
    private final int NUM_FAILED;

    
    protected ModuleStats( int registered , int paid, int booked,
	    int attended, int passed , int failed ){
	NUM_REGISTERED = registered;
	NUM_PAID = paid;
	NUM_BOOKED = booked;
	NUM_ATTENDED = attended;
	NUM_PASSED = passed;
	NUM_FAILED = failed;
    }

    public int getNumRegistered()
    {
        return NUM_REGISTERED;
    }

    public int getNumPaid()
    {
        return NUM_PAID;
    }

    public int getNumBooked()
    {
        return NUM_BOOKED;
    }

    public int getNumAttended()
    {
        return NUM_ATTENDED;
    }

    public int getNumPassed()
    {
        return NUM_PASSED;
    }

    public int getNumFailed()
    {
        return NUM_FAILED;
    }
   


}
