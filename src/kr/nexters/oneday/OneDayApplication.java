package kr.nexters.oneday; 

import kr.nexters.oneday.vo.Person;
import android.app.Application;

public class OneDayApplication extends Application {
	
    @Override
    public void onCreate() {
        super.onCreate();
        Common.initialize(this);
        Common.addPerson(new Person("나", "", null, true));
    }
}