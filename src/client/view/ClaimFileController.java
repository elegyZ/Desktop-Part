package client.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tool.Controller;

public class ClaimFileController  extends Controller 
{
	@FXML
	private Label fileList;
	
	public void setFileText(String fileText)
	{
		fileList.setText(fileText);
	}
}
