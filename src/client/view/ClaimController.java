package client.view;

import desktop.MainApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import model.Claim;
import tool.ClaimTool;
import tool.Controller;
import tool.UserTool;

public class ClaimController extends Controller 
{
	@FXML
	private TableView<Claim> tb_claims;
	@FXML
    private TableColumn<Claim, String> claimLocationColumn;
	@FXML
    private TableColumn<Claim, String> claimDateColumn;
	@FXML
    private TableColumn<Claim, String> claimPolicyIdColumn;
	@FXML
    private TableColumn<Claim, String> claimProgressColumn;
	@FXML
	private Button btn_home;
	@FXML
	private Button btn_logout;
	@FXML
	private Button btn_myClaim;
	@FXML
	private Button btn_myInsurance;
	@FXML
	private Button btn_aboutUs;
	@FXML
	private Button bt_language;
	@FXML
	private Label lb_clientService;
	@FXML
	private Label lb_claimHistory;
	
	private ObservableList<Claim> claimData;
	private MainApp mainApp;
	
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
		btn_myClaim.setText(UserTool.i18n.get("myclaim"));
		btn_aboutUs.setText(UserTool.i18n.get("aboutus"));
		btn_logout.setText(UserTool.i18n.get("logout"));
		
		lb_claimHistory.setText(UserTool.i18n.get("claimHistory"));
		claimLocationColumn.setText(UserTool.i18n.get("accLocation"));
		claimDateColumn.setText(UserTool.i18n.get("claimDate"));
		claimPolicyIdColumn.setText(UserTool.i18n.get("policyIdCol"));
		claimProgressColumn.setText(UserTool.i18n.get("claimPregress"));
	}
	
	private void initData() 
	{
		//------------------------------------------------------------Data Update---------------------------------------------
		claimData = ClaimTool.getClaimAffairList();
		//------------------------------------------------------------GUI Update---------------------------------------------
		tb_claims.setItems(claimData);
		claimPolicyIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPolicyId()));
		claimLocationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAccLocation()));
		claimDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCreateDate().toString()));
		claimProgressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
		
		tb_claims.setRowFactory(tableview -> 
		{
			TableRow<Claim> row = new TableRow<Claim>();
			row.setOnMouseClicked(event -> 
			{
				Claim claim = row.getItem();
				mainApp.showClaimFeedbackView(claim);
			});
			return row ;
		});
	}
	
	@FXML
	public void toHome()
	{
		mainApp.showHomeView();
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
        initData();
    }
}
