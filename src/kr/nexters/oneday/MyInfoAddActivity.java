package kr.nexters.oneday;

import kr.nexters.oneday.R;
import kr.nexters.oneday.R.id;
import kr.nexters.oneday.R.layout;
import kr.nexters.oneday.vo.Person;
import kr.nexters.oneday.widget.TimeTableView;
import kr.nexters.oneday.widget.TitleLayout;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

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
		
		titleLayout.setButtonR2(new OnClickListener() {

			@Override
			public void onClick(View v) {
				saveMyInfo();
				finish();
			}
		});
		
	}

	private void saveMyInfo() {
		Person me = new Person();
		me.setName("나");
		me.setTimeList(tableView.getAllSelectedTimeInfo());
		
		Common.addPerson(me);
		Common.addSelectedPerson(me);
	}
}