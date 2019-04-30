package tool;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NTool 
{
	private final String BASENAME = "i18n.resource.myproperties";//�������Ի���
	private final Locale CN = Locale.CHINA;//����
	private final Locale UK = Locale.UK;//Ӣ��
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
