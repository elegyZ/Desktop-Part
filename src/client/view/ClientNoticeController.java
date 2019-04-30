package client.view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tool.Controller;
import tool.UserTool;

public class ClientNoticeController extends Controller 
{
	@FXML
	private Label hintwords;
	@FXML
	private Button btn_act;
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
	
	private MainApp mainApp;
	private String type;
	
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
		
		setType(type);
	}
	
	public void setType(String t)
	{
		this.type = t;
		if(type.equals("claim"))
		{
			hintwords.setText(UserTool.i18n.get("claimNoticeWords"));
			btn_act.setText(UserTool.i18n.get("back"));
			btn_act.setOnMouseClicked((me) -> {
				mainApp.showInsuranceView();
			});
		}
		else if(type.equals("policy"))
		{
			hintwords.setText(UserTool.i18n.get("policyNoticeWords"));
			btn_act.setText(UserTool.i18n.get("back"));
			btn_act.setOnMouseClicked((me) -> {
				mainApp.showHomeView();
			});
		}
	}
	
	@FXML
	public void buttonAction()
	{
		mainApp.showHomeView();
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
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
        setLanguageBtn();
    }
}
