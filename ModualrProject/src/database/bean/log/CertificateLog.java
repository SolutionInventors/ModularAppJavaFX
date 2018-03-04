package database.bean.log;

import java.sql.Date;

/**
 * A {@code Certificatelog} represents a single row in the CertificateLog 
 * table in the database. The {@code CertificatLog} table keeps track of
 * transactions that are performed on the {@code Certificate} table.<br>
 * The data contained in the {@code CertificateLog} table are auto generated
 * through triggers on the {@code Certificate } table
 * @author Oguejiofor Chidiebere
 *
 */
public class CertificateLog extends Log
{
   private final String NEW_CERTIFICATE_NAME;
   
   private final String OLD_CERTIFICATE_NAME;
   
   /**
    * Initializes this {@code CertificateLog} by specifying the required 
    * information. Once initialized the data cannot be change
    * @param operationDate the date in which a transaction was performed
    * @param certName the {@code Certificate} name 
    * @param type is the type of transaction that was performed. Valid values include
    * TransactionType.INSERT, TransactionType.DELETE and TransactionType.UPDATE
    */
    public CertificateLog( Date operationDate,TransactionType type,  String newCertName, 
	    String oldCertName )
    {
	super( operationDate, type );
	NEW_CERTIFICATE_NAME = newCertName;
	OLD_CERTIFICATE_NAME = oldCertName;
    }
   
    
    /**
     * Gets the previous certificate name. If the {@code operationType} is
     * an INSERT then this returns {@code null } else returns a value
     * @return
     */
    public String getOldCertificateName()
    {
        return OLD_CERTIFICATE_NAME;
    }
    
    /**
     * Gets the new certificate name. If the {@code operationType} is
     * a DELETE then this returns {@code null } else returns a value. <br>
     * @return
     */
    public String getNewCertificateName()
    {
        return NEW_CERTIFICATE_NAME;
    }


    @Override
    public String logDescription()
    {
	switch(getOperationType()){
	    case DELETE:
		return String.format("%s was deleted on %s ", getOldCertificateName(),
			getDateAsString()); 
	    case INSERT:
		return String.format("A new certificate, %s, was created on %s ", 
			getNewCertificateName(),
			getDateAsString());
	    default:
		return String.format("%s was changed to %s on %s", getOldCertificateName(), 
			getNewCertificateName(), getDateAsString()); 
	}
    }

  
    
}
