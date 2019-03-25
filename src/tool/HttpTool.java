package tool;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class HttpTool 
{
	public final static String URL = "https://65ea4d1c-c0d7-4115-8198-1c38cf9dd578.mock.pstmn.io/";
	
	public static JSONObject getObject(String lastpart)
	{
		HttpClient client = HttpClients.createDefault();
		String urlStr = URL + lastpart;
        HttpGet get = new HttpGet(urlStr);
        try 
        {
             
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            JSONObject testObject = JSONObject.fromObject(result);  
            return testObject;
        } 
        catch (ClientProtocolException e) 
        {
            e.printStackTrace();
            return null;
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return null;
        }
	}
	
	public static void main(String[] args) 
	{
		/*
		 * CloseableHttpClient httpCilent = HttpClients.createDefault();//Creates
		 * CloseableHttpClient instance with default configuration. HttpGet httpGet =
		 * new
		 * HttpGet("https://65ea4d1c-c0d7-4115-8198-1c38cf9dd578.mock.pstmn.io/claim");
		 * try { httpCilent.execute(httpGet); System.out.println("1"); } catch
		 * (IOException e) { e.printStackTrace(); }finally { try {
		 * httpCilent.close();//ÊÍ·Å×ÊÔ´ System.out.println("2"); } catch (IOException e) {
		 * e.printStackTrace(); } }
		 */
		 
		JSONObject testObject = HttpTool.getObject("5c7e0af6a60cd62550b700a6");
		System.out.println(testObject.get("accLocation"));
	}
	

}
