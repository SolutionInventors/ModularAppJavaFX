package database.bean.student;
public  class JobResponsibility
    {
	private String duty;
	private int expId;
	private int id;

	public JobResponsibility( int expId, String duty ){
	    setExpId(expId);
	    setDuty(duty);
	}
	public String getDuty()
	{
	    return duty;
	}
	public void setDuty(String duty)
	{
	    this.duty = duty;
	}

	public int getExpId()
	{
	    return expId;
	}
	public void setExpId(int expId)
	{
	    this.expId = expId;
	}
	public int getId()
	{
	    return id;
	}
	public void setId(int id)
	{
	    this.id = id;
	}
	
    }