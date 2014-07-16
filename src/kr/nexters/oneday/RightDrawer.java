package kr.nexters.oneday;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class RightDrawer extends RelativeLayout {

//	private View drawerView;

	public RightDrawer(Context context) {
		super(context);
		initialize();
	}

	public RightDrawer(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}

	public RightDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initialize();
	}

	private void initialize() {
		LayoutInflater.from(getContext()).inflate(R.layout.right_drawer, this);

		initView();

	}

	private void initView() {
//		drawerView = (View) findViewById(R.id.right_drawer);
	}

}
