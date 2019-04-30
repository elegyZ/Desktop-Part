package client.view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Profile;
import tool.Controller;
import tool.ProfileTool;
import tool.UserTool;

public class ClientProfileController extends Controller 
{
	@FXML
	private Label firstName;
	@FXML
	private Label lastName;
	@FXML
	private Label socialId;
	@FXML
	private Label gender;
	@FXML
	private Label age;
	@FXML
	private Label country;
	@FXML
	private Label province;
	@FXML
	private Label city;
	@FXML
	private Label phone;
	@FXML
	private Label email;
	@FXML
	private Button btn_modify;
	@FXML
	private Button btn_home;
	@FXML
	private Button btn_logout;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_myClaim;
	@FXML
	private Button btn_aboutUs;
	@FXML
	private Button bt_language;
	@FXML
	private Label lb_clientService;
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
		lb_clientService.setText(UserTool.i18n.get("clientservice"));
		btn_home.setText(UserTool.i18n.get("home"));
		btn_myInsurance.setText(UserTool.i18n.get("myinsurance"));
		btn_myClaim.setText(UserTool.i18n.get("myclaim"));
		btn_aboutUs.setText(UserTool.i18n.get("aboutus"));
		btn_logout.setText(UserTool.i18n.get("logout"));
		
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
		btn_modify.setText(UserTool.i18n.get("modify"));
	}
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
        setLanguageBtn();
    }
	
	public void setProfile(String profileId)
	{
		profile = ProfileTool.JSONObjectToprofile(ProfileTool.getJSONObject(profileId));
		firstName.setText(profile.getFirstName());
		lastName.setText(profile.getLastName());
		socialId.setText(profile.getSocialId());
		gender.setText(profile.getGender());
		age.setText(String.valueOf(profile.getAge()));
		country.setText(profile.getCountry());
		province.setText(profile.getProvince());
		city.setText(profile.getCity());
		phone.setText(profile.getPhone());
		email.setText(profile.getEmail());
	}
	
	public void modify()
	{
		mainApp.showClientProfileModifyView(profile);
	}
	
	@FXML
	public void toHome()
	{
		mainApp.showHomeView();
	}
	
	@FXML
	public void toClaimView()
	{
		mainApp.showClaimView();
	}
	
	@FXML
	public void toProfile()
	{
		mainApp.showClientProfileView();
	}
	
	@FXML
	public void toAboutUs()
	{
		mainApp.showAboutUsView();
	}
	
	@FXML
	public void logout()
	{
		mainApp.showLogInView();
	}
	
	@FXML
	public void backToInsurance()
	{
		mainApp.showInsuranceView();
	}
}
