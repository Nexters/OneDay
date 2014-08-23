package kr.nexters.oneday;

import java.util.Iterator;

import kr.nexters.oneday.vo.Person;
import kr.nexters.oneday.widget.TimeTableView;
import kr.nexters.oneday.widget.TitleLayout;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

public class FriendStorageActivity extends Activity {
	
	private TitleLayout titleLayout;
	private TimeTableView tableView;
	public static Context context;
	String text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_friend_add);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.custom_title);
		
		context = this;
		
		titleLayout = new TitleLayout(getWindow());
		
		Intent intent = getIntent();
		text = intent.getStringExtra("TextIn1");
		titleLayout.setTitle(text);
		
		tableView = (TimeTableView) findViewById(R.id.tableView);
		tableView.setSelectedMode(true);
		titleLayout.setButtonR(new OnClickListener() {

			@Override
			public void onClick(View v) {
				saveInfo();
				finish();
			}
		}, TitleLayout.BUTTON_CHECK_RES);
		titleLayout.setButtonL(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		}, TitleLayout.BUTTON_EXIT_RES);
		
		loadInfo();
		tableView.setModifiedMode();
	}
	
	private void loadInfo() {
		Person me = new Person(text, null, null, true);
		
		if(Common.getPersonSet().contains(me)) {
			Iterator<Person> it = Common.getPersonSet().iterator();
			while(it.hasNext()) {
				Person p = it.next();
				if(p.equals(me)) {
					tableView.clearAllSector();
					tableView.addPerson(p);
					return;
				}
			}
		}
	}

	private void saveInfo() {
		Person person = new Person();
		person.setName(text);
		person.setTimeList(tableView.getAllSelectedTimeInfo());
		
		Common.addPerson(person);
		Common.addSelectedPerson(person);
	}
	


}
