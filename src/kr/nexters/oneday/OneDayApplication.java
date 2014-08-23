package kr.nexters.oneday; 


//import kr.nexters.oneday.database.DBHandler;
import java.util.ArrayList;

import kr.nexters.oneday.database.DBHelper;
import kr.nexters.oneday.database.PersonDBAdapter;
import kr.nexters.oneday.vo.Person;
import android.app.Application;

public class OneDayApplication extends Application {
	
    @Override
    public void onCreate() {
        super.onCreate();
        Common.initialize(this);
        Common.addPerson(new Person("나", "", null, true));
//        LoadCommonFromDatabase();
       // this.deleteDatabase(DBHelper.DATABASE_NAME);
        //DBHandler dbHandler = new DBHandler(this);
       
       //.loadPersonInfo();
    }
    private void LoadCommonFromDatabase(){

    	PersonDBAdapter DBAdapter = new PersonDBAdapter();
		
		for(Person person : DBAdapter.getPeople()){
			Common.addPerson(person);	
		}
		
		
	}
}