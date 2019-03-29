package employee.view;

import employee.desktop.EmployeeMainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClaimNoticeController 
{
	@FXML
	private Button btn_back;
	@FXML
    private Button btn_insuranceClaim;
    @FXML
    private Button btn_policyModification;
    @FXML
    private Button btn_myAccount;
	private EmployeeMainApp mainApp;
	
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
	
	public void setMainApp(EmployeeMainApp mainApp) 
    {
        this.mainApp = mainApp;
    }
}
