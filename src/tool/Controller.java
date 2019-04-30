package tool;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class Controller 
{
	public void successAlert(String success)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(UserTool.i18n.get("dialogsuccess"));
		alert.setHeaderText(null);
		alert.setContentText(success);
		if(UserTool.i18n.isEnglish())
		{
			alert.getButtonTypes().clear();
			alert.getButtonTypes().add(new ButtonType("OK", ButtonData.YES));
		}
		alert.showAndWait();
	}
	
	public void checkAlert(String warning)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(UserTool.i18n.get("dialogwarning"));
		alert.setHeaderText(null);
		alert.setContentText(warning);
		if(UserTool.i18n.isEnglish())
		{
			alert.getButtonTypes().clear();
			alert.getButtonTypes().add(new ButtonType("OK", ButtonData.YES));
		}
		alert.showAndWait();
	}
	
	public void errorAlert(String error)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(error);
		if(UserTool.i18n.isEnglish())
		{
			alert.getButtonTypes().clear();
			alert.getButtonTypes().add(new ButtonType("OK", ButtonData.YES));
		}
		alert.showAndWait();
	}
}
