package tool;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class Controller 
{
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
	
	public void errorAlert(String error)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(error);
		alert.getButtonTypes().clear(); 
		alert.getButtonTypes().add(new ButtonType("OK", ButtonData.YES));
		alert.showAndWait();
	}
}
