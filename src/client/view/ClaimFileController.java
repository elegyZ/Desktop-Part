package client.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClaimFileController 
{
	@FXML
	private Label fileList;
	
	public void setFileText(String fileText)
	{
		fileList.setText(fileText);
	}
}
