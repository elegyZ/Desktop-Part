package employee.view;

import employee.desktop.MainApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import employee.model.Claim;
import employee.test.TestCase;
import tool.ClaimTool;

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
    private Button btn_insuranceClaim;
    @FXML
    private Button btn_policyModification;
    @FXML
    private Button btn_myAccount;
    
    private TestCase testcase = new TestCase();
    private ObservableList<Claim> claimData = TestCase.claimData;
    private MainApp mainApp;

    @FXML
    private void initialize() 
    {    	
    	ClaimTool.refreshClaimList(claimData);
    	tb_claims.setItems(claimData);
    	policyIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPolicyId()));
    	insuranceTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAccLocation()));		//test
    	dateOfSubmissionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUpdateDate().toString()));
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
                        	claim.setStatus("processing");
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
    
    public void setMainApp(MainApp mainApp) 
    {
    	if(TestCase.flag == 0)		//for testing
    	{
    		testcase.addClaimList();
    		TestCase.flag = 1;
    	}
        this.mainApp = mainApp;
    }
}
