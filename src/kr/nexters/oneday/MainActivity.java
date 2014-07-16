package kr.nexters.oneday;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

public class MainActivity extends Activity {

	private DrawerLayout drawerLayout;
	private View drawerView;
	Button btn;  

	private FrameLayout frame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.custom_title);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerView = (View) findViewById(R.id.right_drawer);
		frame = (FrameLayout) findViewById(R.id.content_frame);

		btn = (Button) findViewById(R.id.btn);

		drawerLayout.setDrawerListener(myDrawerListener);
		

	}

	public void mOnClick(View v) {
		switch(v.getId()) {
		case R.id.btn:			
			if(btn.isSelected()== false){
				drawerLayout.openDrawer(drawerView);
				btn.setSelected(true);
			} else {
				drawerLayout.closeDrawer(drawerView);
				btn.setSelected(false);
			}


		}
	}

	DrawerListener myDrawerListener = new DrawerListener() {
		private float lastTranslate = 0.0f;
		
		@Override public void onDrawerClosed(View drawerView) {  }
		@Override public void onDrawerOpened(View drawerView) {  }
		@Override public void onDrawerStateChanged(int newState) {  }
		
		@Override
		public void onDrawerSlide(View v, float slideOffset) {
			float moveFactor = -(drawerView.getWidth() * slideOffset)/2;

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				frame.setTranslationX(moveFactor);
			} else {
				TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
				anim.setDuration(0);
				anim.setFillAfter(true);
				frame.startAnimation(anim);

				lastTranslate = moveFactor;
			}
		}
	};
}


