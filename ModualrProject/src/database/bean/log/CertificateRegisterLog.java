package database.bean.log;

import java.sql.Date;

public class CertificateRegisterLog extends Log
{
    private final String OLD_CERTIFICATE_NAME;
    private final String NEW_CERTIFICATE_NAME;
    private final String OLD_MODULE_NAME;
    private final String NEW_MODULE_NAME;
    
    public CertificateRegisterLog( Date dateOfOperation, String operationType, 
	    String oldCertName, String newCertName, String oldModuleName, 
	    String newModuleName)
    {
	super( dateOfOperation , operationType );
	
	OLD_CERTIFICATE_NAME = oldCertName;
	NEW_CERTIFICATE_NAME = newCertName;
	OLD_MODULE_NAME = oldModuleName;
	NEW_MODULE_NAME = newModuleName;
	
    }
    public String getOldCertificateName()
    {
        return OLD_CERTIFICATE_NAME;
    }
    public String getNewCertificateName()
    {
        return NEW_CERTIFICATE_NAME;
    }
    public String getOldModuleName()
    {
        return OLD_MODULE_NAME;
    }
    public String getNewModuleName()
    {
        return NEW_MODULE_NAME;
    }
}
