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
	private Button btn_logout;
	@FXML
	private Button btn_myClaim;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_aboutUs;
	@FXML
	private Button bt_language;
	@FXML
	private Label lb_clientService;
	@FXML
	private Label lb_claimInfo;
	@FXML
	private Label lb_location;
	@FXML
	private Label lb_date;
	@FXML
	private Label lb_amount;
	@FXML
	private Label lb_reason;
	@FXML
	private Label lb_claimSupportFile;
	@FXML
	private Label lb_claimStatus;
	
	private MainApp mainApp;
	private Claim claim;
	private String filename = "";
	
	@FXML
	public void changeLanguage()
	{
		UserTool.i18n.changeLanguage();
		setLanguageBtn();
	}
	
	public void setLanguageBtn()
	{
		bt_language.setText(UserTool.i18n.get("language"));
		setText();
	}
	
	public void setText()
	{
		lb_clientService.setText(UserTool.i18n.get("clientservice"));
		btn_home.setText(UserTool.i18n.get("home"));
		btn_myInsurance.setText(UserTool.i18n.get("myinsurance"));
		btn_myClaim.setText(UserTool.i18n.get("myclaim"));
		btn_aboutUs.setText(UserTool.i18n.get("aboutus"));
		btn_logout.setText(UserTool.i18n.get("logout"));
		
		lb_claimInfo.setText(UserTool.i18n.get("claimInfo"));
		lb_location.setText(UserTool.i18n.get("location"));
		lb_date.setText(UserTool.i18n.get("date"));
		lb_amount.setText(UserTool.i18n.get("amount"));
		lb_reason.setText(UserTool.i18n.get("reason"));
		lb_claimSupportFile.setText(UserTool.i18n.get("claimSupportFile"));
		lb_claimStatus.setText(UserTool.i18n.get("claimStatus"));
		rejectLabel.setText(UserTool.i18n.get("reject"));
		btn_back.setText(UserTool.i18n.get("back"));
		btn_download.setText(UserTool.i18n.get("download"));
	}
	    
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
        setLanguageBtn();
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
				successAlert(UserTool.i18n.get("FilesHadBeenDownloadedSuccessfully"));
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
	
	@FXML
	public void toAboutUs()
	{
		mainApp.showAboutUsView();
	}
	
	@FXML
	public void logout()
	{
		mainApp.showLogInView();
	}
}
