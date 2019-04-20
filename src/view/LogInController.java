package view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	
	private MainApp mainApp;
	
	@FXML
	public void clientLogIn()
	{
		String username = clientUserName.getText();
		String password = clientPassword.getText();
		if(username.equals(""))
			checkAlert("Please Enter Your Username.");
		else if(password.equals(""))
			checkAlert("Please Enter Your Password.");
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
						errorAlert("Employee Should Log In On Employee Page.");
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
	public void employeeLogIn()
	{
		String username = employeeUserName.getText();
		String password = employeePassword.getText();
		if(username.equals(""))
			checkAlert("Please Enter Your Username.");
		else if(password.equals(""))
			checkAlert("Please Enter Your Password.");
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
						errorAlert("Client Should Log In On Client Page.");
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
	
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
	}
}
