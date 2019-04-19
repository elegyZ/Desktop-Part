package client.view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import tool.Controller;

public class HomeController extends Controller 
{
	@FXML
	private ScrollPane pane;
	@FXML
	private Button bt_plan1;
	@FXML
	private Button bt_plan2;
	@FXML
	private Button bt_plan3;
	@FXML
	private Button btn_home;
	@FXML
	private Button btn_myProfile;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_insuranceClaim;
	private MainApp mainApp;
	
	@FXML
	public void toPlan1()
	{
		mainApp.showInsurancePurchaseView(1);
	}
	
	@FXML
	public void toPlan2()
	{
		mainApp.showInsurancePurchaseView(2);
	}
	
	@FXML
	public void toPlan3()
	{
		mainApp.showInsurancePurchaseView(3);
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
	public void backToInsurance()
	{
		mainApp.showInsuranceView();
	}
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
    }
	
	public void setPane()
	{
		this.pane.setVvalue(0);
		System.out.println("***");			//test
	}
}
