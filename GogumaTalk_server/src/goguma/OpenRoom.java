package goguma;

import java.util.ArrayList;
import java.util.HashMap;

public class OpenRoom extends HashMap{
	private static OpenRoom instance;
	private HashMap <String, ArrayList> openRoom;
	public static OpenRoom getInstance(){
		if(instance == null)
			instance = new OpenRoom();
		return instance;
	}
	
}
