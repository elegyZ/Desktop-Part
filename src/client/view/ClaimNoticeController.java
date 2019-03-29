package client.view;

import client.desktop.ClientMainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

//client-¿Ì≈‚≥…π¶
public class ClaimNoticeController 
{
	@FXML
	private Button btn_home;
	@FXML
	private Button btn_insuranceService;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_insuranceClaim;
	@FXML
	private Button btn_back;
	
	private ClientMainApp mainApp;
	
	@FXML
	public void backToInsuranceClaim()
	{
		mainApp.showInsuranceClaimView();
	}
	
	public void setMainApp(ClientMainApp mainApp) 
    {
        this.mainApp = mainApp;
    }

}
