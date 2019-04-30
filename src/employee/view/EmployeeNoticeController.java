package employee.view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import tool.Controller;
import tool.UserTool;

public class EmployeeNoticeController extends Controller 
{
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
   	private Button btn_back;
    @FXML
   	private Button bt_language;
    @FXML
	private Label lb_workmenu;
    @FXML
	private Label lb_processSuccess;
    @FXML
	private Button btn_logout;
    
	private MainApp mainApp;
	
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
		
		lb_processSuccess.setText(UserTool.i18n.get("processSuccess"));
		btn_back.setText(UserTool.i18n.get("back"));
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
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
        setLanguageBtn();
        setVisible(false);
		btn_group.setOnMouseEntered((me) -> setVisible(true));
		btn_group.setOnMouseExited((me) -> setVisible(false));
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
