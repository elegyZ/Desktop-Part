package view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import model.Profile;
import tool.Controller;
import tool.DateTool;
import tool.ProfileTool;

public class ProfileCreateController extends Controller 
{
	@FXML
	private TextField tf_firstname;
	@FXML
	private TextField tf_lastname;
	@FXML
	private RadioButton btn_male;
	@FXML
	private RadioButton btn_female;
	@FXML
	private TextField tf_age;
	@FXML
	private TextField tf_socialId;
	@FXML
	private TextField tf_country;
	@FXML
	private TextField tf_province;
	@FXML
	private TextField tf_city;
	@FXML
	private TextField tf_phone;
	@FXML
	private TextField tf_email;
	@FXML
	private Button create;
	
	private MainApp mainApp;
	private Profile profile;
	
	@FXML
	public void initialize()
	{
		btn_male.setSelected(true);
		btn_male.setOnMouseClicked((me) -> {
			btn_female.setSelected(false);
		});
		btn_female.setOnMouseClicked((me) -> {
			btn_male.setSelected(false);
		});
	}
	
	@FXML
	public void createProfile()
	{
		setProfile();
		Pair<Integer, String> reply = ProfileTool.postProfile(ProfileTool.profileToJSONObject(profile));
		if(reply.getKey() == 200)
		{
			mainApp.showHomeView();
		}
		else
		{
			errorAlert(reply.getValue());
			mainApp.showProfileCreateView();
		}
	}
	
	public void setProfile()
	{
		String firstname = tf_firstname.getText();
		String lastname = tf_lastname.getText();
		String gender = btn_male.isSelected()?"male":"female";
		String age = tf_age.getText();
		String socialId = tf_socialId.getText();
		String country = tf_country.getText();
		String province = tf_province.getText();
		String city = tf_city.getText();
		String phone = tf_phone.getText();
		String email = tf_email.getText();
		profile = new Profile("id", lastname, firstname, socialId, gender, Integer.valueOf(age), country, province, city, phone, email,
								DateTool.getCurrentDate(), DateTool.getCurrentDate());
	}
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
    }
}
