package client.view;

import java.io.File;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.Claim;
import tool.Controller;

public class ClaimFeedbackController extends Controller 
{
	@FXML
	private Label date;
	@FXML
	private Label acclocation;
	@FXML
	private Label amount;
	@FXML
	private Text reason;
	@FXML
	private Text filenameList;
	@FXML
	private Button btn_download;
	@FXML
	private Label claimstatus;
	@FXML
	private Label rejectLabel;
	@FXML
	private Text rejectReason;
	@FXML
	private Button btn_back;
	@FXML
	private Button btn_home;
	@FXML
	private Button btn_myClaim;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_myProfile;
	
	private MainApp mainApp;
	private Claim claim;
	private String filename = "";
	
	@FXML
	public void toHome()
	{
		mainApp.showHomeView();
	}
	
	@FXML
	public void toClaimView()
	{
		mainApp.showClaimView();
	}
	
	@FXML
	public void toProfile()
	{
		mainApp.showClientProfileView();
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
	
	public void setClaim(Claim claim)
	{
		this.claim = claim;
		initView();
	}
	
	public void initView()
	{
		date.setText(claim.getAccDate().toString());
		acclocation.setText(claim.getAccLocation());
		amount.setText(String.valueOf(claim.getClaimAmount()));
		reason.setText(claim.getClaimReason());
		claimstatus.setText(claim.getStatus());
		rejectLabel.setManaged(false);
		rejectReason.setManaged(false);
		rejectLabel.setVisible(false);
		rejectReason.setVisible(false);
		if(claim.getStatus().equals("rejected"))
		{
			rejectLabel.setManaged(true);
			rejectReason.setManaged(true);
			rejectLabel.setVisible(true);
			rejectReason.setVisible(true);
			rejectReason.setText(claim.getRejectReason());
		}
		if(!claim.getClaimFiles().isEmpty())
		{
			for(File file:claim.getClaimFiles())
				filename += file.getName() + "\n";
			filenameList.setText(filename);
		}
	}
}
