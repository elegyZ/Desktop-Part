package client.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	private TextField gender;
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
	private Button btn_myProfile;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_myClaim;
	
	private MainApp mainApp;
	private Profile profile;
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
    }
	
	public void setProfile()
	{
		profile = new Profile("", lastName.getText(), firstName.getText(), socialId.getText(), gender.getText(), Integer.parseInt(age.getText()), country.getText(), 
							  province.getText(), city.getText(), phone.getText(), email.getText(), DateTool.getCurrentDate(), DateTool.getCurrentDate());
	}
	
	public void initProfile(Profile profile)
	{
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
	
	public void saveProfile()
	{
		Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(age.getText());
		if(firstName.getText().equals(""))
			checkAlert("Please Input Your Firstname.");
		else if(lastName.getText().equals(""))
			checkAlert("Please Input Your Lastname.");
		else if(socialId.getText().equals(""))
			checkAlert("Please Input Your Identity Number.");
		else if(gender.getText().equals(""))
			checkAlert("Please Input Your Gender.");
		else if(age.getText().equals(""))
			checkAlert("Please Input Your Age.");
		else if(!isNum.matches())
			checkAlert("Please Check Your Age, Only Numbers Allows.");
		else if(country.getText().equals(""))
			checkAlert("Please Input Your Country.");
		else if(province.getText().equals(""))
			checkAlert("Please Input Your Province.");
		else if(city.getText().equals(""))
			checkAlert("Please Input Your City.");
		else if(phone.getText().equals(""))
			checkAlert("Please Input Your Phone.");
		else if(email.getText().equals(""))
			checkAlert("Please Input Your Email.");
		else
		{
			setProfile();
			Pair<Integer, String> reply = HttpTool.postObject("/profiles", UserTool.user.getToken(), ProfileTool.profileToJSONObject(profile));
			if(reply.getKey().equals(200))
				;
			else
			{
				
			}
		}
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
	public void backToInsurance()
	{
		mainApp.showInsuranceView();
	}
}
