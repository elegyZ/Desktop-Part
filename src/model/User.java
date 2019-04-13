package model;

public class User 
{
	private String userId = "";
	private String username = "";
	private String token = "";
	private String profileId = "";
	
	public User(String username, String userId, String token, String profileId)
	{
		this.username = username;
		this.userId = userId;
		this.token = token;
		setProfileId(profileId);
	}
	
	public void setProfileId(String profileId)
	{
		this.profileId = profileId;
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
}
