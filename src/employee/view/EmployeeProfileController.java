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
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
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
}
