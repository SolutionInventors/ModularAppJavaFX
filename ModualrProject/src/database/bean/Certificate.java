package database.bean;

import java.sql.Date;

public class Certificate
{
    private int id;
    private Date dateCreated;
    private String name;
    
    public Certificate(){}
    
    public  Certificate( int id , Date dateCreated, String name ) {
	setDateCreated( dateCreated );
	setName(name);
	setId( id );
	
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
