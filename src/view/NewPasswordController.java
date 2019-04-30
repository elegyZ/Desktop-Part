package view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import tool.Controller;
import tool.UserTool;

public class NewPasswordController extends Controller 
{
	@FXML
	private Tab typename;
	@FXML
	private Label lb_WTSHI;
	@FXML
	private Button bt_language;
	@FXML
	private Label lb_newPassword;
	@FXML
	private Label lb_confirmPassword;
	@FXML
	private PasswordField pf_password1;
	@FXML
	private PasswordField pf_password2;
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
		lb_newPassword.setText(UserTool.i18n.get("password3"));
		lb_confirmPassword.setText(UserTool.i18n.get("password2"));
		if(type.equals("client"))
			typename.setText(UserTool.i18n.get("client"));
		else
			typename.setText(UserTool.i18n.get("employee"));
		bt_back.setText(UserTool.i18n.get("back"));
		bt_submit.setText(UserTool.i18n.get("submit"));
	}
	
	@FXML
	public void back()
	{
		mainApp.showVerificationView(type);
	}
	
	@FXML
	public void submit()
	{
		//mainApp.showNewPasswordView(type);
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
