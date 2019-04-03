package desktop;

import java.io.IOException;
import client.view.ClaimApplicationCheckController;
import client.view.ClaimApplicationController;
import client.view.ClaimController;
import client.view.ClaimNoticeController;
import client.view.ClientProfileController;
import client.view.InsuranceController;
import client.view.InsuranceInformationController;
import employee.view.ClaimAffairController;
import employee.view.ClaimAffairNoticeController;
import employee.view.ClaimInformationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Claim;
import model.Policy;
import view.LogInController;
import view.ClientSignUpController;
import view.EmployeeSignUpController;

public class MainApp extends Application 
{
	private Stage primaryStage;
    private BorderPane rootLayout;
    
	@Override
	public void start(Stage primaryStage) 
	{
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hibernia-Sino");      
        initRootLayout();
        showLogInView();
        //showSignUpView();
	}
	
	
	public void initRootLayout() 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showLogInView() 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/LogInView.fxml"));
            AnchorPane logInView = (AnchorPane) loader.load();
            logInView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(logInView);
            LogInController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showClientSignUpView() 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/ClientSignUpView.fxml"));
            AnchorPane clientSignUpView = (AnchorPane) loader.load();
            clientSignUpView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(clientSignUpView);
            ClientSignUpController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showEmployeeSignUpView() 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/EmployeeSignUpView.fxml"));
            AnchorPane employeeSignUpView = (AnchorPane) loader.load();
            employeeSignUpView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(employeeSignUpView);
            EmployeeSignUpController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showClientProfileView()
	{
		try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../client/view/ClientProfileView.fxml"));
            AnchorPane clientProfileView = (AnchorPane) loader.load();
            clientProfileView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(clientProfileView);
            ClientProfileController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showInsuranceView() 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../client/view/InsuranceView.fxml"));
            AnchorPane InsuranceView = (AnchorPane) loader.load();
            InsuranceView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(InsuranceView);
            InsuranceController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showInsuranceInformationView(Policy policy) 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../client/view/InsuranceInformationView.fxml"));
            AnchorPane insuranceInformationView = (AnchorPane) loader.load();
            insuranceInformationView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(insuranceInformationView);
            InsuranceInformationController controller = loader.getController();
            controller.setPolicy(policy);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showClaimView() 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../client/view/ClaimView.fxml"));
            AnchorPane claimView = (AnchorPane) loader.load();
            claimView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(claimView);
            ClaimController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showClaimApplicationView(String policyId) 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../client/view/ClaimApplicationView.fxml"));
            AnchorPane claimApplicationView = (AnchorPane) loader.load();
            claimApplicationView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(claimApplicationView);
            ClaimApplicationController controller = loader.getController();
            controller.setPolicy(policyId);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showClaimApplicationView2(Claim claim) 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../client/view/ClaimApplicationView.fxml"));
            AnchorPane claimApplicationView = (AnchorPane) loader.load();
            claimApplicationView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(claimApplicationView);
            ClaimApplicationController controller = loader.getController();
            controller.InitClaim(claim);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showClaimApplicationCheckView(Claim claim) 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../client/view/ClaimApplicationCheckView.fxml"));
            AnchorPane claimApplicationCheckView = (AnchorPane) loader.load();
            claimApplicationCheckView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(claimApplicationCheckView);
            ClaimApplicationCheckController controller = loader.getController();
            controller.setClaim(claim);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showClaimNoticeView() 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../client/view/ClaimNoticeView.fxml"));
            AnchorPane claimNoticeView = (AnchorPane) loader.load();
            claimNoticeView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(claimNoticeView);
            ClaimNoticeController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	//----------------------------------------employee views---------------------------------------------
	public void showClaimAffairView() 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../employee/view/ClaimAffairView.fxml"));
            AnchorPane claimAffairView = (AnchorPane) loader.load();
            claimAffairView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(claimAffairView);
            ClaimAffairController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showClaimInformationView(Claim claim) 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../employee/view/ClaimInformationView.fxml"));
            AnchorPane claimInformationView = (AnchorPane) loader.load();
            claimInformationView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(claimInformationView);
            ClaimInformationController controller = loader.getController();
            controller.setClaim(claim);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showClaimAffairNoticeView() 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../employee/view/ClaimAffairNoticeView.fxml"));
            AnchorPane claimAffairNoticeViewView = (AnchorPane) loader.load();
            claimAffairNoticeViewView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(claimAffairNoticeViewView);
            ClaimAffairNoticeController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) 
	{
		launch(args);
	}

}
