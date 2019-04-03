package view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import net.sf.json.JSONObject;
import tool.ClientTool;
import tool.Controller;
import tool.HttpTool;

public class EmployeeSignUpController extends Controller 
{
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private TextField password2;
	@FXML
	private Button bt_signUp;
	@FXML
	private Button bt_back;
	private MainApp mainApp;
	
	@FXML
	public void signUp()
	{
		String name = username.getText();
		String pwd = password.getText();
		String pwd2 = password2.getText();
		if(name.equals(""))
			checkAlert("Please Enter Your Username.");
		else if(pwd.equals(""))
			checkAlert("Please Enter Your Password.");
		else if(pwd2.equals(""))
			checkAlert("Please Confirm Your Password.");
		else if(!pwd.equals(pwd2))
			checkAlert("The Passwords Are Not The Same, Please Confirm Your Password.");
		else
		{
			JSONObject jobject = new JSONObject();
			jobject.put("username", name);
			jobject.put("password", pwd);
			Pair<Integer, String> reply = HttpTool.postObject("/users/registerEmployee", ClientTool.token, jobject);
			System.out.println(reply);			//test
			if(reply.getKey().equals(200)) 
			{
				//»•¿Ì≈‚“≥√Ê£ø	
			}
			else
			{
				JSONObject jerror = JSONObject.fromObject(reply.getValue());
				JSONObject jinfo = JSONObject.fromObject(jerror.get("err"));
				errorAlert(jinfo.getString("name") + "\n" + jinfo.getString("message"));
				username.setText(null);
				password.setText(null);
				password2.setText(null);
			}
		}
	}
	
	@FXML
	public void toSignInView()
	{
		mainApp.showLogInView();
	}
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
    }
}
