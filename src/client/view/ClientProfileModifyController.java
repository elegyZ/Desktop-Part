package client.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import tool.HttpTool;
import tool.ProfileTool;
import tool.UserTool;

public class ClientProfileModifyController extends Controller 
{
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField socialId;
	@FXML
	private RadioButton btn_male;
	@FXML
	private RadioButton btn_female;
	@FXML
	private TextField age;
	@FXML
	private TextField country;
	@FXML
	private TextField province;
	@FXML
	private TextField city;
	@FXML
	private TextField phone;
	@FXML
	private TextField email;
	@FXML
	private Button btn_save;
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
		btn_male.setText(UserTool.i18n.get("male"));
		btn_female.setText(UserTool.i18n.get("female"));
		btn_save.setText(UserTool.i18n.get("submit"));
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
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
        setLanguageBtn();
    }
	
	public void setProfile()
	{
		profile = new Profile("", lastName.getText(), firstName.getText(), socialId.getText(), (btn_male.isSelected()?"male":"female"), Integer.parseInt(age.getText()), country.getText(), 
							  province.getText(), city.getText(), getCode() + " " + phone.getText(), email.getText(), DateTool.getCurrentDate(), DateTool.getCurrentDate());
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
	
	public void initProfile(Profile profile)
	{
		firstName.setText(profile.getFirstName());
		lastName.setText(profile.getLastName());
		socialId.setText(profile.getSocialId());
		if(profile.getGender().toLowerCase().equals("male"))
		{
			btn_male.setSelected(true);
			btn_female.setSelected(false);
		}
		else
		{
			btn_female.setSelected(true);
			btn_male.setSelected(false);
		}
		age.setText(String.valueOf(profile.getAge()));
		country.setText(profile.getCountry());
		province.setText(profile.getProvince());
		city.setText(profile.getCity());
		phone.setText(profile.getPhone());
		email.setText(profile.getEmail());
	}
	
	public void saveProfile()
	{
		Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(age.getText());
		if(firstName.getText().equals(""))
			checkAlert(UserTool.i18n.get("PleaseInputYourFirstname"));
		else if(lastName.getText().equals(""))
			checkAlert(UserTool.i18n.get("PleaseInputYourLastname"));
		else if(socialId.getText().equals(""))
			checkAlert(UserTool.i18n.get("PleaseInputYourIdentityNumber"));
		else if(age.getText().equals(""))
			checkAlert(UserTool.i18n.get("PleaseInputYourAge"));
		else if(!isNum.matches())
			checkAlert(UserTool.i18n.get("PleaseCheckYourAgeOnlyNumbersAllows"));
		else if(country.getText().equals(""))
			checkAlert(UserTool.i18n.get("PleaseInputYourCountry"));
		else if(province.getText().equals(""))
			checkAlert(UserTool.i18n.get("PleaseInputYourProvince"));
		else if(city.getText().equals(""))
			checkAlert(UserTool.i18n.get("PleaseInputYourCity"));
		else if(phone.getText().equals(""))
			checkAlert(UserTool.i18n.get("PleaseInputYourPhone"));
		else if(email.getText().equals(""))
			checkAlert(UserTool.i18n.get("PleaseInputYourEmail"));
		else
		{
			setProfile();
			Pair<Integer, String> reply = HttpTool.postObject("/profiles", UserTool.user.getToken(), ProfileTool.profileToJSONObject(profile));
			if(reply.getKey().equals(200))
			{
				UserTool.freshProfile();
				mainApp.showClientProfileView();
			}
			else
			{
				errorAlert(reply.getValue());
				mainApp.showClientProfileModifyView(profile);
			}
		}
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
