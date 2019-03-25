package test;

import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Claim;
import model.Client;
import model.Policy;
import tool.DateTool;

public class TestCase 
{	
	public static int flag = 0;
	public static ObservableList<Claim> claimData = FXCollections.observableArrayList();
	private Claim testClaim1 = new Claim("claim001", "policy001", "Beijing, China", Date.valueOf("2020-03-15"), "luggage lost", 500, null, "pending", Date.valueOf("2020-03-15"), Date.valueOf("2020-03-15"));
	private Claim testClaim2 = new Claim("claim002", "policy002", "Hangzhou, China", Date.valueOf("2020-04-01"), "broke on the plane", 200, null, "pending", Date.valueOf("2020-04-01"), Date.valueOf("2020-04-01"));
	private Claim testClaim3 = new Claim("claim003", "policy003", "Dublin, Irland", Date.valueOf("2020-09-10"), "lost on the ship", 300, null, "pending", Date.valueOf("2020-09-10"), Date.valueOf("2020-09-10"));
	
    public void addClaimList()
	{
		claimData.add(testClaim1);
		claimData.add(testClaim2);
		claimData.add(testClaim3);
	}
    
    public static Client testClient = new Client("client001", "user001", "lastname", "firstname", "330702199505162322", "female", 18, "Chine", "Beijing", "Chaoyang", "13426220706", "1998sophie@sina.com", DateTool.getCurrentDate(), DateTool.getCurrentDate());

	private Policy testPolicy1 = new Policy("policy001",1,2,Date.valueOf("2020-03-15"),2,Date.valueOf("2020-03-15"),Date.valueOf("2020-03-15"),"insured001");
	private Policy testPolicy2 = new Policy("policy002",2,3,Date.valueOf("2016-02-29"),1,Date.valueOf("2016-02-29"),Date.valueOf("2016-02-29"),"insured002");
	private Policy testPolicy3 = new Policy("policy003",3,1,Date.valueOf("2022-04-01"),24,Date.valueOf("2022-04-01"),Date.valueOf("2022-04-01"),"insured003");
	
	public static ObservableList<Policy> clientPolicyData = FXCollections.observableArrayList();
	public static ObservableList<Claim> clientClaimData = FXCollections.observableArrayList();

	public void addPolicyList()
	{
		testClient.addPolicy(testPolicy1.getId(), testPolicy1);
		testClient.addPolicy(testPolicy2.getId(), testPolicy2);
		testClient.addPolicy(testPolicy3.getId(), testPolicy3);
		clientPolicyData.add(testPolicy1);
		clientPolicyData.add(testPolicy2);
		clientPolicyData.add(testPolicy3);
	}
	

}
