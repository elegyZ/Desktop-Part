package tool;

import java.io.File;
import java.io.IOException;
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
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import model.Claim;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HttpTool 
{
	public final static String URL = "https://65ea4d1c-c0d7-4115-8198-1c38cf9dd578.mock.pstmn.io/";
	
	public static JSONObject getObject(String lastpart)
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
	    CloseableHttpResponse response = null;
	    try 
	    {
	        response = httpClient.execute(httpGet);
	        HttpEntity entity = response.getEntity();
	        String result = EntityUtils.toString(entity, "UTF-8");
	        JSONObject object = JSONObject.fromObject(result);
			return object;
	    } 
	    catch (ClientProtocolException e) 
	    {
	        System.err.println("Problems with Http Protocol");
	        e.printStackTrace();
	        return null;
	    } 
	    catch (ParseException e) 
	    {
	        System.err.println("Parsing error");
	        e.printStackTrace();
	        return null;
	    } 
	    catch (IOException e) 
	    {
	        System.err.println("abnormal IO");
	        e.printStackTrace();
	        return null;
	    } 
	    finally 
	    {
	        if (null != response) 
	        {
	            try 
	            {
	                response.close();
	                httpClient.close();
	            } 
	            catch (IOException e)
	            {
	                System.err.println("Error releasing connection");
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	public static JSONArray getArray(String lastpart)
	{
		String urlStr = URL +lastpart; 
	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    HttpGet httpGet = new HttpGet(urlStr);
	    RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(180 * 1000).setConnectionRequestTimeout(180 * 1000)		
                .setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
	    httpGet.setConfig(requestConfig);
	    httpGet.setHeader("Content-Type","application/json");	    
	    CloseableHttpResponse response = null;
	    try 
	    {
	        response = httpClient.execute(httpGet);
	        HttpEntity entity = response.getEntity();
	        String result = EntityUtils.toString(entity, "UTF-8");
	        JSONArray array = JSONArray.fromObject(result);
			return array;
	    } 
	    catch (ClientProtocolException e) 
	    {
	        System.err.println("Problems with Http Protocol");
	        e.printStackTrace();
	        return null;
	    } 
	    catch (ParseException e) 
	    {
	        System.err.println("Parsing error");
	        e.printStackTrace();
	        return null;
	    } 
	    catch (IOException e) 
	    {
	        System.err.println("abnormal IO");
	        e.printStackTrace();
	        return null;
	    } 
	    finally 
	    {
	        if (null != response) 
	        {
	            try 
	            {
	                response.close();
	                httpClient.close();
	            } 
	            catch (IOException e)
	            {
	                System.err.println("Error releasing connection");
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	public static String postObject(String lastpart, JSONObject object)
	{
		String urlStr = URL +lastpart;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(urlStr);
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(180 * 1000).setConnectionRequestTimeout(180 * 1000)		
                .setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type","application/json");
        CloseableHttpResponse response = null;
        try 
        {
            httpPost.setEntity(new StringEntity(object.toString(),ContentType.create("application/json", "utf-8")));
            System.out.println("request parameters" + EntityUtils.toString(httpPost.getEntity()));			//test
            response = httpClient.execute(httpPost);
            System.out.println(" code:"+response.getStatusLine().getStatusCode());							//test
            System.out.println("doPostForInfobipUnsub response"+response.getStatusLine().toString());			//判断错误抛出异常alert？
            return String.valueOf(response.getStatusLine().getStatusCode());								//成功就返回200
        } 
        catch (ClientProtocolException e) 
	    {
	        System.err.println("Problems with Http Protocol");
	        e.printStackTrace();
	        return null;
	    } 
	    catch (ParseException e) 
	    {
	        System.err.println("Parsing error");
	        e.printStackTrace();
	        return null;
	    } 
	    catch (IOException e) 
	    {
	        System.err.println("abnormal IO");
	        e.printStackTrace();
	        return null;
	    } 
        finally 
        {
        	if (null != response) 
	        {
	            try 
	            {
	                response.close();
	                httpClient.close();
	            } 
	            catch (IOException e)
	            {
	                System.err.println("Error releasing connection");
	                e.printStackTrace();
	            }
	        }
        }
	}
	
	public static String postArray(String lastpart, JSONArray array)
	{
		String urlStr = URL +lastpart;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(urlStr);
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(180 * 1000).setConnectionRequestTimeout(180 * 1000)		
                .setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();         
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type","application/json");
        CloseableHttpResponse response = null;
        try 
        {
            httpPost.setEntity(new StringEntity(array.toString(),ContentType.create("application/json", "utf-8")));
            System.out.println("request parameters" + EntityUtils.toString(httpPost.getEntity()));				//test
            response = httpClient.execute(httpPost);
            System.out.println(" code:"+response.getStatusLine().getStatusCode());								//test
            System.out.println("doPostForInfobipUnsub response"+response.getStatusLine().toString());			//判断错误抛出异常alert？
            return String.valueOf(response.getStatusLine().getStatusCode());
        } 
        catch (ClientProtocolException e) 
	    {
	        System.err.println("Problems with Http Protocol");
	        e.printStackTrace();
	        return null;
	    } 
	    catch (ParseException e) 
	    {
	        System.err.println("Parsing error");
	        e.printStackTrace();
	        return null;
	    } 
	    catch (IOException e) 
	    {
	        System.err.println("abnormal IO");
	        e.printStackTrace();
	        return null;
	    } 
        finally 
        {
        	if (null != response) 
	        {
	            try 
	            {
	                response.close();
	                httpClient.close();
	            } 
	            catch (IOException e)
	            {
	                System.err.println("Error releasing connection");
	                e.printStackTrace();
	            }
	        }
        }
	}
	
	 /** 
     * 上传文件 
     */  
    public void upload() 
    {  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceFile.action");  
        try 
        {  
            FileBody bin = new FileBody(new File("F:\\image\\sendpix0.jpg"));  
            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);  
  
            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();  
            httppost.setEntity(reqEntity);  
  
            System.out.println("executing request " + httppost.getRequestLine());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try 
            {  
                System.out.println("----------------------------------------");  
                System.out.println(response.getStatusLine());  
                HttpEntity resEntity = response.getEntity();  
                if (resEntity != null) 
                {  
                    System.out.println("Response content length: " + resEntity.getContentLength());  
                }  
                EntityUtils.consume(resEntity);  
            } finally 
            {  
                response.close();  
            }  
        } 
        catch (ClientProtocolException e) 
        {  
            e.printStackTrace();  
        } 
        catch (IOException e) 
        {  
            e.printStackTrace();  
        } 
        finally 
        {  
            try 
            {  
                httpclient.close();  
            } 
            catch (IOException e) 
            {  
                e.printStackTrace();  
            }  
        }  
    }  
	
	public static void main(String[] args) 
	{
		
		/*
		 * JSONArray testObject = HttpTool.getArray("claims");
		 * System.out.println(testObject);
		 */
		Claim claim = new Claim("claim_id", "001", null, null, null, 0, null, "pending", DateTool.getCurrentDate(), DateTool.getCurrentDate());
		claim.setAccLocation("Beijing");
		claim.setAccDate(DateTool.getCurrentDate());
		claim.setClaimReason("测试");
		claim.setClaimAmount(Float.valueOf(250));
		claim.setClaimFiles(new ArrayList<File>());
		System.out.println("**************" + postObject("claims", ClaimTool.claimToJSONObject(claim)));
		 
	}
	

}
