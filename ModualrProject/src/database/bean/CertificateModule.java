package database.bean;

public class CertificateModule implements Bean
{
    private int certificateId;
    private  String moduleName;
    
    public CertificateModule(){}

    public int getCertificateId()
    {
        return certificateId;
    }

    public void setCertificateId(int id)
    {
        this.certificateId = id;
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }

}
