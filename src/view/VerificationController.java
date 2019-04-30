package view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import tool.Controller;
import tool.UserTool;

public class VerificationController extends Controller 
{
	@FXML
	private Tab typename;
	@FXML
	private Label lb_WTSHI;
	@FXML
	private Label lb_verification;
	@FXML
	private Button bt_language;
	@FXML
	private Button bt_back;
	@FXML
	private Button bt_send;
	
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
		lb_WTSHI.setText(UserTool.i18n.get("WTSHI"));
		lb_verification.setText(UserTool.i18n.get("verification"));
		bt_back.setText(UserTool.i18n.get("back"));
		bt_send.setText(UserTool.i18n.get("send"));
		if(type.equals("client"))
			typename.setText(UserTool.i18n.get("client"));
		else
			typename.setText(UserTool.i18n.get("employee"));
	}
	
	@FXML
	public void back()
	{
		mainApp.showFindPasswordView(type);
	}
	
	@FXML
	public void send()
	{
		mainApp.showNewPasswordView(type);
	}
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
        setLanguageBtn();
    }
	
	public void setType(String t)
	{
		this.type = t;
	}
}
