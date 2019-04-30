package client.view;

import java.io.File;
import java.io.IOException;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Claim;
import tool.ClaimTool;
import tool.Controller;
import tool.UserTool;

//client-À˜≈‚…Í«Î»∑»œ“≥
public class ClaimApplicationCheckController extends Controller 
{
	@FXML
	private Label locationOfAcc;
	@FXML
	private Label dateOfAcc;
	@FXML
	private Text reasonOfClaim;
	@FXML
	private Label amountOfDamage;
	@FXML
	private Label typeOfAcc;
	@FXML
	private Text filenameList;
	@FXML
	private Button btn_home;
	@FXML
	private Button btn_logout;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_insuranceClaim;
	@FXML
	private Button btn_aboutUs;
	@FXML
	private Button btn_back;
	@FXML
	private Button btn_confirm;
	@FXML
	private Button bt_language;
	@FXML
	private Label lb_clientService;
	@FXML
	private Label lb_claimApplicationInfo;
	@FXML
	private Label lb_subject;
	@FXML
	private Label lb_location;
	@FXML
	private Label lb_date;
	@FXML
	private Label lb_reason;
	@FXML
	private Label lb_amount;
	@FXML
	private Label lb_typeOfAccInfo;
	@FXML
	private Label lb_clickToGet;
	@FXML
	private Text lb_acc1;
	@FXML
	private Text lb_acc2;
	@FXML
	private Text lb_acc3;
	@FXML
	private Label lb_acctype;
	@FXML
	private Label lb_claimSupportFile;
	
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
		btn_insuranceClaim.setText(UserTool.i18n.get("myclaim"));
		btn_aboutUs.setText(UserTool.i18n.get("aboutus"));
		btn_logout.setText(UserTool.i18n.get("logout"));
		
		lb_claimApplicationInfo.setText(UserTool.i18n.get("claimApplicationInfo"));
		lb_subject.setText(UserTool.i18n.get("subject"));
		lb_location.setText(UserTool.i18n.get("location"));
		lb_date.setText(UserTool.i18n.get("date"));
		lb_reason.setText(UserTool.i18n.get("reason"));
		lb_amount.setText(UserTool.i18n.get("amount"));
		lb_typeOfAccInfo.setText(UserTool.i18n.get("typeOfAccInfo"));
		lb_clickToGet.setText(UserTool.i18n.get("clickToGet"));
		lb_acc1.setText(UserTool.i18n.get("acc1"));
		lb_acc2.setText(UserTool.i18n.get("acc2"));
		lb_acc3.setText(UserTool.i18n.get("acc3"));
		lb_acctype.setText(UserTool.i18n.get("acctype"));
		lb_claimSupportFile.setText(UserTool.i18n.get("claimSupportFile"));
		btn_back.setText(UserTool.i18n.get("back"));
		btn_confirm.setText(UserTool.i18n.get("confirm"));
	}
	
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
		typeOfAcc.setText(claim.getTypeProperty(claim.getType()));
		for(File file:claim.getClaimFiles())
			filename += file.getName() + "\n";
		filenameList.setText(filename);
	}
	
	@FXML
	public void submit()
	{
		Pair<Integer, String> result = ClaimTool.post(claim);
		if (result.getKey() == 200)
			mainApp.showClientNoticeView("claim");
		else
			errorAlert("result");
	}
	
	public void setFileNotation(String filelist)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../client/view/ClaimFileView.fxml"));
			VBox claimFileView = (VBox) loader.load();
			claimFileView.setStyle("-fx-background-color: white;");
			Stage fileStage = new Stage();
			Scene fileScene = new Scene(claimFileView, 500, 500);
			ClaimFileController controller = loader.getController();
			controller.setFileText(filelist);
			fileStage.setScene(fileScene);
			fileStage.show();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	public void setNextButton1()
	{
		setFileNotation(UserTool.i18n.get("filelist1"));
	}
	
	@FXML
	public void setNextButton2()
	{
		setFileNotation(UserTool.i18n.get("filelist2"));
	}
	
	@FXML
	public void setNextButton3()
	{
		setFileNotation(UserTool.i18n.get("filelist3"));
	}
	
	@FXML
	public void backToClaimApplication()
	{
		mainApp.showClaimApplicationView2(claim);
	}
	
	@FXML
	public void toHome()
	{
		mainApp.showHomeView();
	}

	@FXML
	public void backToInsurance()
	{
		mainApp.showInsuranceView();
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
	public void toAboutUs()
	{
		mainApp.showAboutUsView();
	}
	
	@FXML
	public void logout()
	{
		mainApp.showLogInView();
	}
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
        setLanguageBtn();
    }

}
