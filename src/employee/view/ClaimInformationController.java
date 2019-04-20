package employee.view;

import java.io.File;
import java.util.Optional;
import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import model.Claim;
import tool.ClaimTool;
import tool.Controller;
import tool.HttpTool;
import tool.UserTool;

public class ClaimInformationController extends Controller 
{
	@FXML
	private Label locationOfAccident;
	@FXML
	private Label dateOfAccident;
	@FXML
	private Text claimReason;
	@FXML
	private Label amountOfDamage;
	@FXML
	private Text filenameList;
	@FXML
	private Button btn_download;
	@FXML
	private Button btn_accept;
	@FXML
	private Button btn_notAccept;
	@FXML
    private Line line_all;
    @FXML
    private Line line_pending;
    @FXML
    private Line line_processing;
    @FXML
    private Line line_closed;
    @FXML
    private VBox btn_group;
    @FXML
    private Button btn_pending;
    @FXML
    private Button btn_processing;
    @FXML
    private Button btn_closed;
	@FXML
    private Button btn_claimAffair;
    @FXML
    private Button btn_policyModification;
    @FXML
    private Button btn_myAccount;
	
	private MainApp mainApp;
	private Claim claim;
	private String type;
	private String filename = "";
	
	private ButtonType acceptType = new ButtonType("Accept", ButtonData.YES);
	/*
	 * private ButtonType notAcceptType = new ButtonType("Not Accept",
	 * ButtonData.YES); private ButtonType cancelType = new ButtonType("Cancel",
	 * ButtonData.CANCEL_CLOSE);
	 */
	private ButtonType okType = new ButtonType("OK", ButtonData.YES);
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
    }
	
	public void setType(String t)
	{
    	this.type = t;
    	if(type.equals("all"))
    	{
    		setVisible(false);
    		line_all.setStrokeWidth(2);
    		btn_group.setOnMouseEntered((me) -> setVisible(true));
    		btn_group.setOnMouseExited((me) -> setVisible(false));
    	}
    	else if(type.equals("pending"))
    		line_pending.setStrokeWidth(2);
    	else if(type.equals("processing"))
    		line_processing.setStrokeWidth(2);
    	else
    		line_closed.setStrokeWidth(2);
	}
	
	public void setVisible(Boolean bool)
    {
    	if(bool)
    	{
    		btn_pending.setVisible(true);
    		btn_processing.setVisible(true);
    		btn_closed.setVisible(true);
    		line_pending.setVisible(true);
    		line_processing.setVisible(true);
    		line_closed.setVisible(true);
    		btn_pending.setManaged(true);
    		btn_processing.setManaged(true);
    		btn_closed.setManaged(true);
    		line_pending.setManaged(true);
    		line_processing.setManaged(true);
    		line_closed.setManaged(true);
    	}
    	else
    	{
    		btn_pending.setVisible(false);
    		btn_processing.setVisible(false);
    		btn_closed.setVisible(false);
    		line_pending.setVisible(false);
    		line_processing.setVisible(false);
    		line_closed.setVisible(false);
    		btn_pending.setManaged(false);
    		btn_processing.setManaged(false);
    		btn_closed.setManaged(false);
    		line_pending.setManaged(false);
    		line_processing.setManaged(false);
    		line_closed.setManaged(false);
    	}
    }
	
	public void setClaim(Claim claim)
	{
		this.claim = claim;
		locationOfAccident.setText(claim.getAccLocation());
		dateOfAccident.setText(claim.getAccDate().toString());
		claimReason.setText(claim.getClaimReason());
		amountOfDamage.setText(String.valueOf(claim.getClaimAmount()));
		if(!claim.getClaimFiles().isEmpty())
		{
			for(File file:claim.getClaimFiles())
				filename += file.getName() + "\n";
			filenameList.setText(filename);
		}
		else
		{
			btn_download.setVisible(false);
			btn_download.setManaged(false);
		}
	}
	
	@FXML
	public void downloadFile()
	{
		String root = setDowmloadPath();
		for(File file:claim.getClaimFiles())
		{
			String lastpart = "/res/claim-files/" + claim.getId() + "/" + file;
			Pair<Integer, String> reply = HttpTool.download(lastpart, UserTool.user.getToken(), file.toString(), root);
			if(reply.getKey() == 200)
				successAlert("Files Had Been Downloaded Successfully.");
			else
				errorAlert(reply.getValue());
		}
	}
	
	public String setDowmloadPath()
	{
		DirectoryChooser directoryChooser=new DirectoryChooser();
		File file = directoryChooser.showDialog(new Stage());
		String path = file.getPath();
		return path;
	}
	
	@FXML
	private void acceptClaim()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Confirm Dialog");
		alert.setHeaderText("Do you accept this claim?");
		alert.setContentText("Please check it cautiously.");
		alert.getButtonTypes().clear();
		alert.getButtonTypes().add(acceptType);
		Optional<ButtonType> result = alert.showAndWait();
		if(result.isPresent())
		{
			claim.setStatus("accept");
			Pair<Integer, String> reply = ClaimTool.accept(claim);
			if(reply.getKey() == 200)
				mainApp.showClaimAffairNoticeView();
			else
				errorAlert(reply.getValue());
		}
	}
	
	@FXML
	private void notAcceptClaim()
	{
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Confirm Dialog");
		dialog.setHeaderText("Do you Not Accept this claim?");
		dialog.setContentText("Please enter the reason:");
		/*
		 * dialog.getDialogPane().getButtonTypes().clear();
		 * dialog.getDialogPane().getButtonTypes().add(notAcceptType);
		 * dialog.getDialogPane().getButtonTypes().add(cancelType);
		 */
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent() && !result.get().equals(" "))
		{
		    claim.setStatus("notAccept");
		    Pair<Integer, String> reply = ClaimTool.reject(claim, result.get());
			if(reply.getKey() == 200)
				mainApp.showClaimAffairNoticeView();
			else
				errorAlert(reply.getValue());
		}
		else
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText(null);
			alert.setContentText("You must enter a reason!");
			alert.getButtonTypes().clear();
			alert.getButtonTypes().add(okType);
			alert.showAndWait();
		}
	}
	
	@FXML
    public void getPendingAffair()
    {
    	mainApp.showClaimAffairView("pending");
    }
    
    @FXML
    public void getProcessingAffair()
    {
    	mainApp.showClaimAffairView("processing");
    }
    
    @FXML
    public void getClosedAffiar()
    {
    	mainApp.showClaimAffairView("closed");
    }
    
    @FXML
    private void getAllClaimAffiar()
    {  	
    	mainApp.showClaimAffairView("all");
    }
    
    @FXML
    public void toProfileView()
    {
    	mainApp.showEmployeeProfileView();
    }
}
