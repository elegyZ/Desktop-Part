package client.view;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import desktop.MainApp;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Claim;
import tool.UserTool;
import tool.Controller;
import tool.DateTool;

//client-Ë÷ÅâÉêÇë
public class ClaimApplicationController  extends Controller 
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
	private ImageView iv_next1;
	@FXML
	private ImageView iv_next2;
	@FXML
	private ImageView iv_next3;
	@FXML
	private ChoiceBox<String> accType;
	@FXML
	private Button btn_upload;
	@FXML
	private ImageView iv_upload;
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
	private String policyId;
	private Claim claim;
	List<File> fileList = new ArrayList<File>();
	
	public void setPolicy(String policyId)
	{
		this.policyId = policyId;
	}
	
	public void InitClaim(Claim claim)
	{
		this.claim = claim;
		locationOfAcc.setText(claim.getAccLocation());
		dateOfAcc.setValue(LocalDate.parse(claim.getAccDate().toString()));
		reasonOfClaim.setText(claim.getClaimReason());
		amountOfDamage.setText(String.valueOf(claim.getClaimAmount()));
		fileList = claim.getClaimFiles();
	}
	
	public void setClaim()
	{
		String userId = UserTool.user.getUserId();
		String accLocation = locationOfAcc.getText();
		Date accDate = Date.valueOf(dateOfAcc.getValue().toString());
		float claimAmount = Float.valueOf(amountOfDamage.getText());
		String claimReason = reasonOfClaim.getText();
		List<File> claimFiles = null;		//test
		String status = "pending";
		String employeeId = "";
		int type = getType();
		claim = new Claim("id",  policyId,  userId,  type,  accLocation,  accDate,  claimReason,  claimAmount, claimFiles,  status,
				  employeeId,  DateTool.getCurrentDate(),  DateTool.getCurrentDate());
	}
	
	public void setFileNotation(String filelist)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../client/view/ClaimFileView.fxml"));
			VBox claimFileView = (VBox) loader.load();
			claimFileView.setStyle("-fx-background-color: white;");
			Stage fileStage = new Stage();
			Scene fileScene = new Scene(claimFileView, 500, 500);
			ClaimFileController controller = loader.getController();
			controller.setFileText(filelist);
			fileStage.setScene(fileScene);
			fileStage.show();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public int getType()
	{
		String result = accType.getValue();
		if(result.equals("First Type"))
			return 1;
		else if(result.equals("Second Type"))
			return 2;
		else
			return 3;
	}
	
	@FXML
	public void initialize()
	{
		accType.setItems(FXCollections.observableArrayList("First Type", "Second Type", "Third Type"));
		accType.setValue("First Type");
		reasonOfClaim.setText("Please describe in detail the reasons for applying for compensation.");
		reasonOfClaim.setOnMouseClicked((me) -> 
		{
			if (reasonOfClaim.getText().equals("Please describe in detail the reasons for applying for compensation."))
				reasonOfClaim.setText("");
		});
		dateOfAcc.setValue(LocalDate.now());
		iv_upload.setVisible(false);
	}
	
	@FXML
	public void setNextButton1()
	{
		setFileNotation("1. The personal ID of the insured\r\n"
				+ "2. Certified documents of the travel of the insured\r\n"
				+ "3. The driver license of the insured\r\n"
				+ "4. Written accident report\r\n"
				+ "5. Police report\r\n"
				+ "6. Car rental contract\r\n"
				+ "7. Car rental company's loss amount and maintenance fee list\r\n"
				+ "8. Compensation certificate\r\n"
				+ "9. Rental vehicle collision insurance or stolen insurance policy(If necessary)\r\n"
				+ "10. Valid proof of rental vehicles(If necessary)\r\n"
				+ "11. Other certification materials");
	}
	
	@FXML
	public void setNextButton2()
	{
		setFileNotation("1. Original proof of delay issued by the carrier, indicating the time and reason for the delay\r\n"
						+ "2. Air ticket, ferry ticket\r\n"
						+ "3. Express order of delayed baggage");
	}
	
	@FXML
	public void setNextButton3()
	{
		setFileNotation("1. Local police original record( must alarm within 24 hours of the accident)\r\n"
				+ "2. Original written certificates of hotel, carrier and other relevant department\r\n"
				+ "3. Loss list and invoice(specify the date and amount of purchase in detai)\r\n"
				+ "4. Proof of source of cash and cheque\r\n"
				+ "5. Reset the invoice for passport and travel documents or original receipt\r\n"
				+ "6. Invoice for travel and accommodation expenses for additional expenses or original receipt");
	}
	
	@FXML
	public void addFile()
	{
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		List<File> list =  fileChooser.showOpenMultipleDialog(stage);
		if (list != null) 
		{  
			list.stream().forEach((file) -> 
			{
				fileList.add(file);
				iv_upload.setVisible(true);			//test
				//System.out.println(file);
			});			
		}
	}
	
	@FXML
	public void submit()
	{
		if(locationOfAcc.getText().equals(""))
			checkAlert("You must enter the location of accident.");
		else if(reasonOfClaim.getText().equals(""))
			checkAlert("You must enter the reason of accident.");
		else if(amountOfDamage.getText().equals(""))
			checkAlert("You must enter the amount of damage.");
		else if(fileList.size() == 0)
			checkAlert("You must update requied files.");
		else
		{
			setClaim();
			mainApp.showClaimApplicationCheckView(claim);
		}
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
