package tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTool 
{
	public static Date dateCounter(Date startDate, int duration)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		calendar.add(Calendar.MONTH, duration);
		return calendar.getTime();
	}
	
	public static Date getCurrentDate()
	{
		return Date.from(LocalDate.now().atStartOfDay(ZoneId.of("UTC")).toInstant());	
	}
	
	public static Date mangoToJava(String dateString)			//GMT
	{
		dateString = dateString.replace("Z", " UTC");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		try 
		{
			Date callbackTime = sdf.parse(dateString);
			return callbackTime;
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String javaToMango(Date date)			//GMT
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss 'UTC'");
		String dateString = sdf.format(date);
		dateString = dateString.replace(" UTC", "Z");
		return dateString;
	}
}
