package database.bean.log;

import java.sql.Date;


public class CertificateLog implements Log
{
    private Date dateOfOperation;
    private String certificateName;
    private LogType operationType;
    
    public CertificateLog(){}

    public CertificateLog( Date operationDate, String certName, String type ){
	setDateOfOperation(operationDate);
	setCertificateName(certName);
	setOperationType(LogType.getLogType(type) );
    }
    
    public Date getDateOfOperation()
    {
        return dateOfOperation;
    }

    public void setDateOfOperation(Date dateOfOperation)
    {
        this.dateOfOperation = dateOfOperation;
    }

    public LogType getOperationType()
    {
	return operationType;
    }

    public void setOperationType(LogType operationType)
    {
	this.operationType = operationType;
    }

    public String getCertificateName()
    {
        return certificateName;
    }

    public void setCertificateName(String certificateName)
    {
        this.certificateName = certificateName;
    }

   

}
