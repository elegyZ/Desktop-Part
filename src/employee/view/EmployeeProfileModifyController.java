package employee.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.util.Pair;
import model.Profile;
import tool.Controller;
import tool.DateTool;
import tool.HttpTool;
import tool.ProfileTool;
import tool.UserTool;

public class EmployeeProfileModifyController extends Controller 
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
    private VBox btn_group;
    @FXML
    private Line line_pending;
    @FXML
    private Line line_processing;
    @FXML
    private Line line_closed;
    @FXML
    private Button btn_pending;
    @FXML
    private Button btn_processing;
    @FXML
    private Button btn_closed;
    @FXML
    private Button btn_policyRenew;
    @FXML
    private Button btn_myProfile;
	
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
		setVisible(false);
		btn_group.setOnMouseEntered((me) -> setVisible(true));
		btn_group.setOnMouseExited((me) -> setVisible(false));
	}
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
    }
	
	public void setProfile()
	{
		profile = new Profile("", lastName.getText(), firstName.getText(), socialId.getText(), (btn_male.isSelected()?"male":"female"), Integer.parseInt(age.getText()), country.getText(), 
							  province.getText(), city.getText(), phone.getText(), email.getText(), DateTool.getCurrentDate(), DateTool.getCurrentDate());
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
			checkAlert("Please Input Your Firstname.");
		else if(lastName.getText().equals(""))
			checkAlert("Please Input Your Lastname.");
		else if(socialId.getText().equals(""))
			checkAlert("Please Input Your Identity Number.");
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
			{
				UserTool.freshProfile();
				mainApp.showEmployeeProfileView();
			}
			else
			{
				errorAlert(reply.getValue());
				mainApp.showEmployeeProfileModifyView(profile);
			}
		}
	}
	
	public void setVisible(Boolean bool)
    {
    	if(bool)
    	{
    		btn_pending.setVisible(true);
    		btn_processing.setVisible(true);
    		btn_closed.setVisible(true);
    		line_pending.setVisible(true);
    		line_processing.setVisible(true);
    		line_closed.setVisible(true);
    		btn_pending.setManaged(true);
    		btn_processing.setManaged(true);
    		btn_closed.setManaged(true);
    		line_pending.setManaged(true);
    		line_processing.setManaged(true);
    		line_closed.setManaged(true);
    	}
    	else
    	{
    		btn_pending.setVisible(false);
    		btn_processing.setVisible(false);
    		btn_closed.setVisible(false);
    		line_pending.setVisible(false);
    		line_processing.setVisible(false);
    		line_closed.setVisible(false);
    		btn_pending.setManaged(false);
    		btn_processing.setManaged(false);
    		btn_closed.setManaged(false);
    		line_pending.setManaged(false);
    		line_processing.setManaged(false);
    		line_closed.setManaged(false);
    	}
    }
	
	@FXML
    public void getPendingAffair()
    {
    	mainApp.showClaimAffairView("pending");
    }
    
    @FXML
    public void getProcessingAffair()
    {
    	mainApp.showClaimAffairView("processing");
    }
    
    @FXML
    public void getClosedAffiar()
    {
    	mainApp.showClaimAffairView("closed");
    }
    
    @FXML
    public void getAllClaimAffiar()
    {
    	
    	mainApp.showClaimAffairView("all");
    }
    
    @FXML
    public void toProfileView()
    {
    	mainApp.showEmployeeProfileView();
    }
}
