package client.view;

import client.desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Claim;
import model.Client;
import model.Policy;
import test.TestCase;

//client-À˜≈‚…Í«Î»∑»œ“≥
public class ClaimApplicationCheckController 
{
	@FXML
	private Label locationOfAcc;
	@FXML
	private Label dateOfAcc;
	@FXML
	private TextArea reasonOfClaim;
	@FXML
	private Label amountOfDamage;
	@FXML
	private Button btn_home;
	@FXML
	private Button btn_insuranceService;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_insuranceClaim;
	@FXML
	private Button btn_back;
	@FXML
	private Button btn_confirm;
	
	private MainApp mainApp;
	private Client client = TestCase.testClient;
	private Policy policy;
	private Claim claim;
	
	public void setPolicy(String policyId)
	{
		this.policy = client.getPolicy().get(policyId);
	}
	
	public void setClaim(Claim claim)
	{
		this.claim = claim;
	}
	
	@FXML
	public void initialize()
	{
		locationOfAcc.setText(claim.getAccLocation());
		dateOfAcc.setText(claim.getAccDate().toString());
	}
	
	@FXML
	public void toNoticeView()
	{
		mainApp.showClaimNoticeView();
	}
	
	@FXML
	public void backToClaimApplication()
	{
		mainApp.showClaimApplicationView(policy.getId());
	}
	
	@FXML
	public void backToInsuranceClaim()
	{
		mainApp.showInsuranceClaimView();
	}
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;

    }

}
