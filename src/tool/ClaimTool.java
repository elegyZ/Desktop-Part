package tool;

import java.util.Iterator;

import javafx.collections.ObservableList;
import model.Claim;

public class ClaimTool 
{
	public static void refreshClaimList(ObservableList<Claim> claimData)
	{
		if(!claimData.isEmpty())
		{
			Iterator<Claim> iterator = claimData.iterator();
	        while(iterator.hasNext())
	        {
	            Claim integer = iterator.next();
	            if(!integer.getStatus().equals("pending"))
	                iterator.remove(); 
	        }
		}
	}
}
