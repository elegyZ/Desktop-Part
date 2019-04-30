package client.view;

import java.sql.Date;
import java.time.LocalDate;
import desktop.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Pair;
import model.Insured;
import model.Policy;
import tool.Controller;
import tool.DateTool;
import tool.PolicyTool;
import tool.UserTool;

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
	private Label lb_insuranceTypeInfo;
	@FXML
	private Label lb_basic;
	@FXML
	private Label lb_medium;
	@FXML
	private Label lb_premium;
	@FXML
	private Text text1;
	@FXML
	private Text text2;
	@FXML
	private Text text3;
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
	@FXML
	private Label lb_insuranceDurationInfo;
	@FXML
	private Label lb_30days;
	@FXML
	private Label lb_startdate;
	@FXML
	private Label lb_duration;
	@FXML
	private Label lb_period;
	@FXML
	private Label lb_expiredate;
	@FXML
	private Label lb_expiredatedate;
	@FXML
	private ChoiceBox<String> cb_nationcode;
	@FXML
	private Label lb_countrycode;
	
	private MainApp mainApp;
	private int plan;
	
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
		
		lb_expiredatedate.setText(DateTool.format(DateTool.getCurrentDate()));
		lb_insuranceTypeInfo.setText(UserTool.i18n.get("insuranceTypeInfo"));
		lb_basic.setText(UserTool.i18n.get("plan1"));
		lb_medium.setText(UserTool.i18n.get("plan2"));
		lb_premium.setText(UserTool.i18n.get("plan3"));
		text1.setText(UserTool.i18n.get("hinttext"));
		text2.setText(UserTool.i18n.get("hinttext"));
		text3.setText(UserTool.i18n.get("hinttext"));
		lb_insuredInformation.setText(UserTool.i18n.get("insuredInformation"));
		lb_firstname.setText(UserTool.i18n.get("firstname"));
		lb_lastname.setText(UserTool.i18n.get("lastname"));
		lb_gander.setText(UserTool.i18n.get("gander"));
		lb_age.setText(UserTool.i18n.get("age"));
		lb_ID.setText(UserTool.i18n.get("ID"));
		lb_phone.setText(UserTool.i18n.get("phone"));
		lb_email.setText(UserTool.i18n.get("email"));
		btn_male.setText(UserTool.i18n.get("male"));
		btn_female.setText(UserTool.i18n.get("female"));
		lb_accountForClaimPayment.setText(UserTool.i18n.get("accountForClaimPayment"));
		lb_bankName.setText(UserTool.i18n.get("bankName"));
		lb_bankAccountName.setText(UserTool.i18n.get("bankAccountName"));
		lb_bankAccount.setText(UserTool.i18n.get("bankAccount"));
		lb_insuranceDurationInfo.setText(UserTool.i18n.get("insuranceDuration"));
		lb_30days.setText(UserTool.i18n.get("30days"));
		lb_startdate.setText(UserTool.i18n.get("startdate"));
		lb_duration.setText(UserTool.i18n.get("duration"));
		lb_period.setText(UserTool.i18n.get("period"));
		lb_expiredate.setText(UserTool.i18n.get("period"));
		btn_submit.setText(UserTool.i18n.get("submit"));
		lb_countrycode.setText(UserTool.i18n.get("countrycode"));
		cb_nationcode.setItems(FXCollections.observableArrayList(UserTool.i18n.get("China"), UserTool.i18n.get("Ireland"), UserTool.i18n.get("US")));
		cb_nationcode.setValue(UserTool.i18n.get("China"));
		setPlan(plan);
	}
	
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
		
		tf_duration.textProperty().addListener(new ChangeListener<String>() 
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
            {
            	showExpireDate();
            }
        });
		
		cb_nationcode.setItems(FXCollections.observableArrayList(UserTool.i18n.get("China"), UserTool.i18n.get("Ireland"), UserTool.i18n.get("US")));
		cb_nationcode.setValue(UserTool.i18n.get("China"));
	}
	
	public String getCode()
	{
		String result = cb_nationcode.getValue();
		if(result.equals(UserTool.i18n.get("China")))
			return "+86";
		else if(result.equals(UserTool.i18n.get("Ireland")))
			return "+353";
		else
			return "+1";
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
			String phone = getCode() + tf_phone.getText();
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
			checkAlert(UserTool.i18n.get("PleaseInputTheInsuranceDuration"));
			return false;
		}
		if(tf_lastname.getText().equals(""))
		{
			checkAlert(UserTool.i18n.get("PleaseInputYourLastname"));
			return false;
		}
		if(tf_firstname.getText().equals(""))
		{
			checkAlert(UserTool.i18n.get("PleaseInputYourFirstname"));
			return false;
		}
		if(tf_socialId.getText().equals(""))
		{
			checkAlert(UserTool.i18n.get("PleaseInputYourSocialID"));
			return false;
		}
		if(tf_phone.getText().equals(""))
		{
			checkAlert(UserTool.i18n.get("PleaseInputYourPhoneNumber"));
			return false;
		}
		if(tf_email.getText().equals(""))
		{
			checkAlert(UserTool.i18n.get("PleaseInputYourEmail"));
			return false;
		}
		if(tf_bankUsername.getText().equals(""))
		{
			checkAlert(UserTool.i18n.get("PleaseInputYourBankAccountName"));
			return false;
		}
		if(tf_bankName.getText().equals(""))
		{
			checkAlert(UserTool.i18n.get("PleaseInputYourBankName"));
			return false;
		}
		if(tf_bankAccount.getText().equals(""))
		{
			checkAlert(UserTool.i18n.get("PleaseInputYourBankAccountNumber"));
			return false;
		}
		if(tf_age.getText().equals(""))
		{
			checkAlert(UserTool.i18n.get("PleaseInputYourAge"));
			return false;
		}
		else
			return true;
	}
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
        setLanguageBtn();
    }
	
	public void setPlan(int i)
	{
		plan = i;
		if(plan == 1)
		{
			cost1.setText(String.valueOf(500) + " " + UserTool.i18n.get("permonth"));
			cost2.setText(String.valueOf(800) + " " + UserTool.i18n.get("permonth"));
			cost3.setText(String.valueOf(1200) + " " + UserTool.i18n.get("permonth"));
			max1.setText(String.valueOf(1000) + UserTool.i18n.get("money"));
			max2.setText(String.valueOf(1600) + UserTool.i18n.get("money"));
			max3.setText(String.valueOf(2400) + UserTool.i18n.get("money"));
		}
		else if(plan == 2)
		{
			cost1.setText(String.valueOf(1500) + " " + UserTool.i18n.get("permonth"));
			cost2.setText(String.valueOf(1800) + " " + UserTool.i18n.get("permonth"));
			cost3.setText(String.valueOf(2200) + " " + UserTool.i18n.get("permonth"));
			max1.setText(String.valueOf(3000) + UserTool.i18n.get("money"));
			max2.setText(String.valueOf(4000) + UserTool.i18n.get("money"));
			max3.setText(String.valueOf(5000) + UserTool.i18n.get("money"));
		}
		else
		{
			cost1.setText(String.valueOf(3000) + " " + UserTool.i18n.get("permonth"));
			cost2.setText(String.valueOf(4000) + " " + UserTool.i18n.get("permonth"));
			cost3.setText(String.valueOf(5000) + " " + UserTool.i18n.get("permonth"));
			max1.setText(String.valueOf(8000) + UserTool.i18n.get("money"));
			max2.setText(String.valueOf(12000) + UserTool.i18n.get("money"));
			max3.setText(String.valueOf(20000) + UserTool.i18n.get("money"));
		}
	}

	public void showExpireDate()
	{
		if(!tf_duration.getText().equals(""))
			lb_expiredatedate.setText(DateTool.format(DateTool.dateCounter(Date.valueOf(startdate.getValue().toString()), Integer.parseInt(tf_duration.getText()))));
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
}
