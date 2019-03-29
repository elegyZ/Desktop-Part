package desktop;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.LogInController;
import view.SignInController;

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
        //showSignInView();
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
            controller.setStage(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showSignInView() 
	{
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/SignInView.fxml"));
            AnchorPane signInView = (AnchorPane) loader.load();
            signInView.setStyle("-fx-background-color: white;");
            rootLayout.setCenter(signInView);
            SignInController controller = loader.getController();
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
