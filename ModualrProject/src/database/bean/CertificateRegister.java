package database.bean;

import utils.ValidationType;

/**
 * This object represents a single entity of a {@code CertificateRegister} in 
 * the database and can be used to add a {@code ModuleTabTable } as a requirement
 * for getting a  {@code Certificate}. Deletes and Inserts represent removing
 * a {@code ModuleTabTable} from the list of {@code Certificate } requirements and
 * adding a {@code ModuleTabTable} to the list respectively.<br><br>
 * 
 * <b>Constructors:</b><br>
 * This class contains two constructors: one with no parameters and another that
 * takes the module name and certificate. Note that the certificate name and
 * module name must be specified before a {@code CertificateRegister} can be
 * added to the database.
 * @author Oguejiofor Chidiebere
 * @see Certificate
 * @see ModuleTabTable
 *
 */
public class CertificateRegister implements Bean
{

    private static final long serialVersionUID = -4064819087006901787L;
    private String certificateName;
    private  String moduleName;

    /**
     * Initialises this {@code CertificateRegister} with the certificate name and
     * module name. This {@code CertificateRegister} can be inserted into the
     * database once this constructor is used with valid data.
     * @param certName the name of the {@code Certificate}
     * @param modName the name of the {@code ModuleTabTable}
     */
    public CertificateRegister(String certName , String modName){
	setCertificateName( certName);
	setModuleName( modName);
    }


    /**
     * Gets the certificate name stored in this {@code CertificateRegister} as
     * a {@code String}
     * @return a {@code String} containing the certificate name
     */
    public String getCertificateName()
    {
	return certificateName;
    }

    /**
     * Sets the certficate name attribute of this object by first removing any 
     * extra white space that may be found in its argument then uses the
     * resulting {@code String } to set the certificate name attrinbute of \
     * this object.
     * @param certName
     */
    public void setCertificateName(String certName)
    {
	this.certificateName = Bean.removeExtraSpaces( certName);
    }


    /**
     * Gets the name of the module name for this {@code Certificate}
     * @return  a {@code String} containing the module name 
     */
    public String getModuleName()
    {
	return moduleName;
    }


    /**
     * Removes any extra space in its argument and uses the resulting 
     * {@code String} to set the module name of this {@code CertificateRegister}
     * @param moduleName the {@code String} containing the module name
     */
    public void setModuleName(String moduleName)
    {
	this.moduleName = Bean.removeExtraSpaces( moduleName );
    }

    /**
     *Checks if the {@code ModuleTabTable} name and {@code Certificate} name of this object
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
	System.out.println( validateModuleName() );
	return validateCertificateName() && validateModuleName();
    }

    /**
     * Validates the {@code certificateName} contained in this {@code CertificateModuel}
     * The name is valid if it contains only letters. 
     * @return {@code true} if the certificate name contains only letters.
     */
    public  boolean validateCertificateName()
    {
	String certName = getCertificateName();
	return certName != null && Bean.hasOnlyLetters(certName) ;
    }


    /**
     * Checks  the format of the module name by ensuring that it is not {@code null } 
     * and that it does not contain any symbol. The module name may contain
     * alphabets and numbers only.
     * @return {@code true} if the module name is valid
     */
    public boolean validateModuleName()
    {
	String modName = getModuleName();
	return modName != null  && Bean.containsEitherAlphaNum( modName)  &&
		modName.substring(0,1).matches("[A-Za-z]");
    }


}
