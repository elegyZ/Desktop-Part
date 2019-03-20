package tool;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTool 
{
	public static Date DateCounter(Date startDate, int duration)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		calendar.add(Calendar.DAY_OF_MONTH, duration);
		return calendar.getTime();
	}
}
