package database.bean.log;

import java.sql.Date;

/**
 * A {@code Certificatelog} represents a single row in the CertificateLog 
 * table in the database. The {@code CertificatLog} table keeps track of
 * transactions that are performed on the {@code Certificate} table.<br>
 * @author Oguejiofor Chidiebere
 *
 */
public class CertificateLog extends Log
{
   private final String CERTIFICATE_NAME;
   
   /**
    * Initializes this {@code CertificateLog} by specifying the required 
    * information. Once initialized the data cannot be change
    * @param operationDate the date in which a transaction was performed
    * @param certName the {@code Certificate} name 
    * @param type contains the transaction type as a {@code String}. 
    * This should contain either "INSERT", "UPDATE" or "DELETE"
    */
    public CertificateLog( Date operationDate, String certName, String type ){
	super( operationDate, type );
	CERTIFICATE_NAME = certName;
    }
   
    public String getCertificateName()
    {
        return CERTIFICATE_NAME;
    }
}
