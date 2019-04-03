package employee.view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import tool.Controller;

public class ClaimAffairNoticeController extends Controller 
{
	@FXML
	private Button btn_back;
	@FXML
    private Button btn_insuranceClaim;
    @FXML
    private Button btn_policyModification;
    @FXML
    private Button btn_myAccount;
	private MainApp mainApp;
	
	@FXML
	public void backToClaimAffair()
	{
		mainApp.showClaimAffairView();
	}
	
	@FXML
    private void backToInsuranceClaim()
    {
    	mainApp.showClaimAffairView();
    }
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
    }
}
