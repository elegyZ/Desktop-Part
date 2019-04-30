package client.view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import tool.Controller;
import tool.UserTool;

public class HomeController extends Controller 
{
	@FXML
	private ScrollPane pane;
	@FXML
	private Button bt_plan1;
	@FXML
	private Button bt_plan2;
	@FXML
	private Button bt_plan3;
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
	private Button bt_language;
	@FXML
	private Label lb_clientService;
	@FXML
	private Text txt_1;
	@FXML
	private Text txt_2;
	@FXML
	private Text txt_3;
	
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
		btn_insuranceClaim.setText(UserTool.i18n.get("myclaim"));
		btn_aboutUs.setText(UserTool.i18n.get("aboutus"));
		btn_logout.setText(UserTool.i18n.get("logout"));
		
		txt_1.setText(UserTool.i18n.get("txt1"));
		txt_2.setText(UserTool.i18n.get("txt2"));
		txt_3.setText(UserTool.i18n.get("txt3"));
		bt_plan1.setText(UserTool.i18n.get("basic"));
		bt_plan2.setText(UserTool.i18n.get("medium"));
		bt_plan3.setText(UserTool.i18n.get("premium"));
	}
	
	@FXML
	public void toPlan1()
	{
		mainApp.showInsurancePurchaseView(1);
	}
	
	@FXML
	public void toPlan2()
	{
		mainApp.showInsurancePurchaseView(2);
	}
	
	@FXML
	public void toPlan3()
	{
		mainApp.showInsurancePurchaseView(3);
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
	
	public void setPane()
	{
		this.pane.setVvalue(0);
		System.out.println("***");			//test
	}
}
