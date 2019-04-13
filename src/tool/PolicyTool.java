package tool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
		String claimId = "123";//jobject.getString("claim");									//test
		
		JSONObject jinsured = (JSONObject) jobject.get("insured");	
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
		Policy policy = new Policy(policyId, plan, level, startDate, duration, createDate, updateDate, insured, claimId);
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
}
