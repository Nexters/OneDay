package kr.nexters.oneday;

import android.content.Context;

public class Common {

	private static Context sContext;  
	
	public static void initialize(Context context) {
		sContext = context;
	}

	public static Context getMainContext() {
		return sContext;
	}
}
