package employee.view;

import desktop.MainApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.util.Pair;
import model.Claim;
import net.sf.json.JSONArray;
import tool.ClaimTool;
import tool.Controller;
import tool.HttpTool;
import tool.UserTool;

public class ClaimAffairController extends Controller 
{
	@FXML
	private TableView<Claim> tb_claims;
	@FXML
    private TableColumn<Claim, String> policyIdColumn;
    @FXML
    private TableColumn<Claim, String> insuranceTypeColumn;
    @FXML
    private TableColumn<Claim, String> dateOfSubmissionColumn;
    @FXML
    private TableColumn<Claim, String> amountOfDamageColumn;
    @FXML
    private TableColumn<Claim, String> acceptanceColumn;
    @FXML
    private ToggleButton bt_translate;
    @FXML
    private Button btn_insuranceClaim;
    @FXML
    private Button btn_policyModification;
    @FXML
    private Button btn_myAccount;
    
    private ObservableList<Claim> claimData;
    private MainApp mainApp;

    @FXML
    private void initialize() 
    {    	
    	//------------------------------------------------------------Data Update---------------------------------------------
    	Pair<Integer, String> reply = HttpTool.getArray("/claims", UserTool.user.getToken());
		if(reply.getKey().equals(200))
		{
			JSONArray jarray = JSONArray.fromObject(reply.getValue());
			claimData = ClaimTool.initClaimList(ClaimTool.getClaimList(jarray));
		}	
    	//else alert
    	//------------------------------------------------------------GUI Update---------------------------------------------
    	tb_claims.setItems(claimData);
    	policyIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPolicyId()));
    	insuranceTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTypeProperty(cellData.getValue().getType())));
    	dateOfSubmissionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSubmissionDateProperty("Ireland")));
    	amountOfDamageColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getClaimAmount())));
    	acceptanceColumn.setCellFactory((col) -> 
    	{
            TableCell<Claim, String> cell = new TableCell<Claim, String>() 
            {

                @Override
                public void updateItem(String item, boolean empty) 
                {
                    super.updateItem(item, empty);
                    if (!empty) 
                    {
                        Claim claim = this.getTableView().getItems().get(this.getIndex());
                        if(claim.getStatus().equals("pending"))
                        {
                        	Button bt_assign = new Button("Assign");
                            this.setGraphic(bt_assign);
                            bt_assign.setOnMouseClicked((assign) -> 
                            {
                            	claim.setStatus("processing");					//testing
                            	Pair<Integer, String> reply = ClaimTool.assign(claim);
                            	if(reply.getKey() == 200)
                            	{
                            		successAlert(reply.getValue());
                                	Button bt_process = new Button("Process");
                                    this.setGraphic(bt_process);
                                    bt_process.setOnMouseClicked((process) -> 
                                    {
                                    	
                                    });
                            	}
                            	else
                            		errorAlert(reply.getValue());
                            });
                        }
                        else if(claim.getStatus().equals("processing"))
                        {
                        	Button bt_process = new Button("Process");
                            this.setGraphic(bt_process);
                            bt_process.setOnMouseClicked((process) -> 
                            {
                            	mainApp.showClaimInformationView(claim);
                            });
                        }
                    }
                }

            };
            return cell;
        });
    }
    
    @FXML
    private void backToInsuranceClaim()
    {
    	
    	mainApp.showClaimAffairView();
    }
    
    @FXML
    private void changeLanguage()
    {
    	if(bt_translate.isSelected())
    	{
    		bt_translate.setText("English");
    		btn_insuranceClaim.setText("保险理赔");
    	}
    	else
    	{
    		bt_translate.setText("中文");
    		btn_insuranceClaim.setText("InsuranceClaim");
    	}
    }
    
    public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
    }
}
