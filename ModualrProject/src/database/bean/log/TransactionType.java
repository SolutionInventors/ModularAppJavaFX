package database.bean.log;

/**
 * This enum contains contains constants that store the different 
 * transactions that are possible namely {@code INSERT, UPDATE and DELETE }.
 * It also contains a constant {@code NONE} which is used when retrieving
 * logs from the database
 * @author Oguejiofor Chidiebere
 *
 */
public enum TransactionType
{
    INSERT, UPDATE, DELETE, NONE;
    
    /**
     * Converts a {@code String} to an {@code TransactionType}. If the {@code String}
     * is not neither  {@code "UPDATE", "DELETE" or "INSERT"} then returns 
     * {@code TransactionType.NONE}
     * @param type contains a string representing a type of transaction. This 
     * may be "INSERT", "UPDATE" or "DELETE". Returns {@code TransactionType.NONE}
     * iF an invalid input was given.
     * @return an {@code TransactionType} object based on the {@code String} passed
     * as argument. Returns TransactionType.NONE if {@code null} or an invalid
     * transaction type was passed as argument.
     */
    public static TransactionType getOPerationType(String type){
	if( type == null ) return TransactionType.NONE;
	switch( type.toUpperCase() ){
	    case "INSERT":
		return TransactionType.INSERT;
	    case "DELETE":
		return TransactionType.DELETE;
	    case "UPDATE":
		return TransactionType.UPDATE;
	}
	return TransactionType.NONE;
    }
    
    /**
     * Gets a {@code String} that can be used in a query on a logging table.
     * Returns "INSERT", "UPDATE" or "DELETE" based on this {@code TransactionType}
     * value.<br>
     * If this object is {@code TransactionType.NONE} then "%" is returned
     * @return {@code String} that can be used to query the database.
     */
    public String getSqlTypeCode(){
	switch( toString() ){
	    case "NONE":
		return "%";
	}
	return toString();
    }
}
