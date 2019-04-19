package client.view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Insured;
import model.Policy;
import tool.Controller;

public class InsuranceInformationController extends Controller 
{
	@FXML
	private Label policyId;
	@FXML
	private Label plan;
	@FXML
	private Label level;
	@FXML
	private Label startDate;
	@FXML
	private Label duration;
	@FXML
	private Label expireDate;
	@FXML
	private Label firstname;
	@FXML
	private Label lastname;
	@FXML
	private Label gender;
	@FXML
	private Label age;
	@FXML
	private Label agesocialId;
	@FXML
	private Label phone;
	@FXML
	private Label email;
	@FXML
	private Label bankAccount;
	@FXML
	private Label bankUsername;
	@FXML
	private Label bankName;
	@FXML
	private Button btn_back;
	@FXML
	private Button btn_modify;
	@FXML
	private Button btn_home;
	@FXML
	private Button btn_insuranceService;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_insuranceClaim;
	
	private MainApp mainApp;
	
	public void setPolicy(Policy p)
	{
		Policy policy = p;
		Insured insured = policy.getInsured();
		policyId.setText(policy.getId());
		plan.setText(policy.getPlanProperty());
		level.setText(policy.getLevelProperty());
		startDate.setText(policy.getStartTimeProperty("Ireland"));			//test
		duration.setText(policy.getDurationProperty());
		expireDate.setText(policy.getEndTimeProperty("Ireland"));			//test
		firstname.setText(insured.getFirstname());
		lastname.setText(insured.getLastname());
		gender.setText(insured.getGender());
		age.setText(String.valueOf(insured.getAge()));
		agesocialId.setText(insured.getSocialId());
		phone.setText(insured.getPhone());
		email.setText(insured.getEmail());
		bankAccount.setText(insured.getBankAccount());
		bankUsername.setText(insured.getBankUserName());
		bankName.setText(insured.getBankName());
	}
	
	@FXML
	public void toHome()
	{
		mainApp.showHomeView();
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
