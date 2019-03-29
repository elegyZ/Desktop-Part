package view;

import client.desktop.ClientMainApp;
import employee.desktop.EmployeeMainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class LogInController 
{
	@FXML
	private TextField clientUserName;
	@FXML
	private TextField clientPassword;
	@FXML
	private TextField employeeUserName;
	@FXML
	private TextField employeePassword;
	@FXML
	private Button bt_clientLogIn;
	@FXML
	private Button bt_clientSignIn;
	@FXML
	private Button bt_employeeLogIn;
	
	private Stage primaryStage;
	private ClientMainApp clientMainApp = new ClientMainApp();
	private EmployeeMainApp employeeMainApp = new EmployeeMainApp();
		
	public void checkAlert(String warning)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(null);
		alert.setContentText(warning);
		alert.getButtonTypes().clear();
		alert.getButtonTypes().add(new ButtonType("OK", ButtonData.YES));
		alert.showAndWait();
	}
	
	@FXML
	public void clientLogIn()
	{
		String username = clientUserName.getText();
		String password = clientPassword.getText();
		if(username.equals(""))
			checkAlert("Please Enter Your User Name.");
		else if(password.equals(""))
			checkAlert("Please Enter Your Password.");
		else
			clientMainApp.start(primaryStage);
	}
	
	public void setStage(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
	}
}
