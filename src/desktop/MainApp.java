package desktop;

import java.io.IOException;
import client.view.ClaimApplicationCheckController;
import client.view.ClaimApplicationController;
import client.view.ClaimController;
import client.view.ClaimFeedbackController;
import client.view.ClientNoticeController;
import client.view.ClientProfileController;
import client.view.ClientProfileModifyController;
import client.view.HomeController;
import client.view.InsuranceController;
import client.view.InsuranceInformationController;
import client.view.InsurancePurchaseController;
import employee.view.EmployeeNoticeController;
import employee.view.EmployeeProfileController;
import employee.view.EmployeeProfileModifyController;
import employee.view.ClaimInformationController;
import employee.view.ClaimAffairController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Claim;
import model.Policy;
import model.Profile;
import tool.UserTool;
import view.ProfileCreateController;
import view.LogInController;
import view.SignUpController;

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
        //showProfileCreateView();
	}
	
	
	public void initRootLayout() 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();    
            rootLayout.setStyle("-fx-background-color: white;");
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
            VBox logInView = (VBox) loader.load();
            logInView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(logInView);
            LogInController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showSignUpView(String type) 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/SignUpView.fxml"));
            VBox signUpView = (VBox) loader.load();
            signUpView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(signUpView);
            SignUpController controller = loader.getController();
            controller.setMainApp(this);
            controller.setType(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showProfileCreateView()
	{
		try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/ProfileCreateView.fxml"));
            HBox profileCreateView = (HBox) loader.load();
            profileCreateView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(profileCreateView);
            ProfileCreateController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showHomeView()
	{
		try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../client/view/HomeView.fxml"));
            HBox homeView = (HBox) loader.load();
            homeView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(homeView);
            HomeController controller = loader.getController();
            controller.setMainApp(this);
            controller.setPane();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showInsurancePurchaseView(int i)
	{
		try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../client/view/InsurancePurchaseView.fxml"));
            HBox insurancePurchaseView = (HBox) loader.load();
            insurancePurchaseView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(insurancePurchaseView);
            InsurancePurchaseController controller = loader.getController();
            controller.setPlan(i);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showClientNoticeView(String type)
	{
		try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../client/view/ClientNoticeView.fxml"));
            HBox clientNoticeView = (HBox) loader.load();
            clientNoticeView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(clientNoticeView);
            ClientNoticeController controller = loader.getController();
            controller.setMainApp(this);
            controller.setType(type);
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
            controller.setProfile(UserTool.user.getProfileId());
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showClientProfileModifyView(Profile profile) 
	{
		try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../client/view/ClientProfileModifyView.fxml"));
            HBox clientProfileModifyView = (HBox) loader.load();
            clientProfileModifyView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(clientProfileModifyView);
            ClientProfileModifyController controller = loader.getController();
            controller.initProfile(profile);
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
	
	public void showClaimFeedbackView(Claim claim)
	{
		try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../client/view/ClaimFeedbackView.fxml"));
            HBox claimFeedbackView = (HBox) loader.load();
            claimFeedbackView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(claimFeedbackView);
			ClaimFeedbackController controller = loader.getController();
			controller.setMainApp(this);
			controller.setClaim(claim);
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
            HBox claimApplicationView = (HBox) loader.load();
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
            HBox claimApplicationView = (HBox) loader.load();
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
            HBox claimApplicationCheckView = (HBox) loader.load();
            claimApplicationCheckView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(claimApplicationCheckView);
            ClaimApplicationCheckController controller = loader.getController();
            controller.setClaim(claim);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	//----------------------------------------employee views---------------------------------------------
	public void showEmployeeProfileView() 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../employee/view/EmployeeProfileView.fxml"));
            HBox employeeProfileView = (HBox) loader.load();
            employeeProfileView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(employeeProfileView);
            EmployeeProfileController controller = loader.getController();
            controller.setMainApp(this);
            controller.setProfile(UserTool.user.getProfileId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showEmployeeProfileModifyView(Profile profile) 
	{
		try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../employee/view/EmployeeProfileModifyView.fxml"));
            HBox employeeProfileModifyView = (HBox) loader.load();
            employeeProfileModifyView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(employeeProfileModifyView);
            EmployeeProfileModifyController controller = loader.getController();
            controller.initProfile(profile);
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showClaimAffairView(String type) 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../employee/view/ClaimAffairView.fxml"));
            HBox claimAffairView = (HBox) loader.load();
            claimAffairView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(claimAffairView);
            ClaimAffairController controller = loader.getController();
            controller.setMainApp(this);
            controller.setType(type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showClaimInformationView(Claim claim, String type) 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../employee/view/ClaimInformationView.fxml"));
            HBox claimInformationView = (HBox) loader.load();
            claimInformationView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(claimInformationView);
            ClaimInformationController controller = loader.getController();
            controller.setMainApp(this);
            controller.setType(type);
            controller.setClaim(claim);
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
            EmployeeNoticeController controller = loader.getController();
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
