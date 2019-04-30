package tool;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NTool 
{
	private final String BASENAME = "i18n.resource.myproperties";//设置语言环境
	private final Locale CN = Locale.CHINA;//中文
	private final Locale UK = Locale.UK;//英文
	private ResourceBundle myResources = ResourceBundle.getBundle(BASENAME,UK);
	
	public void changeLanguage()
	{
		if(isEnglish())
			myResources = ResourceBundle.getBundle(BASENAME,CN);
		else
			myResources = ResourceBundle.getBundle(BASENAME,UK);
	}
	
	public String get(String key)
	{
		return myResources.getString(key);
	}
	
	public Boolean isEnglish()
	{
		return myResources.equals(ResourceBundle.getBundle(BASENAME,UK));
	}
}
