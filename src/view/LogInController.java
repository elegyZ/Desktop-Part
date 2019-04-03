package view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import net.sf.json.JSONObject;
import tool.ClientTool;
import tool.Controller;
import tool.EmployeeTool;
import tool.HttpTool;

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
			JSONObject userObject = new JSONObject();
			userObject.put("username", username);
			userObject.put("password", password);
			Pair<Integer, String> reply = HttpTool.postObject("/users/login", ClientTool.token, userObject);
			System.out.println(reply);			//test
			if(reply.getKey().equals(200)) 
			{
				JSONObject replyObject = JSONObject.fromObject(reply.getValue());
				ClientTool.setToken(replyObject.getString("token"));
				//get Client file 得到userId    /profiles/username
				mainApp.showInsuranceView();		//转去购买页面？
			}
			else
			{
				errorAlert("Error! Please check your Username and Password.");
				clientUserName.setText(null);
				clientPassword.setText(null);
			}
		}
	}
	
	@FXML
	public void clientSignUp()
	{
		mainApp.showClientSignUpView();
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
			JSONObject empolyeeObject = new JSONObject();
			empolyeeObject.put("username", username);
			empolyeeObject.put("password", password);
			Pair<Integer, String> reply = HttpTool.postObject("/users/login", ClientTool.token, empolyeeObject);			//url需要改吗？
			System.out.println(reply);			//test
			if(reply.getKey().equals(200)) 
			{
				JSONObject replyObject = JSONObject.fromObject(reply.getValue());
				EmployeeTool.setToken(replyObject.getString("token"));
				mainApp.showClaimAffairView();
			}
			else
			{
				errorAlert("Error! Please check your Username and Password.");
				employeeUserName.setText(null);
				employeePassword.setText(null);
			}
		}
	}
	
	@FXML
	public void employeeSignUp()
	{
		mainApp.showEmployeeSignUpView();
	}
	
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
	}
}
