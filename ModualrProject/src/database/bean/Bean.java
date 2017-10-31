package database.bean;

/**This interface is used to mark all the classes that represent a single row of a
 * table in the database. It is used extensively by a {@code DatabaseManager} 
 * @author Oguejiofor Chidiebere
 *
 */
public interface Bean{

    public static <T extends Bean >boolean isValid( T bean )
    {
	switch( bean.getClass().getSimpleName() )
	{
	    case "Admin":
		return Admin.isValid( (Admin) bean );
	    case "Phone":
		return Phone.isValid( (Phone) bean );
	    case "Module":
		return Module.isValid( (Module) bean );
	    case "ModuleStatus":
		return ModuleStatus.isValid( (ModuleStatus) bean );
	    case "Student":
		return Student.isValid( (Student) bean );
	    default :
		return false;
	}
    }
    
    
    
}
