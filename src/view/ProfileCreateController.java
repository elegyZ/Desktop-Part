package view;

import desktop.MainApp;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import model.Profile;
import tool.Controller;
import tool.DateTool;
import tool.ProfileTool;
import tool.UserTool;

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
	
	@FXML
	private Label lb_profile;
	@FXML
	private Label lb_firstname;
	@FXML
	private Label lb_lastname;
	@FXML
	private Label lb_gander;
	@FXML
	private Label lb_age;
	@FXML
	private Label lb_ID;
	@FXML
	private Label lb_country;
	@FXML
	private Label lb_province;
	@FXML
	private Label lb_city;
	@FXML
	private Label lb_phone;
	@FXML
	private Label lb_email;
	@FXML
	private Button bt_language;
	@FXML
	private Label lb_countrycode;
	@FXML
	private ChoiceBox<String> cb_nationcode;
	
	private MainApp mainApp;
	private Profile profile;
	
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
		lb_profile.setText(UserTool.i18n.get("profile"));
		lb_firstname.setText(UserTool.i18n.get("firstname"));
		lb_lastname.setText(UserTool.i18n.get("lastname"));
		lb_gander.setText(UserTool.i18n.get("gander"));
		lb_age.setText(UserTool.i18n.get("age"));
		lb_ID.setText(UserTool.i18n.get("ID"));
		lb_country.setText(UserTool.i18n.get("country"));
		lb_province.setText(UserTool.i18n.get("province"));
		lb_city.setText(UserTool.i18n.get("city"));
		lb_phone.setText(UserTool.i18n.get("phone"));
		lb_email.setText(UserTool.i18n.get("email"));
		create.setText(UserTool.i18n.get("create"));
		btn_male.setText(UserTool.i18n.get("male"));
		btn_female.setText(UserTool.i18n.get("female"));
		lb_countrycode.setText(UserTool.i18n.get("countrycode"));
		cb_nationcode.setItems(FXCollections.observableArrayList(UserTool.i18n.get("China"), UserTool.i18n.get("Ireland"), UserTool.i18n.get("US")));
		cb_nationcode.setValue(UserTool.i18n.get("China"));
	}
	
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
		cb_nationcode.setItems(FXCollections.observableArrayList(UserTool.i18n.get("China"), UserTool.i18n.get("Ireland"), UserTool.i18n.get("US")));
		cb_nationcode.setValue(UserTool.i18n.get("China"));
	}
	
	@FXML
	public void createProfile()
	{
		setProfile();
		Pair<Integer, String> reply = ProfileTool.postProfile(ProfileTool.profileToJSONObject(profile));
		if(reply.getKey() == 200)
		{
			if(UserTool.user.isEmployee())
				mainApp.showClaimAffairView("all");
			else
				mainApp.showHomeView();
		}
		else
		{
			errorAlert(reply.getValue());
			mainApp.showProfileCreateView();
		}
	}
	
	public String getCode()
	{
		String result = cb_nationcode.getValue();
		if(result.equals(UserTool.i18n.get("China")))
			return "+86";
		else if(result.equals(UserTool.i18n.get("Ireland")))
			return "+353";
		else
			return "+1";
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
		String phone = getCode() + " " + tf_phone.getText();
		String email = tf_email.getText();
		profile = new Profile("id", lastname, firstname, socialId, gender, Integer.valueOf(age), country, province, city, phone, email,
								DateTool.getCurrentDate(), DateTool.getCurrentDate());
	}
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
    }
}
