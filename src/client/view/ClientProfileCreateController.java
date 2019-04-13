package client.view;

import desktop.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tool.Controller;

public class ClientProfileCreateController extends Controller 
{
	@FXML
	private TextField tf_firstname;
	@FXML
	private TextField tf_lastname;
	@FXML
	private TextField tf_gender;
	@FXML
	private TextField tf_age;
	@FXML
	private TextField tf_socialId;
	@FXML
	private TextField tf_country;
	@FXML
	private TextField tf_province;
	@FXML
	private TextField tf_city;
	@FXML
	private TextField tf_phone;
	@FXML
	private TextField tf_email;
	@FXML
	private Button Create;
	
	private MainApp mainApp;
	
	public void createProfile()
	{
		String firstname = tf_firstname.getText();
		String lastname = tf_lastname.getText();
		String gender = tf_gender.getText();
		String age = tf_age.getText();
		String socialId = tf_socialId.getText();
		String country = tf_country.getText();
		String province = tf_province.getText();
		String city = tf_city.getText();
		String phonoe = tf_phone.getText();
		String email = tf_email.getText();
	}
	
	public void setMainApp(MainApp mainApp) 
    {
        this.mainApp = mainApp;
    }
}
