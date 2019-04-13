package client.view;

import model.Policy;
import net.sf.json.JSONArray;
import tool.UserTool;
import tool.Controller;
import tool.HttpTool;
import tool.PolicyTool;
import desktop.MainApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Pair;

//client-±£œ’¿Ì≈‚“≥
public class InsuranceController extends Controller 
{
	@FXML
	private TableView<Policy> tb_policys;
	@FXML
    private TableColumn<Policy, String> insurancePlanColumn;
	@FXML
    private TableColumn<Policy, String> guaranteePeriodColumn;
	@FXML
    private TableColumn<Policy, String> startingEndingDateColumn;
	@FXML
    private TableColumn<Policy, String> insuranceClaimColumn;
	@FXML
	private Button btn_home;
	@FXML
	private Button btn_insuranceService;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_myProfile;
	
    private ObservableList<Policy> policyData;
	private MainApp mainApp;

	private void initData() 
	{
		//------------------------------------------------------------Data Update---------------------------------------------
		Pair<Integer, String> reply = HttpTool.getArray("/insurances", UserTool.user.getToken());
		if(reply.getKey().equals(200))
		{
			JSONArray jarray = JSONArray.fromObject(reply.getValue());
			policyData = PolicyTool.initPolicyList(PolicyTool.getPolicyList(jarray));
		}		
		//------------------------------------------------------------GUI Update---------------------------------------------
		tb_policys.setItems(policyData);
		insurancePlanColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlanLevelProperty()));
		guaranteePeriodColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDurationProperty()));
		startingEndingDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartEndTimeProperty("Ireland")));		//test
		insuranceClaimColumn.setCellFactory(cellData -> 
    	{
            TableCell<Policy, String> cell = new TableCell<Policy, String>() 
            {

                @Override
                public void updateItem(String item, boolean empty) 
                {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) 
                    {
                        Button acceptBtn = new Button("Claim");
                        this.setGraphic(acceptBtn);
                        acceptBtn.setOnMouseClicked((me) -> 
                        {
                        	Policy policy = this.getTableView().getItems().get(this.getIndex());
                        	mainApp.showClaimApplicationView(policy.getId());
                        });
                    }
                }

            };
            return cell;
        });
		tb_policys.setRowFactory(tableview -> 
		{
			TableRow<Policy> row = new TableRow<Policy>();
			row.setOnMouseClicked(event -> 
			{
				Policy policy = row.getItem();
				mainApp.showInsuranceInformationView(policy);
			});
			return row ;
		});
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
	public void backToInsurance()
	{
		mainApp.showInsuranceView();
	}
	    
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
        initData();
    }
}
