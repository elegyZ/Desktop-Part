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
	public static Policy getPolicyObject(JSONObject jobject)
	{
		String policyId = jobject.getString("_id");
		int plan = jobject.getInt("plan");
		int level = jobject.getInt("level");
		Date startDate = DateTool.mangoToJava(jobject.getString("startDate"));													//test
		int duration = jobject.getInt("duration") / 30;
		Date createDate = DateTool.mangoToJava(jobject.getString("createdAt"));															//test
		Date updateDate = DateTool.mangoToJava(jobject.getString("updatedAt"));															//test
		String insuredId = jobject.getString("insuredId");
		String insuredName = jobject.getString("insuredName");
		String socialId = jobject.getString("socialId");
		String gender = jobject.getString("gender");
		int age = jobject.getInt("age");
		String phone = jobject.getString("phone");
		String email = jobject.getString("email");
		String bankUserName = jobject.getString("backUserName");
		String bankName = jobject.getString("bankName");
		String bankAccount = jobject.getString("bankAccount");
		Insured insured = new Insured(insuredId, insuredName, socialId, gender, age, phone, email, bankUserName, bankName, bankAccount, policyId);
		Policy policy = new Policy(policyId, plan, level, startDate, duration, createDate, updateDate, insured);
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
