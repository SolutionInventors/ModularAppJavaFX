package test;

import java.sql.SQLException;
import java.util.Arrays;

import database.bean.ModuleRegister;
import database.managers.ModuleRegisterManager;
import utils.ModuleRegisterFilter;

public class Filtertest
{

    public static void main(String[] args) throws SQLException
    {
	ModuleRegister[] modRegs = ModuleRegisterManager.search(ModuleRegisterFilter.REG_ID,"4"); //search value
	
	System.out.println(Arrays.toString(modRegs));
	for(ModuleRegister modReg : modRegs){
	    System.out.println(modReg.getId());
	    System.out.println(modReg.getModuleName());

	    System.out.println(modReg.getStudentId());
	}

    }

}
