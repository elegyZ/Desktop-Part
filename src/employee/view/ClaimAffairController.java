package employee.view;

import desktop.MainApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.util.Pair;
import model.Claim;
import tool.ClaimTool;
import tool.Controller;
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
    private Button btn_claimAffiar;
    @FXML
    private Button btn_pending;
    @FXML
    private Button btn_processing;
    @FXML
    private Button btn_closed;
    @FXML
	private Button btn_logout;
    @FXML
	private Button bt_language;
    @FXML
   	private Label lb_workmenu;
    @FXML
   	private Label lb_claimAffairs;
    
    private ObservableList<Claim> claimData;
    private MainApp mainApp;
    private String type;
    
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
		lb_workmenu.setText(UserTool.i18n.get("workmenu"));
		btn_claimAffiar.setText(UserTool.i18n.get("claimAffair"));
		btn_pending.setText(UserTool.i18n.get("pending"));
		btn_processing.setText(UserTool.i18n.get("processing"));
		btn_closed.setText(UserTool.i18n.get("closed"));
		btn_logout.setText(UserTool.i18n.get("logout"));
		
		lb_claimAffairs.setText(UserTool.i18n.get("claimAffairInfo"));
		policyIdColumn.setText(UserTool.i18n.get("policyIdCol"));
		insuranceTypeColumn.setText(UserTool.i18n.get("insuranceTypeCol"));
		dateOfSubmissionColumn.setText(UserTool.i18n.get("dateOfSubmissionCol"));
		amountOfDamageColumn.setText(UserTool.i18n.get("amountOfDamageCol"));
		acceptanceColumn.setText(UserTool.i18n.get("acceptanceCol"));
	}
    
    public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
        setLanguageBtn();
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
    	{
    		line_closed.setStrokeWidth(2);
    		tb_claims.setRowFactory(tableview -> 
    		{
    			TableRow<Claim> row = new TableRow<Claim>();
    			row.setOnMouseClicked(event -> 
    			{
    				Claim claim = row.getItem();
    				mainApp.showClaimInformationView(claim, type);
    			});
    			return row ;
    		});
    	}
    	init();
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

    private void init() 
    {    	
    	//------------------------------------------------------------Data Update---------------------------------------------
    	claimData = ClaimTool.initFilterData(type);
		//------------------------------------------------------------GUI Update---------------------------------------------
		tb_claims.setItems(claimData);
		policyIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPolicyId()));
		insuranceTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTypeProperty(cellData.getValue().getType())));
		dateOfSubmissionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSubmissionDateProperty("Ireland")));
		amountOfDamageColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getClaimAmount())));
		acceptanceColumn.setCellFactory((col) -> {TableCell<Claim, String> cell = new TableCell<Claim, String>() 
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
                        	Button bt_assign = new Button(UserTool.i18n.get("btn_Assign"));
                            this.setGraphic(bt_assign);
                            bt_assign.setOnMouseClicked((assign) -> 
                            {
                            	claim.setStatus("processing");
                            	Pair<Integer, String> reply = ClaimTool.assign(claim);
                            	if(reply.getKey() == 200)
                            	{
                            		successAlert(UserTool.i18n.get("TheClaimHasBeenAssignedToYouSuccessfally"));
                                	Button bt_process = new Button(UserTool.i18n.get("btn_Process"));
                                    this.setGraphic(bt_process);
                                    bt_process.setOnMouseClicked((process) -> 
                                    {
                                    	mainApp.showClaimInformationView(claim, type);
                                    });
                            	}
                            	else
                            		errorAlert(UserTool.i18n.get("CanNotAssignThisClaim") + reply.getValue());
                            });
                        }
                        else if(claim.getStatus().equals("processing") && claim.getEmployeeId().equals(UserTool.user.getUserId()))
                        {
                        	Button bt_process = new Button(UserTool.i18n.get("btn_Process"));
                            this.setGraphic(bt_process);
                            bt_process.setOnMouseClicked((process) -> 
                            {
                            	mainApp.showClaimInformationView(claim, type);
                            });
                        }
					}
				}

			};
			return cell;
		});
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
    
    @FXML
	public void logout()
	{
		mainApp.showLogInView();
	}
}
