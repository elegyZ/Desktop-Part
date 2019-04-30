package client.view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import tool.Controller;
import tool.UserTool;

public class AboutUsController extends Controller 
{
	@FXML
	private Label lb_clientService;
	@FXML
	private Button btn_logout;
	@FXML
	private Button btn_home;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_myClaim;
	@FXML
	private Button btn_aboutUs;
	@FXML
	private Button bt_language;
	@FXML
	private Label lb_name1;
	@FXML
	private Label lb_name2;
	@FXML
	private Label lb_name3;
	@FXML
	private Label lb_name4;
	@FXML
	private Label lb_name5;
	@FXML
	private Label lb_name6;
	@FXML
	private Label lb_position1;
	@FXML
	private Label lb_position2;
	@FXML
	private Label lb_position3;
	@FXML
	private Label lb_position4;
	@FXML
	private Label lb_position5;
	@FXML
	private Label lb_position6;
	@FXML
	private Text txt1;
	@FXML
	private Text txt2;
	@FXML
	private Text txt3;
	@FXML
	private Text txt4;
	@FXML
	private Text txt5;
	@FXML
	private Text txt6;
	
	private MainApp mainApp;
	
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
		
		lb_name1.setText(UserTool.i18n.get("name1"));
		lb_name2.setText(UserTool.i18n.get("name2"));
		lb_name3.setText(UserTool.i18n.get("name3"));
		lb_name4.setText(UserTool.i18n.get("name4"));
		lb_name5.setText(UserTool.i18n.get("name5"));
		lb_name6.setText(UserTool.i18n.get("name6"));
		lb_position1.setText(UserTool.i18n.get("position1"));
		lb_position2.setText(UserTool.i18n.get("position2"));
		lb_position3.setText(UserTool.i18n.get("position3"));
		lb_position4.setText(UserTool.i18n.get("position4"));
		lb_position5.setText(UserTool.i18n.get("position5"));
		lb_position6.setText(UserTool.i18n.get("position6"));
		txt1.setText(UserTool.i18n.get("description1"));
		txt2.setText(UserTool.i18n.get("description2"));
		txt3.setText(UserTool.i18n.get("description3"));
		txt4.setText(UserTool.i18n.get("description4"));
		txt5.setText(UserTool.i18n.get("description5"));
		txt6.setText(UserTool.i18n.get("description6"));
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
