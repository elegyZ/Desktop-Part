package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import tool.DateTool;

public class Policy 
{
	private String id;					//就是保单号码
	private int plan;
	private int level;
	private Date startDate;
	private int duration;
	private Date expireDate;
	private Date createDate;
	private Date updateDate;
	private String insuredId;
	
	public Policy(String id, int plan, int level, Date startDate, int duration, Date createDate, Date updateDate, String insuredId)
	{
		this.id = id;
		setPlan(plan);
		setLevel(level);
		setStartDate(startDate);
		setDuration(duration);
		setExpireDate(startDate, duration);
		setCreateDate(createDate);
		setUpdateDate(updateDate);
		setInsuredId(insuredId);
	}

	public void setPlan(int plan)
	{
		this.plan = plan;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}
	
	public void setDuration(int duration)
	{
		this.duration = duration;
	}
	
	public void setExpireDate(Date startDate, int duration)
	{
		this.expireDate = DateTool.dateCounter(startDate, duration);
	}
	
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
	
	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}
	
	public void setInsuredId(String insuredId)
	{
		this.insuredId = insuredId;
	}
	
	public Date getCreateDate()
	{
		return createDate;
	}
	
	public Date getUpdateDate()
	{
		return updateDate;
	}
	
	public String getInsuredId()
	{
		return insuredId;
	}
	
	public String getId()
	{
		return id;
	}
	
	public int getPlan()
	{
		return plan;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public Date getStartDate()
	{
		return startDate;
	}
	
	public int getDuration()
	{
		return duration;
	}
	
	public Date getExpireDate()
	{
		return expireDate;
	}

	public String getPlanLevelProperty() 
	{
		String property = "";
		if(plan == 1)
			property += "Basic";
		else if(plan == 2)
			property += "Medium";
		else if(plan == 3)
			property += "Premium";
		else
			property += "No Plan";
		if(level == 1)
			property += "-I";
		else if(level == 2)
			property += "-II";
		else if(level == 3)
			property += "-III";
		else
			property += "No Level";
		return property;
	}

	public String getDurationProperty() 
	{
		String property = "";
		property = String.valueOf(duration) + " Month";
		if(duration > 1)
			property += "s";
		return property;
	}
	
	public String getStartEndTimeProperty()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.UK);
		return df.format(startDate) + "\n" + df.format(expireDate);
	}
	
}
