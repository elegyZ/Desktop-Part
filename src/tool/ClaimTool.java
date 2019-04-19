package tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

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
	
	public static ObservableList<Claim> getClientClaimList(List<Claim> list)
	{
		ObservableList<Claim> claimData = FXCollections.observableArrayList();
		for(Claim claim:list)
			claimData.add(claim);
		return claimData;
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
		if(jobject.has("files"))
		{
			JSONArray array = jobject.getJSONArray("files");
			if (!array.isEmpty()) {
				for (int i = 0; i < array.size(); i++)
					claimFiles.add(new File((String) array.get(i)));
			}
		}		 
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
	
	public static Map<String, ContentBody> claimToMap(Claim claim)
	{
		Map<String, ContentBody> map = new HashMap<String, ContentBody>();
		map.put("type", new StringBody(String.valueOf(claim.getType()), ContentType.MULTIPART_FORM_DATA));
		map.put("insurance", new StringBody(claim.getPolicyId(), ContentType.MULTIPART_FORM_DATA));
		map.put("location", new StringBody(claim.getAccLocation(), ContentType.MULTIPART_FORM_DATA));
		map.put("date", new StringBody(DateTool.javaToMango(claim.getAccDate()), ContentType.MULTIPART_FORM_DATA));
		map.put("reason", new StringBody(claim.getClaimReason(), ContentType.MULTIPART_FORM_DATA));
		map.put("amount", new StringBody(String.valueOf(claim.getClaimAmount()), ContentType.MULTIPART_FORM_DATA));
		int i = 0;
		for(File file:claim.getClaimFiles())
		{
			map.put("file" + i, new FileBody(file));
			i++;
		}
		return map;
	}
	
	public static Pair<Integer, String> post(Claim claim)
	{
		Pair<Integer, String> reply;
		try {
			reply = HttpTool.postClaim("/claims", UserTool.user.getToken(), ClaimTool.claimToMap(claim));
		} 
		catch (ClientProtocolException e) 
		{
			e.printStackTrace();
			reply = new Pair<Integer, String>(null, e.toString());
		} catch (IOException e) 
		{
			e.printStackTrace();
			reply = new Pair<Integer, String>(null, e.toString());
		}
		return reply;
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
