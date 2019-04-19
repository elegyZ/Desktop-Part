package client.view;

import java.sql.Date;
import java.time.LocalDate;
import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import model.Insured;
import model.Policy;
import tool.Controller;
import tool.PolicyTool;

public class InsurancePurchaseController extends Controller 
{
	@FXML
	private Label cost1;
	@FXML
	private Label cost2;
	@FXML
	private Label cost3;
	@FXML
	private Label max1;
	@FXML
	private Label max2;
	@FXML
	private Label max3;
	@FXML
	private CheckBox check1;
	@FXML
	private CheckBox check2;
	@FXML
	private CheckBox check3;
	@FXML
	private TextField tf_firstname;
	@FXML
	private TextField tf_lastname;
	@FXML
	private TextField tf_socialId;
	@FXML
	private RadioButton btn_male;
	@FXML
	private RadioButton btn_female;
	@FXML
	private TextField tf_age;
	@FXML
	private TextField tf_phone;
	@FXML
	private TextField tf_email;
	@FXML
	private TextField tf_bankUsername;
	@FXML
	private TextField tf_bankName;
	@FXML
	private TextField tf_bankAccount;
	@FXML
	private DatePicker startdate;
	@FXML
	private TextField tf_duration;
	@FXML
	private Button btn_submit;
	@FXML
	private Button btn_home;
	@FXML
	private Button btn_myProfile;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_insuranceClaim;
	private MainApp mainApp;
	private int plan;
	
	@FXML
	public void initialize()
	{
		startdate.setValue(LocalDate.now());
		check1.setSelected(true);
		check1.setOnMouseClicked((me) -> {
			check2.setSelected(false);
			check3.setSelected(false);
		});
		
		check2.setOnMouseClicked((me) -> {
			check1.setSelected(false);
			check3.setSelected(false);
		});
		
		check3.setOnMouseClicked((me) -> {
			check1.setSelected(false);
			check2.setSelected(false);
		});
		btn_male.setSelected(true);
		btn_male.setOnMouseClicked((me) -> {
			btn_female.setSelected(false);
		});
		btn_female.setOnMouseClicked((me) -> {
			btn_male.setSelected(false);
		});
	}
	
	@FXML
	public void submit()
	{
		if(check())
		{
			int level = check1.isSelected() ? 1 : (check2.isSelected() ? 2 : (check3.isSelected() ? 3 : 1));
			Date startDate = Date.valueOf(startdate.getValue().toString());
			int duration = Integer.parseInt(tf_duration.getText());
			String lastname = tf_lastname.getText();
			String firstname = tf_firstname.getText();
			String socialId = tf_socialId.getText();
			String gender = (btn_male.isSelected()?"male":"female");
			int age = Integer.valueOf(tf_age.getText());
			String phone = tf_phone.getText();
			String email = tf_email.getText();
			String bankUsername = tf_bankUsername.getText();
			String bankName = tf_bankName.getText();
			String bankAccount = tf_bankAccount.getText();
			Insured insured = new Insured("id", lastname, firstname, socialId, gender, age, phone, email, bankUsername, bankName, bankAccount, null);
			Policy policy = new Policy("id", plan, level, startDate, duration, null, null, insured, false);
			Pair<Integer, String> reply = PolicyTool.postPolicy(PolicyTool.policyToJSONObject(policy));
			if(reply.getKey() == 200)
				mainApp.showClientNoticeView("policy");
			else
				errorAlert(reply.getValue());
		}
	}
	
	public Boolean check()
	{
		if(tf_duration.getText().equals(""))
		{
			checkAlert("Please Input The Insurance Duration.");
			return false;
		}
		if(tf_lastname.getText().equals(""))
		{
			checkAlert("Please Input Your Lastname.");
			return false;
		}
		if(tf_firstname.getText().equals(""))
		{
			checkAlert("Please Input Your Firstname.");
			return false;
		}
		if(tf_socialId.getText().equals(""))
		{
			checkAlert("Please Input Your SocialID.");
			return false;
		}
		if(tf_phone.getText().equals(""))
		{
			checkAlert("Please Input Your Phone Number.");
			return false;
		}
		if(tf_email.getText().equals(""))
		{
			checkAlert("Please Input Your Email.");
			return false;
		}
		if(tf_bankUsername.getText().equals(""))
		{
			checkAlert("Please Input Your Bank Account Name.");
			return false;
		}
		if(tf_bankName.getText().equals(""))
		{
			checkAlert("Please Input Your Bank Name.");
			return false;
		}
		if(tf_bankAccount.getText().equals(""))
		{
			checkAlert("Please Input Your Bank Account Number.");
			return false;
		}
		if(tf_age.getText().equals(""))
		{
			checkAlert("Please Input Your Age.");
			return false;
		}
		else
			return true;
	}
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
    }
	
	public void setPlan(int i)
	{
		plan = i;
		if(plan == 1)
		{
			cost1.setText(String.valueOf(500));
			cost2.setText(String.valueOf(800));
			cost3.setText(String.valueOf(1200));
			max1.setText(String.valueOf(1000));
			max2.setText(String.valueOf(1600));
			max3.setText(String.valueOf(2400));
		}
		else if(plan == 2)
		{
			cost1.setText(String.valueOf(1500));
			cost2.setText(String.valueOf(1800));
			cost3.setText(String.valueOf(2200));
			max1.setText(String.valueOf(3000));
			max2.setText(String.valueOf(4000));
			max3.setText(String.valueOf(5000));
		}
		else
		{
			cost1.setText(String.valueOf(3000));
			cost2.setText(String.valueOf(4000));
			cost3.setText(String.valueOf(5000));
			max1.setText(String.valueOf(8000));
			max2.setText(String.valueOf(12000));
			max3.setText(String.valueOf(20000));
		}
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
}
