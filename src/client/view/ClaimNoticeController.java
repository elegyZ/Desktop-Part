package client.view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import tool.Controller;

//client-¿Ì≈‚≥…π¶
public class ClaimNoticeController  extends Controller 
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
	
	private MainApp mainApp;
	
	@FXML
	public void backToInsurance()
	{
		mainApp.showInsuranceView();
	}
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
    }

}
