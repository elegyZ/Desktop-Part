package client.view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tool.Controller;
import tool.NoticeTool;

public class ClientNoticeController extends Controller 
{
	@FXML
	private Label hintwords;
	@FXML
	private Button btn_act;
	@FXML
	private Button btn_home;
	@FXML
	private Button btn_myProfile;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_insuranceClaim;
	private MainApp mainApp;
	private String type;
	
	public void setType(String t)
	{
		this.type = t;
		if(type.equals("claim"))
		{
			hintwords.setText(NoticeTool.ClaimNoticeWords);
			btn_act.setText(NoticeTool.ClaimNoticeButton);
			btn_act.setOnMouseClicked((me) -> {
				mainApp.showInsuranceView();
			});
		}
		else if(type.equals("policy"))
		{
			hintwords.setText(NoticeTool.PolicyNoticeWords);
			btn_act.setText(NoticeTool.PolicyNoticeButton);
			btn_act.setOnMouseClicked((me) -> {
				mainApp.showHomeView();
			});
		}
		else if(type.equals("no profile"))
		{
			hintwords.setText(NoticeTool.NoProfileNoticeWords);
			btn_act.setText(NoticeTool.NoProfieNoticeButton);
			btn_act.setOnMouseClicked((me) -> {
				mainApp.showProfileCreateView();
			});
		}
	}
	
	@FXML
	public void buttonAction()
	{
		mainApp.showHomeView();
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
}
