package kr.nexters.oneday;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private DrawerLayout drawerLayout;
	private View drawerView;
	  Button btn;  
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.custom_title);
		
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerView = (View) findViewById(R.id.left_drawer);

		btn = (Button) findViewById(R.id.btn);
    
		drawerLayout.setDrawerListener(myDrawerListener);
		drawerView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return true;
			}
		});
    
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

			public void onDrawerClosed(View drawerView) {

			}

			public void onDrawerOpened(View drawerView) {

			}

			public void onDrawerSlide(View drawerView, float slideOffset) {

			}

			public void onDrawerStateChanged(int newState) {

			}
		};
}
