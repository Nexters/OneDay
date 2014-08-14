package kr.nexters.oneday;

import kr.nexters.oneday.widget.FriendDeleteDialog;
import kr.nexters.oneday.widget.LeftDrawer;
import kr.nexters.oneday.widget.TimeTableView;
import kr.nexters.oneday.widget.TitleLayout;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.TranslateAnimation;

public class MainActivity extends Activity {

	private DrawerLayout drawerLayout;
	private LeftDrawer drawerView;
	private TimeTableView tableView;
	private TitleLayout titleLayout;
	
	private int futureTask = -1;
	private static final int FUTURE_TASK_START_ACT_MY_INFO = 1;
	private static final int FUTURE_TASK_START_ACT_FRIEND_INFO = 2;
	private static final int FUTURE_TASK_DELETE_FRIEND_INFO = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.custom_title);
		
		titleLayout = new TitleLayout(getWindow());

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		drawerView = (LeftDrawer) findViewById(R.id.left_drawer);
		drawerView.setOnClickListenerViews(clicker);
		tableView = (TimeTableView) findViewById(R.id.tableView);
		tableView.setSelectedMode(false);
		
		drawerLayout.setDrawerListener(myDrawerListener);

		titleLayout.setButtonL(new OnClickListener() {

			@Override
			public void onClick(View v) {
				toggleDrawer();
			}
		}, TitleLayout.BUTTON_TOGGLE_RES);
		
		titleLayout.setButtonR(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//공유 코드 작성				
				Intent sharingIntent = new Intent(Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");
				sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
				startActivity(Intent.createChooser(sharingIntent, "Share using"));
			}
		}, TitleLayout.BUTTON_EXPORT_RES);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		tableView.setPerson(Common.getPersonSelectedSet());
		drawerView.refresh();
	}

	public void toggleDrawer() {
		if (drawerLayout.isDrawerOpen(drawerView) == false){
			drawerLayout.openDrawer(drawerView);
		} else {
			drawerLayout.closeDrawer(drawerView);
		}
	}
	
	@Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && drawerLayout.isDrawerOpen(drawerView)) {
			drawerLayout.closeDrawer(drawerView);
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	private DrawerListener myDrawerListener = new DrawerListener() {
		private float lastTranslate = 0.0f;

		@Override 
		public void onDrawerClosed(View drawerView) { 
			switch(futureTask) {
			case FUTURE_TASK_START_ACT_MY_INFO:
				Intent intent2 = new Intent(MainActivity.this, MyInfoAddActivity.class);
				startActivity(intent2);
				break;
			case FUTURE_TASK_START_ACT_FRIEND_INFO:
				Intent intent = new Intent(MainActivity.this, FriendInfoAddActivity.class);
				startActivity(intent);
				break;
			case FUTURE_TASK_DELETE_FRIEND_INFO:
				FriendDeleteDialog dialog2 = new FriendDeleteDialog(MainActivity.this);
				dialog2.show();
				break;
			}
			futureTask = -1;
		}
		@Override public void onDrawerOpened(View drawerView) {  }
		@Override public void onDrawerStateChanged(int newState) {  }

		@SuppressLint("NewApi")
		@Override
		public void onDrawerSlide(View v, float slideOffset) {
			float moveFactor = (drawerView.getWidth() * slideOffset)/2;

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				tableView.setTranslationX(moveFactor);
			} else {
				TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
				anim.setDuration(0);
				anim.setFillAfter(true);
				tableView.startAnimation(anim);

				lastTranslate = moveFactor;
			}
		}
	};
	
	private OnClickListener clicker = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(drawerLayout.isDrawerOpen(drawerView)) {
				drawerLayout.closeDrawers();
			}
			switch (v.getId()) {
			case R.id.FriendAddButton:
				futureTask = FUTURE_TASK_START_ACT_FRIEND_INFO;
				break;
				
			case R.id.FriendDeleteButton:
				futureTask = FUTURE_TASK_DELETE_FRIEND_INFO;
				break;
				
			case R.id.leftdrawer_setting_btn:
				futureTask = FUTURE_TASK_START_ACT_MY_INFO;
				break;
			}
		}
	};
}


