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


    private String getOldDetails(){
	return String.format("Id: %s; CertificateIssued: %s;  Email: %s;  ActiveStatus: %s", 
		oldStudentID(), previousCertificateIssued(), oldEmail(), prevActiveStatus() ); 
    }
    
    private String getNewDetails(){
	return String.format("Id: %s; CertificateIssued: %s;  Email: %s;  ActiveStatus: %s", 
		newStudentID(), newCertificateIssued(), newEmail(), newActiveStatus() ); 
    }
    public String oldStudentID()
    {
        return OLD_ID;
    }

    public String newStudentID()
    {
        return NEW_ID;
    }

    public String previousCertificateIssued()
    {
        return PREV_CERT_ISSUED;
    }

    public String newCertificateIssued()
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

    public String oldEmail()
    {
        return OLD_EMAIL;
    }

    public String newEmail()
    {
        return NEW_EMAIL;
    }


    @Override
    public String logDescription()
    {
	switch(getOperationType()){
	    case INSERT:
		return String.format("A new Student with Id %s was registered on %s",
			newStudentID(), getDateAsString()); 
	    default:
		return String.format("A student detail was udpated from %s to %s on %s" , 
			getOldDetails(), getNewDetails(), getDateAsString());
	    
	}
    }


}
