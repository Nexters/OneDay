package kr.nexters.oneday;

import kr.nexters.oneday.widget.TimeTableView;
import kr.nexters.oneday.widget.TitleLayout;
import kr.nexters.oneday.widget.TimeTableView.DAY;
import kr.nexters.oneday.widget.TimeTableView.TIME;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.TranslateAnimation;

public class MainActivity extends Activity {

	private DrawerLayout drawerLayout;
	private View drawerView;

	private TimeTableView tableView;
	private TitleLayout titleLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.custom_title);
		
		titleLayout = new TitleLayout(getWindow());

		titleLayout = new TitleLayout(getWindow());

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerView = (View) findViewById(R.id.left_drawer);
		tableView = (TimeTableView) findViewById(R.id.tableView);

		drawerLayout.setDrawerListener(myDrawerListener);

		titleLayout.setButtonL(new OnClickListener() {

			@Override
			public void onClick(View v) {
				toggleDrawer();
			}
		});
		
	}

	public void toggleDrawer() {
		if (drawerLayout.isDrawerOpen(drawerView) == false){
			drawerLayout.openDrawer(drawerView);
		} else {
			drawerLayout.closeDrawer(drawerView);
		}
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


