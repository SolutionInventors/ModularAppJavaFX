package database.bean;

import exception.InvalidBeanException;

/**
 * This object represents a single entity of a {@code CertificateModule} in 
 * the database and can be used to add a {@code Module } as a requirement
 * for getting a  {@code Certificate}. Deletes and Inserts represent removing
 * a {@code Module} from the list of {@code Certificate } requirements and
 * adding a {@code Module} to the list respectively. <br>
 * Note that  inserts and deletes are the only transactions that can be performed on 
 * a {@code CertificateModule}
 * @author Oguejiofor Chidiebere
 *
 */
public class CertificateModule implements Bean
{
    private String certificateName;
    private  String moduleName;
    
    public CertificateModule(){}

    public String getCertificateName()
    {
        return certificateName;
    }

    public void setCertificateName(String name)
    {
        this.certificateName = name;
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }

    public static boolean isValid( CertificateModule certModule)
    {
	String modName = certModule.getModuleName();
	String certName = certModule.getCertificateName();
	
	if(  ( modName != null  && certName != null && 
		Bean.hasOnlyLetters(certName)) ){
	    return true;
	}
	
	return false;
	
	
    }
}
