package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import database.bean.Admin;
import database.bean.Student;

public class GetClassTest
{

    public static void main(String[] args) 
	    throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
//	Bean bean = new Admin();
	Class<?> cls = Class.forName("test.Test");
	Method m = cls.getMethod("print" );
	String[] params = null; 
	m.invoke(null ,params ); 
    }

}
