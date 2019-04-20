package tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import javafx.util.Pair;
import model.Claim;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HttpTool 
{
	//public final static String URL = "https://65ea4d1c-c0d7-4115-8198-1c38cf9dd578.mock.pstmn.io/";
	public final static String URL = "http://59.110.243.55:3000";
	public final static String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YzlkOGQyM2UzZDE4MjZjOTE1YzJkOGIiLCJpYXQiOjE1NTU3NjU0MTgsImV4cCI6MTU1NTg1MTgxOH0.7TWGUQCGYqfiVQg87WbXsuOQxhQINkNN8dDfpjMiwPw";
	
	public static Pair<Integer, String> getObject(String lastpart, String token)
	{
		String urlStr = URL +lastpart; 
	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    HttpGet httpGet = new HttpGet(urlStr);
	    RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(180 * 1000).setConnectionRequestTimeout(180 * 1000)		
                .setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
        //connetionTimeout：指客户端和服务器建立连接的timeout， 就是HTTP请求的三个阶段，一：建立连接；二：数据传送；三，断开连接。超时后会ConnectionTimeOutException
		//connectionRequestTimout：指从连接池获取连接的timeout
        //socketTimeout：指客户端从服务器读取数据的timeout，超出后会抛出SocketTimeOutException
        //setRedirectsEnabled允许重定向
	    httpGet.setConfig(requestConfig);
	    httpGet.setHeader("Content-Type","application/json");	   
	    httpGet.addHeader("Authorization", "Bearer " + token);
	    CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "UTF-8");
			int code = response.getStatusLine().getStatusCode();
			Pair<Integer, String> pair = new Pair<Integer, String>(code, result);
			return pair;
		} catch (ClientProtocolException e) {
			System.err.println("Problems with Http Protocol");
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			System.err.println("Parsing error");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.err.println("abnormal IO");
			e.printStackTrace();
			return null;
		} finally {
			if (response != null) {
				try {
					response.close();
					httpClient.close();
				} catch (IOException e) {
					System.err.println("Error releasing connection");
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Pair<Integer, String> getArray(String lastpart, String token)
	{
		String urlStr = URL +lastpart; 
	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    HttpGet httpGet = new HttpGet(urlStr);
	    RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(180 * 1000).setConnectionRequestTimeout(180 * 1000)		
                .setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
	    httpGet.setConfig(requestConfig);
	    httpGet.setHeader("Content-Type","application/json");	
	    httpGet.addHeader("Authorization", "Bearer " + token);
	    CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "UTF-8");
			int code = response.getStatusLine().getStatusCode();
			Pair<Integer, String> pair = new Pair<Integer, String>(code, result);
			return pair;
		} catch (ClientProtocolException e) {
			System.err.println("Problems with Http Protocol");
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			System.err.println("Parsing error");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.err.println("abnormal IO");
			e.printStackTrace();
			return null;
		} finally {
			if (null != response) {
				try {
					response.close();
					httpClient.close();
				} catch (IOException e) {
					System.err.println("Error releasing connection");
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Pair<Integer, String> postClaim(String lastpart, String token, Map<String,ContentBody> reqParam) throws ClientProtocolException, IOException
	{
		String urlStr = URL +lastpart;
        CloseableHttpClient httpClient = HttpClients.createDefault(); 
        HttpPost httpPost = new HttpPost(urlStr);
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(180 * 1000).setConnectionRequestTimeout(180 * 1000)		
                .setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();  
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Authorization", "Bearer " + token);
        CloseableHttpResponse response = null;
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        for(Entry<String,ContentBody> param : reqParam.entrySet())
        	multipartEntityBuilder.addPart(param.getKey(), param.getValue());
        HttpEntity reqEntity = multipartEntityBuilder.build();
        httpPost.setEntity(reqEntity);
        response = httpClient.execute(httpPost);            
        try 
        {  		
			String entity = EntityUtils.toString(response.getEntity()); 
			int code = response.getStatusLine().getStatusCode();
			System.out.println("code:" + response.getStatusLine().getStatusCode()); // test
			System.out.println("response" + response.getStatusLine().toString()); // 判断错误抛出异常alert？
			Pair<Integer, String> pair = new Pair<Integer, String>(code, entity);
			return pair;
        }catch (ClientProtocolException e) {
			System.err.println("Problems with Http Protocol");
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			System.err.println("Parsing error");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.err.println("abnormal IO");
			e.printStackTrace();
			return null;
		} finally {
			if (null != response) {
				try {
					response.close();
					httpClient.close();
				} catch (IOException e) {
					System.err.println("Error releasing connection");
					e.printStackTrace();
				}
			}
		}
    }
	
	public static Pair<Integer, String> postObject(String lastpart, String token, JSONObject object)
	{
		String urlStr = URL + lastpart;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(urlStr);
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(180 * 1000).setConnectionRequestTimeout(180 * 1000)		
                .setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type","application/json");
        httpPost.addHeader("Authorization", "Bearer " + token);
        CloseableHttpResponse response = null;
		try {
			httpPost.setEntity(new StringEntity(object.toString(), ContentType.create("application/json", "utf-8")));
			System.out.println("request parameters" + EntityUtils.toString(httpPost.getEntity())); // test
			response = httpClient.execute(httpPost);
			String entity = EntityUtils.toString(response.getEntity());
			int code = response.getStatusLine().getStatusCode();
			System.out.println("code:" + response.getStatusLine().getStatusCode()); // test
			System.out.println("response" + response.getStatusLine().toString()); // 判断错误抛出异常alert？
			Pair<Integer, String> pair = new Pair<Integer, String>(code, entity);
			return pair;
		} catch (ClientProtocolException e) {
			System.err.println("Problems with Http Protocol");
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			System.err.println("Parsing error");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.err.println("abnormal IO");
			e.printStackTrace();
			return null;
		} finally {
			if (null != response) {
				try {
					response.close();
					httpClient.close();
				} catch (IOException e) {
					System.err.println("Error releasing connection");
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Pair<Integer, String> postArray(String lastpart, String token, JSONArray array)
	{
		String urlStr = URL + lastpart;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(urlStr);
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(180 * 1000).setConnectionRequestTimeout(180 * 1000)		
                .setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();         
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type","application/json");
        httpPost.addHeader("Authorization", "Bearer " + token);
        CloseableHttpResponse response = null;
        try 
        {
            httpPost.setEntity(new StringEntity(array.toString(),ContentType.create("application/json", "utf-8")));
            System.out.println("request parameters" + EntityUtils.toString(httpPost.getEntity())); // test
			response = httpClient.execute(httpPost);
			String entity = EntityUtils.toString(response.getEntity());
			int code = response.getStatusLine().getStatusCode();
			System.out.println("code:" + response.getStatusLine().getStatusCode()); // test
			System.out.println("response" + response.getStatusLine().toString()); // 判断错误抛出异常alert？
			Pair<Integer, String> pair = new Pair<Integer, String>(code, entity);
			return pair;
        } 
		catch (ClientProtocolException e) {
			System.err.println("Problems with Http Protocol");
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			System.err.println("Parsing error");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.err.println("abnormal IO");
			e.printStackTrace();
			return null;
		} finally {
			if (null != response) {
				try {
					response.close();
					httpClient.close();
				} catch (IOException e) {
					System.err.println("Error releasing connection");
					e.printStackTrace();
				}
			}
		}
	}

    public static Pair<Integer, String> download(String lastpart, String token, String fileSaveName, String root) 
    {  
    	String urlStr = URL + lastpart;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(urlStr);
	    RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(180 * 1000).setConnectionRequestTimeout(180 * 1000)		
                .setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
	    httpGet.setConfig(requestConfig);
	    httpGet.setHeader("Content-Type","application/json");	
	    httpGet.addHeader("Authorization", "Bearer " + token);
	    CloseableHttpResponse response = null;
	    int cache = 10 * 1024;
        try 
        {  
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			int code = response.getStatusLine().getStatusCode();
			String result = "";
			if(code == 200)
			{
	            InputStream is = entity.getContent();  
	            String fileSavePath = getFileSavePath(root, fileSaveName);  
	            File file = new File(fileSavePath);  
	            file.getParentFile().mkdirs();  
	            FileOutputStream fileout = new FileOutputStream(file);  
	            /** 
	             * 根据实际运行效果 设置缓冲区大小 
	             */  
	            byte[] buffer=new byte[cache];  
	            int ch = 0;  
	            while ((ch = is.read(buffer)) != -1) {  
	                fileout.write(buffer,0,ch);  
	            }  
	            result = EntityUtils.toString(entity, "UTF-8");
	            is.close();  
	            fileout.flush();  
	            fileout.close();
			}
			Pair<Integer, String> pair = new Pair<Integer, String>(code, result);
			return pair;
        } catch (Exception e) {  
            e.printStackTrace();  
            return new Pair<Integer, String>(404,e.toString());
        }    
    }  	

    public static String getFileSavePath(String root, String fileSaveName) 
    {  
        String filepath = root + "\\"; 
        filepath += fileSaveName;
        return filepath;  
    }
    
	public static void main(String[] args) 
	{
//		Pair<Integer, String> reply = HttpTool.getArray("/claims", TOKEN);
//		JSONArray jarray = JSONArray.fromObject(reply.getValue());
//		for(int i = 0;i < jarray.size();i++)
//			System.out.println(JSONObject.fromObject(jarray.get(i)).getString("files") + "\n" + JSONObject.fromObject(jarray.get(i)));
		
		List<Claim> claimData = new ArrayList<Claim>();
		Pair<Integer, String> reply = HttpTool.getArray("/claims?_id=5cb9c374b19d9b195023a587", TOKEN);
		if(reply.getKey().equals(200))
		{
			JSONArray jarray = JSONArray.fromObject(reply.getValue());
			claimData = ClaimTool.getClaimList(jarray);
		}	
		System.out.println(claimData.get(0).getClaimFiles().get(0).toString());		///res/claim-files/:claimId/:filename
		//download("/res/claim-files/5cb9c374b19d9b195023a587/1555678068107_test.txt", TOKEN, claimData.get(0).getClaimFiles().get(0).toString());


//		String username = "user";
//		String password = "pwd";
//		JSONObject jobject = new JSONObject();
//		jobject.put("username", username);
//		jobject.put("password", password);
//		System.out.println(postObject("/users/login", "", jobject).getValue());

	}
	

}
