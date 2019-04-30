package client.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tool.Controller;
import tool.UserTool;

public class ClaimFileController  extends Controller 
{
	@FXML
	private Label title;
	@FXML
	private Label fileList;
	
	public void setFileText(String fileText)
	{
		title.setText(UserTool.i18n.get("claimSupportFileList"));
		fileList.setText(fileText);
	}
}
