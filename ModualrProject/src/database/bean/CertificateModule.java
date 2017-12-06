package database.bean;

import utils.ValidationType;

/**
 * This object represents a single entity of a {@code CertificateModule} in 
 * the database and can be used to add a {@code Module } as a requirement
 * for getting a  {@code Certificate}. Deletes and Inserts represent removing
 * a {@code Module} from the list of {@code Certificate } requirements and
 * adding a {@code Module} to the list respectively. <br>
 * Note that  inserts and deletes are the only transactions that can be performed on 
 * a {@code CertificateModule}<br>
 * 
 * <b>Constructors:</b><br>
 * This class contains two constructors: one with no parameters and another that
 * takes the module name and certificate. Note that the certificate name and
 * module name must be specified before a {@code CertificateModule} can be
 * added to the database.
 * @author Oguejiofor Chidiebere
 *
 */
public class CertificateModule implements Bean
{
   
    private static final long serialVersionUID = -4064819087006901787L;
    private String certificateName;
    private  String moduleName;
    
    /**
     * Initialises this {@code CertificateModule} with the certificate name and
     * module name. This {@code CertificateModule} can be inserted into the
     * database once this constructor is used
     * @param certName
     * @param modName
     */
    public CertificateModule(String certName , String modName){
	setCertificateName( certName);
	setModuleName( modName);
    }

    
    public String getCertificateName()
    {
        return certificateName;
    }

    public void setCertificateName(String certName)
    {
        this.certificateName = Bean.removeExtraSpaces( certName);
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public void setModuleName(String moduleName)
    {
        this.moduleName = Bean.removeExtraSpaces( moduleName );
    }

    /**
     *Checks if the {@code Module} name and {@code Certificate} name of this object
     *is valid. <br>
     *The module name is valid if it contains only letters or is alphanumeric with
     *a letter(s) coming first. For example<br>
     *{@code Fittings }, {@code Motor Controls , Ethics 1, Work Ethics 2} are all
     *valid for the module name but {@code 1Fitting, 2 Ethics, Ethics - 4} are invlaid
     *module name<br>
     *The Certificate name validation is more straight forward. The Certificate name must
     *contain only letters thus {@code Electro technics} ,{@code Electromechnics} is
     * are valid. But {@code Electro 1 , Electro-technics, Elect>Elect} are invalid.
     * @param certModule
     * @return
     */
    public boolean isValid( ValidationType type)
    {
	String modName = getModuleName();
	String certName = getCertificateName();
	
	if(  ( modName != null  && certName != null && 
		Bean.hasOnlyLetters(certName))  && 
		Bean.isAlphanumeric( modName)){
	    return true;
	}
	return false;
	
	
    }
}
