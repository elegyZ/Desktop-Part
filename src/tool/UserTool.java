package tool;

import javafx.util.Pair;
import model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserTool 
{
	public static User user = new User();
	
	//---------------User HTTP methods----------------------
	public static Pair<Integer, String> login(String username, String password)
	{
		JSONObject userObject = new JSONObject();
		userObject.put("username", username);
		userObject.put("password", password);
		Pair<Integer, String> reply = HttpTool.postObject("/users/login", UserTool.user.getToken(), userObject);
		return reply;
	}
	
	public static Boolean initUserInfo(Pair<Integer, String> reply, String username)
	{
		JSONObject replyObject = JSONObject.fromObject(reply.getValue());
		UserTool.user.setToken(replyObject.getString("token"));
		UserTool.user.setUsername(username);
		Pair<Integer, String> userInfo = HttpTool.getObject("/users?username=" + UserTool.user.getUsername(), UserTool.user.getToken());
		JSONObject juserinfo = JSONArray.fromObject(userInfo.getValue()).getJSONObject(0);
		UserTool.user.setUserId(juserinfo.getString("_id"));
		UserTool.user.setEmployee(juserinfo.getBoolean("employee"));
		if(hasProfile(juserinfo))
		{
			UserTool.user.setProfileId(juserinfo.getString("profile"));
			return true;
		}
		return false;
	}
	
	public static Boolean hasProfile(JSONObject jobject)
	{
		return jobject.has("profile");
	}
}
