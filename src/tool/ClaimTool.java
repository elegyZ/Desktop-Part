package tool;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Claim;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ClaimTool 
{	
	public static ObservableList<Claim> initClaimList(List<Claim> list)
	{
		ObservableList<Claim> claimData = FXCollections.observableArrayList();
		for(Claim claim:list)
			claimData.add(claim);
		return claimData;
	}
	
	public static void refreshClaimList(ObservableList<Claim> claimData)
	{
		if(!claimData.isEmpty())
		{
			Iterator<Claim> iterator = claimData.iterator();
	        while(iterator.hasNext())
	        {
	            Claim integer = iterator.next();
	            if(!integer.getStatus().equals("pending"))
	                iterator.remove(); 
	        }
		}
	}
	
	//---------------Claim HTTP methods----------------------
	public static Claim getClaimObject(JSONObject jobject)
	{
		String id = jobject.getString("_id");
		String policyId = jobject.getString("insuranceId");
		String accLocation = jobject.getString("accLocation");
		Date accDate = DateTool.mangoToJava(jobject.getString("accDate"));	
		String claimReason = jobject.getString("claimReason");
		float claimAmount = (float) jobject.getDouble("claimAmount");
		List<File> claimFiles = new ArrayList<File>();
		JSONArray array = jobject.getJSONArray("claimFiles");
		if(!array.isEmpty())
		{
			for(int i = 0;i < array.size();i++)
				claimFiles.add((File) array.get(i));
		}
		String status = jobject.getString("accDate");
		Date createDate = DateTool.mangoToJava(jobject.getString("createdAt"));	
		Date updateDate = DateTool.mangoToJava(jobject.getString("updatedAt"));	
		Claim claim = new Claim(id, policyId, accLocation, accDate, claimReason, claimAmount, claimFiles, status, createDate, updateDate);
		return claim;
	}
	
	public static List<Claim> getClaimList(JSONArray jarray)
	{
		List<Claim> claimList = new ArrayList<Claim>();
		for(int i = 0;i < jarray.size();i++)
		{
			JSONObject jobject = jarray.getJSONObject(i);
			claimList.add(getClaimObject(jobject));
		}
		return claimList;
	}
	
	public static JSONObject claimToJSONObject(Claim claim)
	{
		JSONObject jobject = new JSONObject();
		jobject.put("_id", claim.getId());
		jobject.put("insuranceId", claim.getPolicyId());
		jobject.put("accLocation", claim.getAccLocation());
		jobject.put("accDate", DateTool.javaToMango(claim.getAccDate()));
		jobject.put("claimReason", claim.getClaimReason());
		jobject.put("claimAmount", claim.getClaimAmount());
		jobject.put("claimFiles", "test");			//test
		jobject.put("status", claim.getStatus());
		return jobject;
	}
}
