package database.bean;

/**This class represents a single row in the Phone table*/
public class Phone  implements Bean
{
    private int id;
    private int number;
    
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public int getNumber()
    {
        return number;
    }
    public void setNumber(int number)
    {
        this.number = number;
    }
    
    
}
