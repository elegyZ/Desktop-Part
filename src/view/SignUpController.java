package view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import net.sf.json.JSONObject;
import tool.UserTool;
import tool.Controller;
import tool.HttpTool;

public class SignUpController extends Controller 
{
	@FXML
	private Tab typename;
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
	@FXML
	private Label lb_WTSHI;
	@FXML
	private Label lb_username;
	@FXML
	private Label lb_password1;
	@FXML
	private Label lb_password2;
	@FXML
	private Button bt_language;
	
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
		lb_password1.setText(UserTool.i18n.get("password"));
		lb_password2.setText(UserTool.i18n.get("password2"));
		bt_signUp.setText(UserTool.i18n.get("signup"));
		bt_back.setText(UserTool.i18n.get("back"));
		if(type.equals("client"))
			typename.setText(UserTool.i18n.get("client"));
		else
			typename.setText(UserTool.i18n.get("employee"));
	}
	
	@FXML
	public void signUp()
	{
		String name = username.getText();
		String pwd = password.getText();
		String pwd2 = password2.getText();
		if(name.equals(""))
			checkAlert(UserTool.i18n.get("PleaseEnterYourUsername"));
		else if(pwd.equals(""))
			checkAlert(UserTool.i18n.get("PleaseEnterYourPassword"));
		else if(pwd2.equals(""))
			checkAlert(UserTool.i18n.get("PleaseConfirmYourPassword"));
		else if(!pwd.equals(pwd2))
			checkAlert(UserTool.i18n.get("ThePasswordsAreNotTheSamePleaseConfirmYourPassword"));
		else
		{
			JSONObject jobject = new JSONObject();
			jobject.put("username", name);
			jobject.put("password", pwd);
			Pair<Integer, String> reply;
			if(type.equals("client"))
				reply = HttpTool.postObject("/users/signup", UserTool.user.getToken(), jobject);
			else
				reply = HttpTool.postObject("/users/registerEmployee", UserTool.user.getToken(), jobject);
			if(reply.getKey().equals(200)) 
			{
				mainApp.showProfileCreateView();
			}
			else if(reply.getKey().equals(500))
			{
				JSONObject jerror = JSONObject.fromObject(reply.getValue());
				JSONObject jinfo = JSONObject.fromObject(jerror.get("err"));
				errorAlert(jinfo.getString("name") + "\n" + jinfo.getString("message"));
				username.setText(null);
				password.setText(null);
				password2.setText(null);
			}
			else
			{
				errorAlert(UserTool.i18n.get("ErrorPleasecheckyourUsernameandPassword")); 
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
        setLanguageBtn();
    }
	
	public void setType(String t)
	{
		this.type = t;
	}
}
