package tool;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import model.Claim;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ClaimTool 
{	
	public static ObservableList<Claim> initClaimList(List<Claim> list)
	{
		ObservableList<Claim> claimData = FXCollections.observableArrayList();
		for(Claim claim:list)
		{	
			if(claim.getStatus().equals("pending"))
				claimData.add(claim);
			else if(claim.getStatus().equals("processing") && claim.getEmployeeId().equals(UserTool.user.getUserId()))
				claimData.add(claim);
		}
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
		String policyId = jobject.getString("insurance");
		String userId = jobject.getString("user");
		int type = jobject.getInt("type");
		String accLocation = jobject.getString("location");
		Date accDate = DateTool.mangoToJava(jobject.getString("date"));	
		String claimReason = jobject.getString("reason");
		float claimAmount = (float) jobject.getDouble("amount");
		List<File> claimFiles = new ArrayList<File>();
		/*
		 * JSONArray array = jobject.getJSONArray("claimFiles"); if(!array.isEmpty()) {
		 * for(int i = 0;i < array.size();i++) claimFiles.add((File) array.get(i)); }
		 */
		String status = jobject.getString("status");
		String employeeId = jobject.has("employee") ? jobject.getString("employee") : "";
		Date createDate = DateTool.mangoToJava(jobject.getString("createdAt"));	
		Date updateDate = DateTool.mangoToJava(jobject.getString("updatedAt"));	
		Claim claim = new Claim(id, policyId, userId, type, accLocation, accDate, claimReason, claimAmount, claimFiles, status, employeeId, createDate, updateDate);
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
		/*
		 * List<File> files = claim.getClaimFiles(); List<byte[]> bytefiles = new
		 * ArrayList<byte[]>(); for(File file:files) { byte[] bytefile =
		 * HttpTool.getFileToByte(file); bytefiles.add(bytefile); }
		 */
		JSONObject jobject = new JSONObject();
		jobject.put("type", claim.getType());
		jobject.put("insurance", claim.getPolicyId());
		jobject.put("location", claim.getAccLocation());
		jobject.put("date", DateTool.javaToMango(claim.getAccDate()));
		jobject.put("reason", claim.getClaimReason());
		jobject.put("amount", claim.getClaimAmount());
		//jobject.put("file", value);
		return jobject;
	}
	
	public static Pair<Integer, String> assign(Claim claim)
	{
		return HttpTool.getObject("/claims/assign/" + claim.getId(), UserTool.user.getToken());
	}
	
	public static Pair<Integer, String> accept(Claim claim)
	{
		return HttpTool.getObject("/claims/accept/" + claim.getId(), UserTool.user.getToken());
	}
	
	public static Pair<Integer, String> reject(Claim claim, String reason)
	{
		JSONObject jobject = new JSONObject();
		jobject.put("rejectReason", reason);
		return HttpTool.postObject("/claims/reject/" + claim.getId(), UserTool.user.getToken(), jobject);
	}
}
