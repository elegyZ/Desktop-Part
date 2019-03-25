package client.desktop;

import java.io.IOException;
import client.view.ClaimApplicationCheckController;
import client.view.ClaimApplicationController;
import client.view.ClaimNoticeController;
import client.view.InsuranceClaimController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Claim;

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
        showInsuranceClaimView();
        //showClaimApplicationView();
        //showClaimApplicationCheckView();
        //showClaimNoticeView();
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
	
	public void showInsuranceClaimView() 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/InsuranceClaimView.fxml"));
            AnchorPane claimAffairView = (AnchorPane) loader.load();
            claimAffairView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(claimAffairView);
            InsuranceClaimController controller = loader.getController();
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
            loader.setLocation(MainApp.class.getResource("../view/ClaimApplicationView.fxml"));
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
	
	public void showClaimApplicationCheckView(String policyId, Claim claim) 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/ClaimApplicationCheckView.fxml"));
            AnchorPane claimApplicationCheckView = (AnchorPane) loader.load();
            claimApplicationCheckView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(claimApplicationCheckView);
            ClaimApplicationCheckController controller = loader.getController();
            controller.setPolicy(policyId);
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
            loader.setLocation(MainApp.class.getResource("../view/ClaimNoticeView.fxml"));
            AnchorPane claimNoticeView = (AnchorPane) loader.load();
            claimNoticeView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(claimNoticeView);
            ClaimNoticeController controller = loader.getController();
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