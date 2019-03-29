package employee.view;

import employee.desktop.EmployeeMainApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import model.Claim;
import test.TestCase;
import tool.ClaimTool;
import tool.HttpTool;
import tool.PolicyTool;

public class ClaimAffairController 
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
    private EmployeeMainApp mainApp;

    @FXML
    private void initialize() 
    {    	
    	//------------------------------------------------------------Data Update---------------------------------------------
    	claimData = ClaimTool.initClaimList(ClaimTool.getClaimList(HttpTool.getArray(TestCase.claim_array)));
    	
    	//------------------------------------------------------------GUI Update---------------------------------------------
    	tb_claims.setItems(claimData);
    	policyIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPolicyId()));
    	insuranceTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(PolicyTool.getPolicyObject(HttpTool.getObject(cellData.getValue().getPolicyId())).getPlanLevelProperty()));
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
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) 
                    {
                        Button acceptBtn = new Button("Accept");
                        this.setGraphic(acceptBtn);
                        acceptBtn.setOnMouseClicked((me) -> 
                        {
                        	Claim claim = this.getTableView().getItems().get(this.getIndex());
                        	claim.setStatus("processing");					//testing
                        	//POST修改操作
                        	mainApp.showClaimInformationView(claim);
                        });
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
    
    public void setMainApp(EmployeeMainApp mainApp) 
    {
        this.mainApp = mainApp;
    }
}
