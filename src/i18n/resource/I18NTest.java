package i18n.resource;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NTest 
{

	public static void main(String[] args) 
	{
		//资源包基名(包名+myproperties)
        String basename = "i18n.resource.myproperties";
        //设置语言环境
        Locale cn = Locale.CHINA;//中文
        Locale uk = Locale.UK;//英文
        //根据基名和语言环境加载对应的语言资源文件
        ResourceBundle myResourcesCN = ResourceBundle.getBundle(basename,cn);//加载myproperties_zh.properties
        ResourceBundle myResourcesUK = ResourceBundle.getBundle(basename,uk);//加载myproperties_en.properties
         
        //加载资源文件后， 程序就可以调用ResourceBundle实例对象的 getString方法获取指定的资源信息名称所对应的值。
        //String value =  myResources.getString(“key");
        String usernameCN = myResourcesCN.getString("username");
        String passwordCN = myResourcesCN.getString("password");
         
        String usernameUS = myResourcesUK.getString("username");
        String passwordUS = myResourcesUK.getString("password");
         
        System.out.println(usernameCN+"--"+passwordCN);
        System.out.println(usernameUS+"--"+passwordUS);
	}

}
