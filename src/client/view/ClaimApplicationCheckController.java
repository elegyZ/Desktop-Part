package client.view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.util.Pair;
import model.Claim;
import tool.ClaimTool;
import tool.ClientTool;
import tool.Controller;
import tool.HttpTool;

//client-À˜≈‚…Í«Î»∑»œ“≥
public class ClaimApplicationCheckController extends Controller 
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
		Pair<Integer, String> result = HttpTool.postObject("/claims", ClientTool.token, ClaimTool.claimToJSONObject(claim));
		if (result.getKey() == 200)
			mainApp.showClaimNoticeView();
		else
		{
			System.out.println(result);
		}
	}
	
	@FXML
	public void backToClaimApplication()
	{
		mainApp.showClaimApplicationView2(claim);
	}
	
	@FXML
	public void backToInsurance()
	{
		mainApp.showInsuranceView();
	}
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;

    }

}
