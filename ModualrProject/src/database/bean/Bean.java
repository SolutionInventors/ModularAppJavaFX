package database.bean;

/**This interface is used to mark all the classes that represent a single row of a
 * table in the database. It is used extensively by a {@code DatabaseManager} 
 * @author Oguejiofor Chidiebere
 *
 */
public interface Bean{
    
    /**
     * Checks that a {@code String} has only letters and space.
     * Returns {@code true } if validation is met
     * @param word
     * @return
     */
    public static boolean hasOnlyLetters( String word)
    {
	if( word.trim().matches( "[[A-za-z]{1,}\\s[A-Za-z]{0,}]{1,}") ){
	    return true;
	}
	return false;
    }
    
    /**
     * Checks if a phone number format passed as {@code String} is valid
     * @param phoneNumber the phone number to validate
     * @return {@code true} if the phone number inputed is valid
     */
    public static boolean isPhoneValid( String phoneNumber ){
	if( phoneNumber.matches("[+|0-9][0-9]{1,}" ) ) return true;
	
	return false;
    }
    /**
     * Checks if a {@code String } passed as its argument contains numbers and letters
     * with a letter coming first. This is used to validate class names and 
     * module names
     * @param word the {@code String } to be checked
     * @return {@code true } if the word is alphanumeric 
     */
    public static boolean isAlphanumeric( String word ){
	return word.matches("[A-Za-z]{1,}[\\s|A-Za-z|0-9]*");
    }

    /**
     * Removes any double  space anywhere in  a {@code String } passed as 
     * argument 
     * @param string the {@code String} whose spaces would be removed 
     * @return a {@code String } with no extra spaces
     */
    public static String removeExtraSpaces(String string ){
	if ( string == null ) return string;
	string = string.trim();
	while( string.matches( "\\S*\\s{2,}\\S*" ) )
	    string = string.replaceAll("  " , " " ).replaceAll("  ", " ");
	return string;
    }
   
    /**
     * This class level method checks if a {@code Bean} object is valid by calling
     * instance method isValid
     * @param bean  the {@code Bean} to check
     * @param type the {@code ValidationType}
     * @see object level method for a detailed explanation 
     * isValid( ValidationType)
     * @return {@code true} if the object is valid
     */
    public static boolean isValid( Bean bean , ValidationType type){
	return bean.isValid(type);
    }
    
    /**
     * Checks the format of a {@code Bean} object.<br>
     * Returns {@code true } if a {@code Bean } object is valid. 
     * Implementations are found in the subclasses of {@code Bean } object
     * 
     * @param type the {@code ValidationType} object. If this is set to 
     * {@code  ValidationType.NEW_BEAN} then this method checks if the {@code Bean}
     * can be inserted into the database.<br>
     * If this is set to {@code  ValidationType.EXISTING_BEAN} then this method checks if the {@code Bean}
     * <em><b>CAN</b></em>   exist in the database.<br>
     * 
     * @return
     */
    public boolean isValid( ValidationType type);
}
