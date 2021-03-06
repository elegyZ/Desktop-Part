package model;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Claim 
{
	private String id;
	private String policyId;
	private String userId;
	private String accLocation;
	private Date accDate;
	private float claimAmount;
	private String claimReason;
	private List<File> claimFiles;
	private String status;					//��accept, notAccept, processing, pending����
	private String rejectReason;
	private String employeeId;
	private int type;
	private Date createDate;
	private Date updateDate;
	
	public Claim(String id, String policyId, String userId, int type, String accLocation, Date accDate, String claimReason, float claimAmount, List<File> claimFiles, String status,
				 String employeeId, Date createDate, Date updateDate)
	{
		this.id = id;
		setType(type);
		setPolicyId(policyId);
		setUserId(userId);
		setAccLocation(accLocation);
		setAccDate(accDate);
		setClaimReason(claimReason);
		setClaimAmount(claimAmount);
		setClaimFiles(claimFiles);
		setStatus(status);
		this.rejectReason = "";
		setEmployeeId(employeeId);
		setCreateDate(createDate);
		setUpdateDate(updateDate);
	}
	
	//------------set methods-----------------------------------------
	public void setType(int type)
	{
		this.type = type;
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	public void setEmployeeId(String employeeId)
	{
		this.employeeId = employeeId;
	}
	
	public void setRejectReason(String rejectReason)
	{
		this.rejectReason = rejectReason;
	}
	
	public void setUpdateDate(Date updateDate)
	{
		this.updateDate = updateDate;
	}
	
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
	
	public void setPolicyId(String policyId)
	{
		this.policyId = policyId;
	}
	
	public void setAccLocation(String accLocation)
	{
		this.accLocation = accLocation;
	}
	
	public void setAccDate(Date accDate)
	{
		this.accDate = accDate;
	}
	
	public void setClaimReason(String claimReason)
	{
		this.claimReason = claimReason;
	}
	
	public void setClaimAmount(float claimAmount)
	{
		this.claimAmount = claimAmount;
	}
	
	public void setClaimFiles(List<File> claimFiles)
	{
		this.claimFiles = claimFiles;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	//------------get methods-----------------------------------
	public int getType()
	{
		return type;
	}
	
	public String getUserId()
	{
		return userId;
	}
	
	public String getEmployeeId()
	{
		return employeeId;
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getPolicyId()
	{
		return policyId;
	}
	
	public String getAccLocation()
	{
		return accLocation;
	}
	
	public Date getAccDate()
	{
		return accDate;
	}
	
	public String getClaimReason()
	{
		return claimReason;
	}
	
	public float getClaimAmount()
	{
		return claimAmount;
	}
	
	public List<File> getClaimFiles()
	{
		return claimFiles;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public Date getCreateDate()
	{
		return createDate;
	}
	
	public Date getUpdateDate()
	{
		return updateDate;
	}
	
	public String getRejectReason()
	{
		return rejectReason;
	}
	
	//--------------get String property----------------
	public String getSubmissionDateProperty(String country)
	{
		if(country.equals("Ireland"))
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.UK);
			return df.format(updateDate);
		}
		else
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
			return df.format(updateDate);
		}
	}
	
	public String getTypeProperty(int type)
	{
		if(type == 1)
			return "First Type";
		else if(type == 2)
			return "Second Type";
		else
			return "Third Type";
	}
}
