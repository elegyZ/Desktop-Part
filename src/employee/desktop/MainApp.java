package employee.desktop;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import employee.model.Claim;
import employee.view.ClaimAffairController;
import employee.view.ClaimInformationController;
import employee.view.ClaimNoticeController;

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
        showClaimAffairView();
        //showClaimInformationView();
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
	
	public void showClaimAffairView() 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/ClaimAffairView.fxml"));
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
            loader.setLocation(MainApp.class.getResource("../view/ClaimInformationView.fxml"));
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
	
	public void showClaimNoticeView() 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/ClaimNoticeView.fxml"));
            AnchorPane claimNoticeViewView = (AnchorPane) loader.load();
            claimNoticeViewView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(claimNoticeViewView);
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
