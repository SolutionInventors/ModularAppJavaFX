package database.bean;

/**This interface is used to mark all the classes that represent a single row of a
 * table in the database. It is used extensively by a {@code DatabaseManager} 
 * @author Oguejiofor Chidiebere
 *
 */
public interface Bean{
    
    public static boolean hasOnlyLetters( String word)
    {
	if( word.trim().matches( "[[A-za-z]{1,}\\s[A-Za-z]{0,}]{1,}") ){
	    return true;
	}
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

    public static String removeExtraSpaces(String name ){
	name = name.trim();
	while( name.matches( "\\S*\\s{2,}\\S*" ) )
	    name =name.replaceAll("  " , " " ).replaceAll("  ", " ");
	return name;
    }
   
}
