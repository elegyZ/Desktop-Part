package tool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
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
	public final static String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1YzlkOGQyM2UzZDE4MjZjOTE1YzJkOGIiLCJpYXQiOjE1NTUxMjIwNjYsImV4cCI6MTU1NTIwODQ2Nn0.F0MyH03JRVDVj9s6Ot-MxWveG3mcd3q9nEUbDyf5cZQ";
	
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
	
	public static Pair<Integer, String> postObject(String lastpart, String token, JSONObject object)
	{
		String urlStr = URL +lastpart;
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
		String urlStr = URL +lastpart;
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
	
	public static byte[] getFileToByte(File file) 
	{
        byte[] by = new byte[(int) file.length()];
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
            byte[] bb = new byte[2048];
            int ch;
            ch = is.read(bb);
            while (ch != -1) {
                bytestream.write(bb, 0, ch);
                ch = is.read(bb);
            }
            by = bytestream.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException("transform file into bin Array 出错",ex);
        } finally {
        	if(is != null)
				try {
					is.close();
				} catch (IOException e) {
					System.err.println("Error closing FileInputStream");
					e.printStackTrace();
				}
        }
        return by;
    }
	
	public static void main(String[] args) 
	{
		
		  Pair<Integer, String> pair = getArray("/users", TOKEN);
		  System.out.println(pair);
		 

		/*
		 * Claim claim = new Claim("claim_id", "001", null, 1, null, null, null, 0,
		 * null, "pending", null, DateTool.getCurrentDate(), DateTool.getCurrentDate());
		 * claim.setAccLocation("Beijing"); claim.setAccDate(DateTool.getCurrentDate());
		 * claim.setClaimReason("测试"); claim.setClaimAmount(Float.valueOf(250));
		 * claim.setClaimFiles(new ArrayList<File>());
		 * System.out.println("**************");
		 * System.out.println(postObject("/claims", TOKEN,
		 * ClaimTool.claimToJSONObject(claim)));
		 */
		 
		/*
		 * String username = "user"; String password = "pwd"; JSONObject jobject = new
		 * JSONObject(); jobject.put("username", username); jobject.put("password",
		 * password); System.out.println(postObject("/users/login", "",
		 * jobject).getValue());
		 */

	}
	

}
