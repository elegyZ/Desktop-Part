package client.view;

import java.io.File;
import java.io.IOException;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Claim;
import tool.ClaimTool;
import tool.Controller;

//client-À˜≈‚…Í«Î»∑»œ“≥
public class ClaimApplicationCheckController extends Controller 
{
	@FXML
	private Label locationOfAcc;
	@FXML
	private Label dateOfAcc;
	@FXML
	private Text reasonOfClaim;
	@FXML
	private Label amountOfDamage;
	@FXML
	private Label typeOfAcc;
	@FXML
	private Text filenameList;
	@FXML
	private Button btn_home;
	@FXML
	private Button btn_myProfile;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_insuranceClaim;
	@FXML
	private Button btn_back;
	@FXML
	private Button btn_confirm;
	
	private MainApp mainApp;
	private Claim claim;
	private String filename = "";
	
	public void setClaim(Claim claim)
	{
		this.claim = claim;
		initView();
	}
	
	public void initView()
	{
		locationOfAcc.setText(claim.getAccLocation());
		dateOfAcc.setText(claim.getAccDate().toString());
		reasonOfClaim.setText(claim.getClaimReason());
		amountOfDamage.setText(String.valueOf(claim.getClaimAmount()));
		typeOfAcc.setText(claim.getTypeProperty(claim.getType()));
		for(File file:claim.getClaimFiles())
			filename += file.getName() + "\n";
		filenameList.setText(filename);
	}
	
	@FXML
	public void submit()
	{
		Pair<Integer, String> result = ClaimTool.post(claim);
		if (result.getKey() == 200)
			mainApp.showClientNoticeView("claim");
		else
			errorAlert("result");
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
	public void backToClaimApplication()
	{
		mainApp.showClaimApplicationView2(claim);
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
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;

    }

}
