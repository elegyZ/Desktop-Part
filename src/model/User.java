package model;

public class User 
{
	private String userId = "";
	private String username = "";
	private String token = "";
	private String profileId = "";
	private Boolean employee = false;
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setToken(String token)
	{
		this.token = token;
	}
	
	public void setProfileId(String profileId)
	{
		this.profileId = profileId;
	}
	
	public void setEmployee(Boolean employee)
	{
		this.employee = employee;
	}
	
	public String getUserId()
	{
		return userId;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getToken()
	{
		return token;
	}
	
	public String getProfileId()
	{
		return profileId;
	}
	
	public Boolean isEmployee()
	{
		return employee;
	}
}
