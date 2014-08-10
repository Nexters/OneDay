package kr.nexters.oneday;

import kr.nexters.oneday.vo.Person;
import kr.nexters.oneday.widget.FriendAddDialog;
import kr.nexters.oneday.widget.TimeTableView;
import kr.nexters.oneday.widget.TitleLayout;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

public class FriendInfoAddActivity extends Activity {

	private TitleLayout titleLayout;
	private TimeTableView tableView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_friend_add);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.custom_title);
		
		titleLayout = new TitleLayout(getWindow());
		titleLayout.setTitle("친구 추가");
		
		tableView = (TimeTableView) findViewById(R.id.tableView);
		
		titleLayout.setButtonR2(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FriendAddDialog dialog = new FriendAddDialog(FriendInfoAddActivity.this);
				dialog.show();
			}
		});
		
	}
	
	public void saveFriendInfo() {
		Person friend = new Person();
		friend.setName("나");
		friend.setTimeList(tableView.getAllSelectedTimeInfo());
		
		Common.addPerson(friend);
		Common.addSelectedPerson(friend);
	}
	
}
