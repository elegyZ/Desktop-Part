package employee.view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import model.Profile;
import tool.Controller;
import tool.ProfileTool;
import tool.UserTool;

public class EmployeeProfileController extends Controller 
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
    private VBox btn_group;
    @FXML
    private Line line_pending;
    @FXML
    private Line line_processing;
    @FXML
    private Line line_closed;
    @FXML
    private Button btn_claimAffair;
    @FXML
    private Button btn_pending;
    @FXML
    private Button btn_processing;
    @FXML
    private Button btn_closed;
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
	private Label lb_workmenu;
    @FXML
	private Button btn_logout;
	
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
		lb_workmenu.setText(UserTool.i18n.get("workmenu"));
		btn_claimAffair.setText(UserTool.i18n.get("claimAffair"));
		btn_pending.setText(UserTool.i18n.get("pending"));
		btn_processing.setText(UserTool.i18n.get("processing"));
		btn_closed.setText(UserTool.i18n.get("closed"));
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
        setVisible(false);
		btn_group.setOnMouseEntered((me) -> setVisible(true));
		btn_group.setOnMouseExited((me) -> setVisible(false));
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
		mainApp.showEmployeeProfileModifyView(profile);
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
    
    @FXML
	public void logout()
	{
		mainApp.showLogInView();
	}
}
