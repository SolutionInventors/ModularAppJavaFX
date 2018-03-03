package database.bean.log;

import java.sql.Date;

/**
 * The {@code CertificateRegisterLog} table keeps track of transactions 
 * performed on the {@code CertificateRegister} table in the database. <br>
 * These informations are auto generated via triggers in the 
 * {@code CertificateRegister} table. <br>
 * A {@code CertificateRegisterLog} object is immutable.
 * @author Oguejiofor Chidiebere
 *
 */
public class CertificateRegisterLog extends Log
{
    private final String OLD_CERTIFICATE_NAME;
    private final String NEW_CERTIFICATE_NAME;
    private final String OLD_MODULE_NAME;
    private final String NEW_MODULE_NAME;

    /**
     * Initializes this {@code CertificateRegisterLog } with the required field.
     * These data are only accurate when retrieved from the 
     * {@link database.managers.LogManager#getLog(Class, TransactionType, int)}.
     * Once initialized there is no way to change any of the values 
     * 
     * @param dateOfOperation the date the operation took place
     * @param type is the type of transaction that was performed. Valid values include
     * TransactionType.INSERT, TransactionType.DELETE and TransactionType.UPDATE
     * @param oldCertName the name of the certificate begfore the transaction
     * @param newCertName the name of the certificate after the transaction
     * @param oldModuleName the module name befor the transaction
     * @param newModuleName the module name after the transaction
     */
    public CertificateRegisterLog( Date dateOfOperation, TransactionType transactionType, 
	    String oldCertName, String newCertName, String oldModuleName, 
	    String newModuleName)
    {
	super( dateOfOperation , transactionType );

	OLD_CERTIFICATE_NAME = oldCertName;
	NEW_CERTIFICATE_NAME = newCertName;
	OLD_MODULE_NAME = oldModuleName;
	NEW_MODULE_NAME = newModuleName;

    }

    /**
     * Gets the name of the {@code Certificate} before the transaction/ This is {@code null}
     * for inserts
     * @return a {@code String } 
     */
    public String getOldCertificateName()
    {
	return OLD_CERTIFICATE_NAME;
    }
    /**
     * Gets the name of the {@code Certificate} after the transaction. This is {@code null}
     * for deletes
     * @return {@code String}
     */
    public String getNewCertificateName()
    {
	return NEW_CERTIFICATE_NAME;
    }

    /**
     * Gets the module name before the transaction. This is {@code null} for inserts
     * @return
     */
    public String getOldModuleName()
    {
	return OLD_MODULE_NAME;
    }

    /**
     * Gets the module name after the transaction. This is {@code null} for deletes
     * @return
     */
    public String getNewModuleName()
    {
	return NEW_MODULE_NAME;
    }

    @Override
    public String logDescription()
    {
	switch(getOperationType()){

	    case DELETE:
		return String.format("%s was removed from module required for  %s", 
			getOldModuleName(), getOldCertificateName()); 
	    default:
		return String.format("%s was added to module required for %s", 
			getNewModuleName(), getNewCertificateName()); 

	}
    }

}
