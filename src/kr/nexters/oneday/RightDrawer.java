package kr.nexters.oneday;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RightDrawer extends RelativeLayout{
	
	private View drawerView;
	
	public RightDrawer(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public RightDrawer(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		setBackgroundColor(Color.BLUE);
	}
	
	public RightDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	
	 private void initialize() {
	      LayoutInflater.from(getContext()).inflate(R.layout.right_drawer, this);
	      
	      initView();

	   }
	 
	 private void initView() {
		 drawerView = (View) findViewById(R.id.right_drawer);
	   }
	
}


