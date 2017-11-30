package database.bean;

public enum ValidationType
{
    /**
     * This constant is used to check if a {@code Bean} can be inserted
     * into the database. It is used with static method isValid on
     * the bean subclass
     */
    NEW_BEAN, 
    /**
     * This constant is used to check if a {@code Bean}'s format is valid. 
     * It is used to test if a {@code Bean} has the  requirements
     * for it to be deleted from the database.
     * This should be used when testing a {@code Bean} before it is
     * deleted  or to test if an existing {@code Bean} can be updated.
     * the bean subclass
     */
    EXISTING_BEAN;
}
