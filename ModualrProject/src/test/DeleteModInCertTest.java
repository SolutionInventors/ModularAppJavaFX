package test;

import java.sql.SQLException;

import database.managers.CertificateRegisterManager;
import exception.InvalidAdminException;

public class DeleteModInCertTest
{

    public static void main(String[] args) throws SQLException, InvalidAdminException
    {
	boolean success = CertificateRegisterManager.clearAllModules("Name");
	System.out.println(success);
    }

}
