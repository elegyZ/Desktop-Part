package tool;

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
		return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
