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
	private String accLocation;
	private Date accDate;
	private float claimAmount;
	private String claimReason;
	private List<File> claimFiles;
	private String status;					//”–accept, notAccept, processing, pendingÀƒ÷÷
	private String rejectReason;
	private Date createDate;
	private Date updateDate;
	
	public Claim(String id, String policyId, String accLocation, Date accDate, String claimReason, float claimAmount, List<File> claimFiles, String status,
				 Date createDate, Date updateDate)
	{
		this.id = id;
		setPolicyId(policyId);
		setAccLocation(accLocation);
		setAccDate(accDate);
		setClaimReason(claimReason);
		setClaimAmount(claimAmount);
		setClaimFiles(claimFiles);
		setStatus(status);
		this.rejectReason = "";
		setCreateDate(createDate);
		setUpdateDate(updateDate);
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
}
