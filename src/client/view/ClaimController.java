package client.view;

import desktop.MainApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Pair;
import model.Claim;
import net.sf.json.JSONArray;
import tool.ClaimTool;
import tool.UserTool;
import tool.Controller;
import tool.HttpTool;

public class ClaimController extends Controller 
{
	@FXML
	private TableView<Claim> tb_claims;
	@FXML
    private TableColumn<Claim, String> claimPlanColumn;
	@FXML
    private TableColumn<Claim, String> claimPeriodColumn;
	@FXML
    private TableColumn<Claim, String> claimDateColumn;
	@FXML
    private TableColumn<Claim, String> claimProgressColumn;
	@FXML
	private Button btn_home;
	@FXML
	private Button btn_insuranceService;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_insuranceClaim;
	
	private ObservableList<Claim> claimData;
	private MainApp mainApp;
	
	private void initData() 
	{
		//------------------------------------------------------------Data Update---------------------------------------------
		Pair<Integer, String> reply = HttpTool.getArray("/claims?user=" + UserTool.user.getUserId(), UserTool.user.getToken());
		if(reply.getKey().equals(200))
		{
			JSONArray jarray = JSONArray.fromObject(reply.getValue());
			claimData = ClaimTool.initClaimList(ClaimTool.getClaimList(jarray));
		}		
		//------------------------------------------------------------GUI Update---------------------------------------------
		tb_claims.setItems(claimData);
		claimPlanColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPolicyId()));		//test
		claimPeriodColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPolicyId()));		//test
		claimDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPolicyId()));		//test
		claimProgressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPolicyId()));		//test	
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
