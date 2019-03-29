package test;

import model.Client;
import tool.DateTool;

public class TestCase 
{	
	public static int flag = 0;
	public static String policy_array = "purchased_insurances";
	public static String policy001 = "policy001";
	public static String policy002 = "policy002";
	public static String policy003 = "policy003";
	public static String claim_array = "claims";
	
    public static Client testClient = new Client("client001", "user001", "lastname", "firstname", "330702199505162322", "female", 18, "Chine", "Beijing", "Chaoyang", "13426220706", "1998sophie@sina.com", DateTool.getCurrentDate(), DateTool.getCurrentDate());
	

}
