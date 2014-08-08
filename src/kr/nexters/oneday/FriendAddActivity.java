package kr.nexters.oneday;

import kr.nexters.oneday.R;
import kr.nexters.oneday.R.id;
import kr.nexters.oneday.R.layout;
import kr.nexters.oneday.R.menu;
import kr.nexters.oneday.widget.TimeTableView;
import kr.nexters.oneday.widget.TitleLayout;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

public class FriendAddActivity extends Activity {

	private TitleLayout titleLayout;
	private TimeTableView tableView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_friend_add);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.custom_title);
		
		titleLayout = new TitleLayout(getWindow());
		
		tableView = (TimeTableView) findViewById(R.id.tableView);
		
		titleLayout.setButtonR2(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
			}
		});
		
	}

}
