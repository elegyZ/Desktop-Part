package client.view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Insured;
import model.Policy;
import tool.Controller;
import tool.UserTool;

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
	private Button btn_home;
	@FXML
	private Button btn_logout;
	@FXML
	private Button btn_myClaim;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_aboutUs;
	@FXML
	private Button bt_language;
	@FXML
	private Label lb_clientService;
	@FXML
	private Label lb_insuranceDetails;
	@FXML
	private Label lb_insurancePolicy;
	@FXML
	private Label lb_policyId;
	@FXML
	private Label lb_insurancePlan;
	@FXML
	private Label lb_insuranceLevel;
	@FXML
	private Label lb_insuranceStartdate;
	@FXML
	private Label lb_insuranceDuration;
	@FXML
	private Label lb_insuranceExpiredate;
	@FXML
	private Label lb_insuredInformation;
	@FXML
	private Label lb_firstname;
	@FXML
	private Label lb_lastname;
	@FXML
	private Label lb_gander;
	@FXML
	private Label lb_age;
	@FXML
	private Label lb_ID;
	@FXML
	private Label lb_phone;
	@FXML
	private Label lb_email;
	@FXML
	private Label lb_accountForClaimPayment;
	@FXML
	private Label lb_bankName;
	@FXML
	private Label lb_bankAccountName;
	@FXML
	private Label lb_bankAccount;
	
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
		lb_clientService.setText(UserTool.i18n.get("clientservice"));
		btn_home.setText(UserTool.i18n.get("home"));
		btn_myInsurance.setText(UserTool.i18n.get("myinsurance"));
		btn_myClaim.setText(UserTool.i18n.get("myclaim"));
		btn_aboutUs.setText(UserTool.i18n.get("aboutus"));
		btn_logout.setText(UserTool.i18n.get("logout"));
		
		lb_insuranceDetails.setText(UserTool.i18n.get("insuredDetail"));
		lb_insurancePolicy.setText(UserTool.i18n.get("insurancePolicy"));
		lb_policyId.setText(UserTool.i18n.get("policyId"));
		lb_insurancePlan.setText(UserTool.i18n.get("insurancePlan"));
		lb_insuranceLevel.setText(UserTool.i18n.get("insuranceLevel"));
		lb_insuranceStartdate.setText(UserTool.i18n.get("insuranceStartdate"));
		lb_insuranceDuration.setText(UserTool.i18n.get("insuranceDuration"));
		lb_insuranceExpiredate.setText(UserTool.i18n.get("insuranceExpiredate"));
		lb_insuredInformation.setText(UserTool.i18n.get("insuredInformation"));
		lb_firstname.setText(UserTool.i18n.get("firstname"));
		lb_lastname.setText(UserTool.i18n.get("lastname"));
		lb_gander.setText(UserTool.i18n.get("gander"));
		lb_age.setText(UserTool.i18n.get("age"));
		lb_ID.setText(UserTool.i18n.get("ID"));
		lb_phone.setText(UserTool.i18n.get("phone"));
		lb_email.setText(UserTool.i18n.get("email"));
		lb_accountForClaimPayment.setText(UserTool.i18n.get("accountForClaimPayment"));
		lb_bankName.setText(UserTool.i18n.get("bankName"));
		lb_bankAccountName.setText(UserTool.i18n.get("bankAccountName"));
		lb_bankAccount.setText(UserTool.i18n.get("bankAccount"));
		btn_back.setText(UserTool.i18n.get("back"));
	}
	
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
	
	@FXML
	public void toAboutUs()
	{
		mainApp.showAboutUsView();
	}
	
	@FXML
	public void logout()
	{
		mainApp.showLogInView();
	}
	
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
		setLanguageBtn();
	}
}
