package Parameters;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Parameters {
	
	//General Parameters
	public static String htmlReport = "<style>.img:hover{color: #424242;-webkit-transition: all .3s ease-in;-moz-transition: all .3s ease-in;-ms-transition: all .3s ease-in;-o-transition: all .3s ease-in;" +
			"transition: all .3s ease-in;opacity: 1;transform: scale(2.99);-ms-transform: scale(2.99); /* IE 9 */-webkit-transform: scale(2.99); /* Safari and Chrome */}</style>";
	public static String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	public static String endTime = null;
	
	//Login Parameters
	public static String url = "https://www.carnival.com/";
	public static String browser = "FF";
	public static int timeout = 10;
	public static String user = "colpatria";
	public static String Password = "abc123";
	
	//Sail Flow Parameters
	public static String SailTo = "Alaska";
	public static String SailFrom = "Seattle, WA";
	public static String SailDate = "052019";
	public static String SailDuration = "6 - 9 Days";
	
	//Log Messages 
	public static String msgOpenbr = "Open Browser successfully.";
	public static String msgFillForm = "The form was filled correctly.";
	public static String msgLogin = "The user was logIn successfully.";
	public static String ErrwaitElement = "the element wasn't found in the page: ";
	public static String ErrEnbleElement = "the element wasn't enable in the list: ";
	public static String msgContRslt = "The Search Result Container was showed correctly with ";
	public static String msgitinerary = "The values of the itinerary are corrects respect the first Itinerary.";
	public static String Erritinerary = "The values of the itinerary are wrong respect the first Itinerary.";
	public static String msgitineraryRange = "The days are inside to the range selected.";
	public static String ErritineraryRange = "The days are outside to the range selected.";
	
}
