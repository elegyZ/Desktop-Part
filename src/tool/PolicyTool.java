package tool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import model.Insured;
import model.Policy;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PolicyTool 
{
	public static ObservableList<Policy> initPolicyList(List<Policy> list)
	{
		ObservableList<Policy> policyData = FXCollections.observableArrayList();
		for(Policy policy:list)
			policyData.add(policy);
		return policyData;
	}
	
	//---------------Policy HTTP methods----------------------
	public static Boolean isClaiming(JSONObject jobject)
	{
		if(jobject.has("claim"))
			return true;
		return false;
	}
	
	public static Policy getPolicyObject(JSONObject jobject)
	{
		String policyId = jobject.getString("_id");
		int plan = jobject.getInt("plan");
		int level = jobject.getInt("level");
		Date startDate = DateTool.mangoToJava(jobject.getString("startDate"));
		int duration = jobject.getInt("duration") / 30;
		Date createDate = DateTool.mangoToJava(jobject.getString("createdAt"));
		Date updateDate = DateTool.mangoToJava(jobject.getString("updatedAt"));
		Boolean claiming = isClaiming(jobject) ? true : false;	
		JSONObject jinsured = JSONArray.fromObject(jobject.get("insured")).getJSONObject(0);
		String insuredId = jinsured.getString("_id");		
		String lastname = jinsured.getString("lastname");
		String firstname = jinsured.getString("firstname");	
		String socialId = jinsured.getString("socialId");
		String gender = jinsured.getString("gender");
		int age = jinsured.getInt("age");
		String phone = jinsured.getString("phone");
		String email = jinsured.getString("email");
		String bankName = jinsured.getString("bankName");
		String bankAccount = jinsured.getString("bankAccount");
		String bankUsername = jinsured.getString("bankUsername");
		Insured insured = new Insured(insuredId, lastname, firstname, socialId, gender, age, phone, email, bankUsername, bankName, bankAccount, policyId);
		Policy policy = new Policy(policyId, plan, level, startDate, duration, createDate, updateDate, insured, claiming);
		return policy;
	}
	
	public static List<Policy> getPolicyList(JSONArray jarray)
	{
		List<Policy> policyList = new ArrayList<Policy>();
		for(int i = 0;i < jarray.size();i++)
		{
			JSONObject jobject = jarray.getJSONObject(i);
			policyList.add(getPolicyObject(jobject));
		}
		return policyList;
	}
	
	public static Pair<Integer, String> postPolicy(JSONObject jobject)
	{
		return HttpTool.postObject("/insurances", UserTool.user.getToken(), jobject);
	}
	
	public static JSONObject policyToJSONObject(Policy policy)
	{
		JSONObject jobject = new JSONObject();
		JSONObject insured = new JSONObject();
		jobject.put("plan", policy.getPlan());
		jobject.put("level", policy.getLevel());
		jobject.put("startDate", DateTool.javaToMango(policy.getStartDate()));
		jobject.put("duration", policy.getDuration() * 30);
		jobject.put("expireDate", DateTool.javaToMango(policy.getExpireDate()));
		insured.put("lastname", policy.getInsured().getLastname());
		insured.put("firstname", policy.getInsured().getFirstname());
		insured.put("socialId", policy.getInsured().getSocialId());
		insured.put("gender", policy.getInsured().getGender());
		insured.put("age", policy.getInsured().getAge());
		insured.put("phone", policy.getInsured().getPhone());
		insured.put("email", policy.getInsured().getEmail());
		insured.put("bankName", policy.getInsured().getBankName());
		insured.put("bankAccount", policy.getInsured().getBankAccount());
		insured.put("bankUsername", policy.getInsured().getBankUserName());
		jobject.put("insured", insured);
		return jobject;
	}
}
