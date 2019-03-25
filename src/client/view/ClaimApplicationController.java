package client.view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import client.desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import model.Claim;
import model.Client;
import model.Policy;
import test.TestCase;
import tool.DateTool;

//client-Ë÷ÅâÉêÇë
public class ClaimApplicationController 
{
	@FXML
	private TextField locationOfAcc;
	@FXML
	private DatePicker dateOfAcc;				//²Ù×÷¿ØÖÆ£¿
	@FXML
	private TextArea reasonOfClaim;
	@FXML
	private TextField amountOfDamage;
	@FXML
	private Button btn_upload;
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
	@FXML
	private Button btn_submit;
	
	private MainApp mainApp;
	private Client client = TestCase.testClient;
	private Policy policy;
	private Claim claim;
	
	public void setPolicy(String policyId)
	{
		this.policy = client.getPolicy().get(policyId);
	}
	
	public void setClaim()
	{
		claim = new Claim(null, policy.getId(), null, null, null, 0, null, "pending", DateTool.getCurrentDate(), DateTool.getCurrentDate());
		claim.setPolicyId("001"); 										//test
		claim.setAccLocation(locationOfAcc.getText());
		claim.setAccDate(Date.valueOf(dateOfAcc.getValue().toString()));
		claim.setClaimReason(reasonOfClaim.getText());
		claim.setClaimAmount(Float.valueOf(amountOfDamage.getText()));
		claim.setClaimFiles(new ArrayList<String>());					//test
	}
	
	public void check(String warning)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(null);
		alert.setContentText(warning);
		alert.getButtonTypes().clear();
		alert.getButtonTypes().add(new ButtonType("OK", ButtonData.YES));
		alert.showAndWait();
	}
	
	
	@FXML
	public void initialize()
	{
		reasonOfClaim.setText("Please describe in detail the reasons for applying for compensation.");
		dateOfAcc.setValue(LocalDate.now());
	}
	
	@FXML
	public void submit()
	{
		if(locationOfAcc.getText().equals(""))
			check("You must enter the location of accident.");
		else if(reasonOfClaim.getText().equals("Please describe in detail the reasons for applying for compensation."))
			check("You must enter the reason of accident.");
		else if(amountOfDamage.getText().equals(""))
			check("You must enter the amount of damage.");
		//else if(File)
		else
		{
			setClaim();
			//client.addClaim(policy.getId(), claim);
			mainApp.showClaimApplicationCheckView(policy.getId(), claim);
		}
	}
	
	@FXML
	public void addFile()
	{
		//code
	}

	@FXML
	public void backToInsuranceClaim()
	{
		mainApp.showInsuranceClaimView();
	}
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;

    }

}
