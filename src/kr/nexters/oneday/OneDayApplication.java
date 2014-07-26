package kr.nexters.oneday; 


import kr.nexters.oneday.database.DBHandler;
import android.app.Application;

public class OneDayApplication extends Application {
	
    @Override
    public void onCreate() {
        super.onCreate();
        DBHandler dbHandler = new DBHandler(this);
        dbHandler.loadPersonInfo();
    }
}