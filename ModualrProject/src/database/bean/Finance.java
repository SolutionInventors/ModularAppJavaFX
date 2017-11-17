package database.bean;

public class Finance
{
    private String name ;
    private String address;
    private String telephone;
    private String email;
    
    public Finance(){}
    
    public Finance( String name , String address, String phone, String mail ){
	setName( name );
	setAddress(address);
	setTelephone(phone);
	setEmail(mail);
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = Bean.removeExtraSpaces( name);
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = Bean.removeExtraSpaces( address);
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = Bean.removeExtraSpaces( telephone);
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = Bean.removeExtraSpaces( email );
    }

}
