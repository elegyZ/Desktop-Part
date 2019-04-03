package client.view;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Claim;
import tool.ClientTool;
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
		claim = new Claim("claim_id", policyId, ClientTool.userId, null, null, null, 0, null, "pending", "", DateTool.getCurrentDate(), DateTool.getCurrentDate());
		claim.setAccLocation(locationOfAcc.getText());
		claim.setAccDate(Date.valueOf(dateOfAcc.getValue().toString()));
		claim.setClaimReason(reasonOfClaim.getText());
		claim.setClaimAmount(Float.valueOf(amountOfDamage.getText()));
		claim.setClaimFiles(fileList);
	}
	
	public void setFileNotation(String filelist)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../client/view/ClaimFileView.fxml"));
			AnchorPane claimFileView = (AnchorPane) loader.load();
			claimFileView.setStyle("-fx-background-color: white;");
			Stage fileStage = new Stage();
			Scene fileScene = new Scene(claimFileView, 400, 400);
			ClaimFileController controller = loader.getController();
			controller.setFileText(filelist);
			fileStage.setScene(fileScene);
			fileStage.show();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
	public void initialize()
	{
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
		setFileNotation("123");
	}
	
	@FXML
	public void setNextButton2()
	{
		setFileNotation("123");
	}
	
	@FXML
	public void setNextButton3()
	{
		setFileNotation("123");
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
