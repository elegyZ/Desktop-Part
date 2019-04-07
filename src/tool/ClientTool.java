package tool;

import model.Client;

public class ClientTool 
{
	public static String token = "";
	public static String username = "";
	
	public static void setToken(String token)
	{
		ClientTool.token = token;
	}
	
	public static void setUsername(String username)
	{
		ClientTool.username = username;
	}
	
	public static Boolean hasProfile()
	{
		HttpTool.getObject("/profiles/" + username, ClientTool.token);
		return true;
	}
	
	public static Client getProfile()
	{
		return null;
		
	}
}
