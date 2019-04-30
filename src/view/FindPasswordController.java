package view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import tool.Controller;
import tool.UserTool;

public class FindPasswordController extends Controller 
{
	@FXML
	private Tab typename;
	@FXML
	private Label lb_WTSHI;
	@FXML
	private Label lb_username;
	@FXML
	private Label lb_phoneNumber;
	@FXML
	private TextField tf_userName;
	@FXML
	private TextField tf_phoneNumber;
	@FXML
	private Button bt_language;
	@FXML
	private Button bt_back;
	@FXML
	private Button bt_submit;
	
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
		lb_username.setText(UserTool.i18n.get("username"));
		lb_phoneNumber.setText(UserTool.i18n.get("phonenumber"));
		bt_back.setText(UserTool.i18n.get("back"));
		bt_submit.setText(UserTool.i18n.get("submit"));
		if(type.equals("client"))
			typename.setText(UserTool.i18n.get("client"));
		else
			typename.setText(UserTool.i18n.get("employee"));
	}
	
	@FXML
	public void back()
	{
		mainApp.showLogInView();
	}
	
	@FXML
	public void submit()
	{
		mainApp.showVerificationView(type);
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
