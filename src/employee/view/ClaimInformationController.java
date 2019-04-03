package employee.view;

import java.util.Optional;
import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import model.Claim;
import tool.Controller;

public class ClaimInformationController extends Controller 
{
	@FXML
    private Button btn_insuranceClaim;
    @FXML
    private Button btn_policyModification;
    @FXML
    private Button btn_myAccount;
	@FXML
	private Label locationOfAccident;
	@FXML
	private Label dateOfAccident;
	@FXML
	private TextArea claimReason;
	@FXML
	private Label amountOfDamage;
	@FXML
	private Button btn_accept;
	@FXML
	private Button btn_notAccept;
	
	private MainApp mainApp;
	private Claim claim;
	
	private ButtonType acceptType = new ButtonType("Accept", ButtonData.YES);
	//private ButtonType notAcceptType = new ButtonType("Not Accept");
	//private ButtonType cancelType = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	private ButtonType okType = new ButtonType("OK", ButtonData.YES);
	
	public void setClaim(Claim claim)
	{
		this.claim = claim;
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
			mainApp.showClaimNoticeView();
		}
	}
	
	@FXML
	private void notAcceptClaim()
	{
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Confirm Dialog");
		dialog.setHeaderText("Do you Not Accept this claim?");
		dialog.setContentText("Please enter the reason:");
		//dialog.getDialogPane().getButtonTypes().clear();
		//dialog.getDialogPane().getButtonTypes().add(notAcceptType);
		//dialog.getDialogPane().getButtonTypes().add(cancelType);
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent() && !result.get().equals(" "))
		{
		    System.out.println("reason: " + result.get());	//reason testing
		    claim.setStatus("notAccept");
		    mainApp.showClaimNoticeView();
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
    private void backToInsuranceClaim()
    {
		claim.setStatus("pending");
		//POSTÐÞ¸Ä²Ù×÷
    	mainApp.showClaimAffairView();
    }
	
	public void setMainApp(MainApp mainApp) 
    {
		locationOfAccident.setText(claim.getAccLocation());
		dateOfAccident.setText(claim.getAccDate().toString());
		claimReason.setText(claim.getClaimReason());
		amountOfDamage.setText(String.valueOf(claim.getClaimAmount()));
        this.mainApp = mainApp;

    }
}
