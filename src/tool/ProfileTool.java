package tool;

import java.util.Date;

import javafx.util.Pair;
import model.Profile;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProfileTool 
{
	//---------------User HTTP methods----------------------
	public static Profile JSONObjectToprofile(JSONObject jobject)
	{
		String id = jobject.getString("_id");
		String lastName = jobject.getString("lastname");
		String firstName = jobject.getString("firstname");
		String socialId = jobject.getString("socialId");
		String gender = jobject.getString("gender");
		int age = jobject.getInt("age");
		String country = jobject.getString("country");
		String province = jobject.getString("province");
		String city = jobject.getString("city");
		String phone = jobject.getString("phone");
		String email = jobject.getString("email");
		Date createDate = DateTool.mangoToJava(jobject.getString("createdAt"));
		Date updateDate = DateTool.mangoToJava(jobject.getString("updatedAt"));
		Profile profile = new Profile(id, lastName, firstName, socialId, gender, age, country, province, city, phone, email, createDate, updateDate);
		return profile;
	}
	
	public static JSONObject profileToJSONObject(Profile profile)
	{
		JSONObject jobject = new JSONObject();
		jobject.put("firstname", profile.getFirstName());
		jobject.put("lastname", profile.getLastName());
		jobject.put("socialId", profile.getSocialId());
		jobject.put("gender", profile.getGender());
		jobject.put("age", profile.getAge());
		jobject.put("country", profile.getCountry());
		jobject.put("province", profile.getProvince());
		jobject.put("city", profile.getCity());
		jobject.put("phone", profile.getPhone());
		jobject.put("email", profile.getEmail());
		return jobject;
	}
	
	public static JSONObject getJSONObject(String profile)
	{
		Pair<Integer, String> profileInfo = HttpTool.getObject("/profiles?_id=" + profile, UserTool.user.getToken());
		return JSONArray.fromObject(profileInfo.getValue()).getJSONObject(0);
	}
	
	public static Pair<Integer, String> postProfile(JSONObject jobject)
	{
		return HttpTool.postObject("/profiles", UserTool.user.getToken(), jobject);
	}
}
