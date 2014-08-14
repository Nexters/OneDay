package kr.nexters.oneday;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import kr.nexters.oneday.vo.Person;
import kr.nexters.oneday.widget.TimeTableView;
import kr.nexters.oneday.widget.TitleLayout;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class MyInfoAddActivity extends Activity {

	private TitleLayout titleLayout;
	private TimeTableView tableView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_friend_add);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.custom_title);
		
		titleLayout = new TitleLayout(getWindow());
		titleLayout.setTitle("내 시간표 관리");
		
		tableView = (TimeTableView) findViewById(R.id.tableView);
		titleLayout.setButtonR(new OnClickListener() {

			@Override
			public void onClick(View v) {
				saveMyInfo();
				finish();
			}
		}, TitleLayout.BUTTON_CHECK_RES);
		titleLayout.setButtonL(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		}, TitleLayout.BUTTON_EXIT_RES);
		
		loadMyInfo();
	}
	
	private void loadMyInfo() {
		Person me = new Person("나", null, null);
		
		if(Common.getPersonSet().contains(me)) {
			Iterator<Person> it = Common.getPersonSet().iterator();
			while(it.hasNext()) {
				Person p = it.next();
				if(p.equals(me)) {
					tableView.clearSector();
					tableView.addPerson(p);
					return;
				}
			}
		}
	}

	private void saveMyInfo() {
		Person me = new Person();
		me.setName("나");
		me.setTimeList(tableView.getAllSelectedTimeInfo());
		
		Common.addPerson(me);
		Common.addSelectedPerson(me);
	}
}
