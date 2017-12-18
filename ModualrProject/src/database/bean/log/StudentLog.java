package database.bean.log;

import java.sql.Date;

public class StudentLog extends Log
{
    private final String OLD_ID;
    private final String NEW_ID;
    private final String PREV_CERT_ISSUED;
    private final String NEW_CERT_ISSUED;
    private final boolean PREV_ACTIVE_STATUS;
    private final boolean NEW_ACTIVE_STATUS;
    private final String OLD_EMAIL;
    private final String NEW_EMAIL;
   
    public StudentLog( 
	    Date operationDate,  TransactionType transactionType, String oldStudId,
	    String newStudId, String prevCertIssued, String newCertIssued, 
	    boolean prevActiveStatus, boolean newActiveStatus, 
	    String oldMail , String newMail )
    {
	super( operationDate, transactionType );
	OLD_ID = oldStudId;
	NEW_ID = newStudId;
	PREV_CERT_ISSUED = prevCertIssued;
	NEW_CERT_ISSUED = newCertIssued;
	PREV_ACTIVE_STATUS = prevActiveStatus;
	NEW_ACTIVE_STATUS =newActiveStatus;
	OLD_EMAIL = oldMail;
	NEW_EMAIL = newMail;
    }


    public String getOldStudentID()
    {
        return OLD_ID;
    }

    public String getNewStudentID()
    {
        return NEW_ID;
    }

    public String getPreviousCertIssued()
    {
        return PREV_CERT_ISSUED;
    }

    public String getNewCertIssued()
    {
        return NEW_CERT_ISSUED;
    }

    public boolean prevActiveStatus()
    {
        return PREV_ACTIVE_STATUS;
    }

    public boolean newActiveStatus()
    {
        return NEW_ACTIVE_STATUS;
    }

    public String getOldEmail()
    {
        return OLD_EMAIL;
    }

    public String getNewEmail()
    {
        return NEW_EMAIL;
    }


}
