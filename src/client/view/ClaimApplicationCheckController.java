package client.view;

import client.desktop.ClientMainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Claim;
import tool.ClaimTool;
import tool.HttpTool;

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
	
	private ClientMainApp mainApp;
	private Claim claim;
	
	public void setClaim(Claim claim)
	{
		this.claim = claim;
		initView();
	}
	
	public void initView()
	{
		locationOfAcc.setText(claim.getAccLocation());
		dateOfAcc.setText(claim.getAccDate().toString());
		reasonOfClaim.setText(claim.getClaimReason());
		amountOfDamage.setText(String.valueOf(claim.getClaimAmount()));
	}
	
	@FXML
	public void submit()
	{
		String result = HttpTool.postObject("claims", ClaimTool.claimToJSONObject(claim));
		if(result.equals("200"))
			mainApp.showClaimNoticeView();
	}
	
	@FXML
	public void backToClaimApplication()
	{
		mainApp.showClaimApplicationView2(claim);
	}
	
	@FXML
	public void backToInsuranceClaim()
	{
		mainApp.showInsuranceClaimView();
	}
	
	public void setMainApp(ClientMainApp mainApp) 
    {
        this.mainApp = mainApp;

    }

}
