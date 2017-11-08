package database.bean;

import java.sql.Date;

public class ModularClass
{
    private String name;
    private Date dateCreated;
    
    public ModularClass(){}

    /**
     * Initialises this object by setting the name of this {@code ModularClass}.
     * Should be used when inserting a new {@code ModularClass}
     * @param name
     */
    public ModularClass( String name ) {
	setName( name );
    }
    public ModularClass( String name , Date dateCreated ){
	setName( name );
	setDateCreated(dateCreated);
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
	name = name.trim();
	while( name.matches( "\\S*\\s{2,}\\S*" ) )
	    name =name.replaceAll("  " , " " ).replaceAll("  ", " ");
        this.name = name;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public static boolean isValid(ModularClass newClass)
    {
	return newClass.getName().matches("[A-Za-z]* [A-Za-z|0-9]*");
    }

    
}
