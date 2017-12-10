package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.managers.ConnectionManager;

public class PreviledgeTest
{

    public static void main(String[] args) throws SQLException
    {
	String[] tableNames = getTables();

	for( int i = 0 ; i < tableNames.length ; i++ ){
	    System.out.println( tableNames[i] );
	}
    }

    public static String[] getTables() throws SQLException
    {
	ResultSet result =  null;
	List<String > list = new ArrayList<>(30);
	try(Connection conn = ConnectionManager.getInstance().getConnection();
		Statement stmt = conn.createStatement();  
		){
	    result = stmt. executeQuery("SELECT TABLE_NAME FROM information_schema.tables");


	    while( result.next()){

		list.add( result.getObject(1).toString());

	    }

	}
	finally{
	    if( result != null ) result.close();
	}
	return list.toArray( new String[ list.size()] ); 

    }


}
