package client.view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tool.Controller;

public class ClientProfileController extends Controller 
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
	private Button btn_home;
	@FXML
	private Button btn_myProfile;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_myClaim;
	
	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
    }
}
