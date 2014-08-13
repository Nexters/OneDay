package kr.nexters.oneday;

import java.util.ArrayList;
import kr.nexters.oneday.adapter.PersonListAdapter;
import kr.nexters.oneday.vo.Person;
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
import android.widget.ListView;

public class MainActivity extends Activity {

	private DrawerLayout drawerLayout;
	private LeftDrawer drawerView;
	private TimeTableView tableView;
	private TitleLayout titleLayout;
	
	private ArrayList<Person> pItem = null;
	private PersonListAdapter pAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.custom_title);
		
		titleLayout = new TitleLayout(getWindow());

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		drawerView = (LeftDrawer) findViewById(R.id.left_drawer);
		tableView = (TimeTableView) findViewById(R.id.tableView);
		tableView.setSelectedMode(false);
		
		drawerLayout.setDrawerListener(myDrawerListener);
		drawerView.setDrawerLayout(drawerLayout);

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

	DrawerListener myDrawerListener = new DrawerListener() {
		private float lastTranslate = 0.0f;

		@Override public void onDrawerClosed(View drawerView) {  }
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
}


