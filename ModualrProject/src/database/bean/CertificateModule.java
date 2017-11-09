package database.bean;

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
    public static boolean isValid( CertificateModule certModule)
    {
	String modName = certModule.getModuleName();
	String certName = certModule.getCertificateName();
	
	if(  ( modName != null  && certName != null && 
		Bean.hasOnlyLetters(certName))  && 
		Bean.isAlphanumeric( modName)){
	    return true;
	}
	return false;
	
	
    }
}
