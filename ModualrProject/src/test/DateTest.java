package test;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTest
{

    public static void main(String[] args) throws ParseException
    {
	DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
	Date end = new Date( df.parse("10-10-2018").getTime());
	Calendar calendar = Calendar.getInstance();
	
	Date date  = new Date( df.parse("01-10-2017").getTime());
	Date temp = new Date( df.parse("01-10-2017").getTime());
	System.out.println( date.equals(temp));
	calendar.setTime(date  );
	System.out.println(calendar.before(end));
	System.out.println(calendar.get( Calendar.YEAR));
	System.out.println(calendar.get( Calendar.MILLISECOND));
	calendar.add(Calendar.YEAR, -100);
	System.out.println(calendar.get( Calendar.YEAR));
	System.out.println(date);
	Calendar endCal  = Calendar.getInstance();
	endCal.add(Calendar.YEAR, -100); 
//	Date d  = new Date( endCal.getTime().getTime());
	System.out.println(calendar.before(endCal));
	System.out.println(calendar.get(Calendar.DATE));
	System.out.println(calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US));
    }

}
