package view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import tool.UserTool;
import tool.Controller;

public class LogInController extends Controller 
{
	@FXML
	private TextField clientUserName;
	@FXML
	private TextField clientPassword;
	@FXML
	private TextField employeeUserName;
	@FXML
	private TextField employeePassword;
	@FXML
	private Button bt_clientLogIn;
	@FXML
	private Button bt_clientSignUp;
	@FXML
	private Button bt_employeeLogIn;
	@FXML
	private Button bt_employeeSignUp;
	@FXML
	private Label lb_WTSHI;
	@FXML
	private Tab tb_client;
	@FXML
	private Tab tb_employee;
	@FXML
	private Label lb_username1;
	@FXML
	private Label lb_password1;
	@FXML
	private Label lb_forget1;
	@FXML
	private Label lb_username2;
	@FXML
	private Label lb_password2;
	@FXML
	private Label lb_forget2;
	@FXML
	private Button bt_language;
	
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
		lb_WTSHI.setText(UserTool.i18n.get("WTSHI"));
		tb_client.setText(UserTool.i18n.get("client"));
		tb_employee.setText(UserTool.i18n.get("employee"));
		lb_username1.setText(UserTool.i18n.get("username"));
		lb_username2.setText(UserTool.i18n.get("username"));
		lb_password1.setText(UserTool.i18n.get("password"));
		lb_password2.setText(UserTool.i18n.get("password"));
		lb_forget1.setText(UserTool.i18n.get("forget"));
		lb_forget2.setText(UserTool.i18n.get("forget"));
		bt_clientLogIn.setText(UserTool.i18n.get("login"));
		bt_employeeLogIn.setText(UserTool.i18n.get("login"));
		bt_clientSignUp.setText(UserTool.i18n.get("signup"));
		bt_employeeSignUp.setText(UserTool.i18n.get("signup"));
	}
	
	@FXML
	public void clientLogIn()
	{
		String username = clientUserName.getText();
		String password = clientPassword.getText();
		if(username.equals(""))
			checkAlert(UserTool.i18n.get("PleaseEnterYourUsername"));
		else if(password.equals(""))
			checkAlert(UserTool.i18n.get("PleaseEnterYourPassword"));
		else
		{
			Pair<Integer, String> reply = UserTool.login(username, password);
			if(reply.getKey().equals(200)) 
			{
				if(UserTool.initUserInfo(reply, username))
				{
					if(!UserTool.user.isEmployee())
						mainApp.showHomeView();
					else
					{
						errorAlert(UserTool.i18n.get("EmployeeShouldLogInOnEmployeePage"));
						clientUserName.setText(null);
						clientPassword.setText(null);
					}
				}
				else
					mainApp.showProfileCreateView();
			}
			else
			{
				errorAlert(reply.getValue());
				clientUserName.setText(null);
				clientPassword.setText(null);
			}
		}
	}
	
	@FXML
	public void clientSignUp()
	{
		mainApp.showSignUpView("client");
	}
	
	@FXML
	public void clientForgot()
	{
		mainApp.showFindPasswordView("client");
	}
	
	@FXML
	public void employeeLogIn()
	{
		String username = employeeUserName.getText();
		String password = employeePassword.getText();
		if(username.equals(""))
			checkAlert(UserTool.i18n.get("PleaseEnterYourUsername"));
		else if(password.equals(""))
			checkAlert(UserTool.i18n.get("PleaseEnterYourPassword"));
		else
		{
			Pair<Integer, String> reply = UserTool.login(username, password);
			if(reply.getKey().equals(200)) 
			{
				if(UserTool.initUserInfo(reply, username))
				{
					if(UserTool.user.isEmployee())
						mainApp.showClaimAffairView("all");
					else
					{
						errorAlert(UserTool.i18n.get("ClientShouldLogInOnClientPage"));
						employeeUserName.setText(null);
						employeePassword.setText(null);
					}
				}
				else
					mainApp.showProfileCreateView();
			}
			else
			{
				errorAlert(reply.getValue());
				employeeUserName.setText(null);
				employeePassword.setText(null);
			}
		}
	}
	
	@FXML
	public void employeeSignUp()
	{
		mainApp.showSignUpView("employee");
	}
	
	@FXML
	public void employeeForgot()
	{
		mainApp.showFindPasswordView("employee");
	}
	
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
		setLanguageBtn();
	}
}
