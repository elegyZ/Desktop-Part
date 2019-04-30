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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
	private Text filenameList;
	@FXML
	private Button btn_home;
	@FXML
	private Button btn_logout;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_insuranceClaim;
	@FXML
	private Button btn_aboutUs;
	@FXML
	private Button btn_back;
	@FXML
	private Button btn_submit;
	@FXML
	private Button bt_language;
	@FXML
	private Label lb_clientService;
	@FXML
	private Label lb_claimApplicationInfo;
	@FXML
	private Label lb_subject;
	@FXML
	private Label lb_location;
	@FXML
	private Label lb_date;
	@FXML
	private Label lb_amount;
	@FXML
	private Label lb_typeOfAccInfo;
	@FXML
	private Label lb_clickToGet;
	@FXML
	private Text lb_acc1;
	@FXML
	private Text lb_acc2;
	@FXML
	private Text lb_acc3;
	@FXML
	private Label lb_acctype;
	@FXML
	private Label lb_claimSupportFile;
	
	private MainApp mainApp;
	private String policyId;
	private Claim claim;
	private List<File> fileList = new ArrayList<File>();
	private String filename = "";
	
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
		btn_insuranceClaim.setText(UserTool.i18n.get("myclaim"));
		btn_aboutUs.setText(UserTool.i18n.get("aboutus"));
		btn_logout.setText(UserTool.i18n.get("logout"));
		
		lb_claimApplicationInfo.setText(UserTool.i18n.get("claimApplicationInfo"));
		lb_subject.setText(UserTool.i18n.get("subject"));
		lb_location.setText(UserTool.i18n.get("location"));
		lb_date.setText(UserTool.i18n.get("date"));
		lb_amount.setText(UserTool.i18n.get("amount"));
		lb_typeOfAccInfo.setText(UserTool.i18n.get("typeOfAccInfo"));
		lb_clickToGet.setText(UserTool.i18n.get("clickToGet"));
		lb_acc1.setText(UserTool.i18n.get("acc1"));
		lb_acc2.setText(UserTool.i18n.get("acc2"));
		lb_acc3.setText(UserTool.i18n.get("acc3"));
		lb_acctype.setText(UserTool.i18n.get("acctype"));
		lb_claimSupportFile.setText(UserTool.i18n.get("claimSupportFile"));
		btn_upload.setText(UserTool.i18n.get("upload"));
		btn_back.setText(UserTool.i18n.get("back"));
		btn_submit.setText(UserTool.i18n.get("submit"));
		accType.setItems(FXCollections.observableArrayList(UserTool.i18n.get("FirstType"), UserTool.i18n.get("SecondType"), UserTool.i18n.get("ThirdType")));
		accType.setValue(UserTool.i18n.get("FirstType"));
	}
	
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
		for(File file:claim.getClaimFiles())
			filename += file.getName() + "\n";
		filenameList.setText(filename);
	}
	
	public void setClaim()
	{
		String userId = UserTool.user.getUserId();
		String accLocation = locationOfAcc.getText();
		Date accDate = Date.valueOf(dateOfAcc.getValue().toString());
		float claimAmount = Float.valueOf(amountOfDamage.getText());
		String claimReason = reasonOfClaim.getText();
		List<File> claimFiles = fileList;
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
		if(result.equals(UserTool.i18n.get("FirstType")))
			return 1;
		else if(result.equals(UserTool.i18n.get("SecondType")))
			return 2;
		else
			return 3;
	}
	
	@FXML
	public void initialize()
	{
		accType.setItems(FXCollections.observableArrayList(UserTool.i18n.get("FirstType"), UserTool.i18n.get("SecondType"), UserTool.i18n.get("ThirdType")));
		accType.setValue(UserTool.i18n.get("FirstType"));
		reasonOfClaim.setPromptText(UserTool.i18n.get("Pleasedescribeindetailthereasonsforapplyingforcompensation"));
		dateOfAcc.setValue(LocalDate.now());
	}
	
	@FXML
	public void setNextButton1()
	{
		setFileNotation(UserTool.i18n.get("filelist1"));
	}
	
	@FXML
	public void setNextButton2()
	{
		setFileNotation(UserTool.i18n.get("filelist2"));
	}
	
	@FXML
	public void setNextButton3()
	{
		setFileNotation(UserTool.i18n.get("filelist3"));
	}
	
	@FXML
	public void addFile()
	{
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(UserTool.i18n.get("OpenResourceFile"));
		List<File> list =  fileChooser.showOpenMultipleDialog(stage);
		if (list != null) 
		{  
			filename = "";
			fileList.clear();
			list.stream().forEach((file) -> 
			{
				fileList.add(file);
				filename += file + "\n";
			});	
			filenameList.setText(filename);
		}
	}
	
	@FXML
	public void submit()
	{
		if(locationOfAcc.getText().equals(""))
			checkAlert(UserTool.i18n.get("Youmustenterthelocationofaccident"));
		else if(reasonOfClaim.getText().equals(""))
			checkAlert(UserTool.i18n.get("Youmustenterthereasonofclaim"));
		else if(amountOfDamage.getText().equals(""))
			checkAlert(UserTool.i18n.get("Youmustentertheamountofdamage"));
		else if(fileList.size() == 0)
			checkAlert(UserTool.i18n.get("Youmustupdaterequiedfiles"));
		else
		{
			setClaim();
			mainApp.showClaimApplicationCheckView(claim);
		}
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
