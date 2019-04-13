package tool;

import model.User;
import net.sf.json.JSONObject;

public class UserTool 
{
	public static User user = new User("", "", "", "");
	
	public static void setUser(String userid, String username, String token, String profileId)
	{
		user = new User(userid, username, token, profileId);
	}
	
	//---------------User HTTP methods----------------------
	public static String getUserId(JSONObject jobject)
	{
		return jobject.getString("_id");
	}
	
	public static String getProfileId(JSONObject jobject)
	{
		return jobject.getString("profile");
	}
}
