package client.view;

import java.io.File;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Claim;
import tool.Controller;
import tool.HttpTool;
import tool.UserTool;

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
		else
		{
			btn_download.setVisible(false);
			btn_download.setManaged(false);
		}
	}
	
	@FXML
	public void downloadFile()
	{
		String root = setDowmloadPath();
		for(File file:claim.getClaimFiles())
		{
			String lastpart = "/res/claim-files/" + claim.getId() + "/" + file;
			Pair<Integer, String> reply = HttpTool.download(lastpart, UserTool.user.getToken(), file.toString(), root);
			if(reply.getKey() == 200)
				successAlert("Files Had Been Downloaded Successfully.");
			else
				errorAlert(reply.getValue());
		}
	}
	
	public String setDowmloadPath()
	{
		DirectoryChooser directoryChooser=new DirectoryChooser();
		File file = directoryChooser.showDialog(new Stage());
		String path = file.getPath();
		return path;
	}
	
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
}
