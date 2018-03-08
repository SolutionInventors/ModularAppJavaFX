package database.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import database.managers.ConnectionManager;
import utils.ValidationType;

/**This interface is used to mark all the classes  that represent a single row of a
 * table( except the logging tables) in the database. It is used extensively by a 
 * {@code DatabaseManager}. All implementation of this interface must implement
 * method {@link #isValid(ValidationType)} that checks if this object is valid. <br>
 * This method contains some static methods that aid testing of its subclasses. These
 * methods should also be used before performing operations with the manager classes
 * 
 * @author Oguejiofor Chidiebere
 *
 */
public interface Bean extends Serializable
{

    /**
     * Checks that the {@code String} begins with a letter and has only letters and space.
     * Returns {@code true } if validation is met. 
     * @param word 
     * @return
     */
    public static boolean hasOnlyLetters( String word)
    {
	if( word.trim().matches( "[A-za-z]{1,}[A-Za-z|\\s]*") ){
	    return true;
	}
	return false;
    }

    /**
     * Checks if a phone number format passed as {@code String} is valid. It
     * does this by ensuring that the phone number contains only numbers which
     * may be prepended by a plus sign.<br>
     * For example,  +21331302 and 21331302 are both valid phone numbers
     * @param phoneNumber the phone number to validate
     * @return {@code true} if the phone number inputed is valid
     */
    public static boolean isPhoneValid( String phoneNumber ){
	if( phoneNumber == null) return false;
	if( phoneNumber.matches("[+|0-9][0-9]{1,}" ) ) return true;

	return false;
    }
  

    /**
     * Returns {@code true} if the argument contains either alphabet, numbers or
     * both. Note that spaces are  ignored
     * @param word the {@code String } to be checked
     * @return {@code true } if the word is alphanumeric 
     */
    public static boolean containsEitherAlphaNum( String word ){

	return word.trim().matches("[\\w|\\d]+[\\w|\\d|\\s]*");
    }

    
    /**
     * Removes any double  space anywhere in  a {@code String } passed as 
     * argument. This ensures that invalid {@code String } is not inputed into
     * the database. If {@code null  } is passed as argument the an empty {@code String}
     * is returned<br> 
     * For example " Some    String    "  when passed as argument returns "Some String"
     * @param string the {@code String} whose spaces would be removed 
     * @return a {@code String } with no extra spaces or an empty {@code String} if the
     * argument is {@code null }
     */
    public static String removeExtraSpaces(String string ){
	if ( string == null ) return "";
	return string.replaceAll( "\\s{2,}", " ").trim();
    }

    /**
     * This returns a {@code String} with all the words in the String Capitalized.
     * For example, if the argument is "some Unknown string" the output would be
     * "Some Unknown String"
     * @param string the {@code String} to capitalized
     * @return a {@code String} with the words capitalized
     */
    public static String capitalizeWords(String string)
    {
	if( string.length() <= 0  ) return string;
	string = removeExtraSpaces(string);
	List<String> list =  Arrays.stream( string.split( " " ) )
		.map( s-> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
		.collect( Collectors.toList());

	return String.join( " ", list.toArray( new String[list.size()] )) ;

    }
    
   

    /**
     * Truncates any extra {@code String} in its argument and returns a {@code String}	that
     * is within the specified maxLength. For example, if the {@code String} is
     * "SomeString" and the max lenth is 5 then "SomeS" is returned. This is handy when
     * enforcing the maximum {@code String}  length that  a table would provide in the
     * database.
     * @param string the containing {@code String}
     * @param maxLength the maximum length that is required. If this is above the length of 
     * the string then the string is returned.
     * @return a truncated {@code String}
     */
    public static String truncateString( String string, int maxLength ){
	int strinLength = string.length() <= maxLength ? string.length() : maxLength;
	return string.substring(0, strinLength);
    }

    /**
     * Checks the format of a {@code Bean} object based on a {@code ValidationType}.
     * {@code ValidationType } values may be NEW_BEAN or EXISTING_BEAN. The 
     * {@code ValidationType.EXISTING_BEAN} tests only the primary keys or unique values
     * in the database while {@code ValidationType.NEW_BEAN} checks that all the attributes in 
     * the object are valid
     * <br>
     * Implementations are found in the subclasses of {@code Bean } object
     * 
     * @param type the {@code ValidationType} object. If this is set to 
     * {@code  ValidationType.NEW_BEAN} then this method checks if the {@code Bean}
     * can be inserted into the database.<br>
     * If this is set to {@code  ValidationType.EXISTING_BEAN} then this method checks if the {@code Bean}
     * <em><b>CAN</b></em>   exist in the database.<br>
     * 
     * @return {@code true} when the object has valid data
     */
    public boolean isValid( ValidationType type);

    /**
     * Checks if a {@code String } passed as argument contains only 
     * numbers. Note that empty {@code String} returns {@code false}
     * @param string
     */
    public static boolean hasOnlyNumbers(String string)
    {
	return string.matches("[0-9]{1,}");

    }
    
    /**
     * Checks that the two dates are before the current date in the database and
     * that the first argument  is before the second
     * @param earlier
     * @param later
     * @return
     */
    public static boolean validateDate(Date earlier, Date later){
	Date currentDate = ConnectionManager.getCurrentDate();
	return  earlier !=null  && later != null && 
		( earlier.before(later) || earlier.equals( later ) ) && 
		( currentDate.after(earlier) || currentDate.equals(earlier)) && 
		( currentDate.after(later) || currentDate.equals(later));
    }
    
    
}
