package kr.nexters.oneday; 

import java.util.ArrayList;
import java.util.List;

import kr.nexters.oneday.vo.Person;
import kr.nexters.oneday.vo.TimeInfo;
import android.app.Application;

public class OneDayApplication extends Application {
	
    @Override
    public void onCreate() {
        super.onCreate();
        Common.initialize(this);
        
        if(Common.getPerson("나") == null) {
        	List<TimeInfo> dummy = new ArrayList<TimeInfo>();
        	Common.addPerson(new Person("나", "", dummy, false));
        }
    }
}