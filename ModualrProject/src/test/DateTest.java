package test;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTest
{

    public static void main(String[] args) throws ParseException
    {
	DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
	Date begin = new Date( df.parse("22-10-2017").getTime());
	Date end = new Date( df.parse("10-10-2018").getTime());
	Calendar calendar = Calendar.getInstance();
	calendar.setTime( new Date( df.parse("22-10-2017").getTime()) );
	System.out.println(calendar.before(end));
	System.out.println(calendar.get( Calendar.DAY_OF_MONTH));
	System.out.println(calendar.get( Calendar.MILLISECOND));
	
    }

}
