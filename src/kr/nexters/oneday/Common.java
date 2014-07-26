package kr.nexters.oneday;

import java.util.List;

import kr.nexters.oneday.database.Person;
import android.content.Context;

public class Common {
	public static List<Person> personList;
	
	private static Context sContext;  

	public static void initialize(Context context) {
		sContext = context;
	}

	public static Context getMainContext() {
		return sContext;
	}
}
