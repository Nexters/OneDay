package kr.nexters.oneday.widget;

import kr.nexters.oneday.FriendInfoAddActivity;
import kr.nexters.oneday.MyInfoAddActivity;
import kr.nexters.oneday.R;
import kr.nexters.oneday.adapter.PersonListAdapter;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;


public class LeftDrawer extends RelativeLayout {
	
	private ListView pDrawerList;
	private PersonListAdapter pAdapter = null;
	
	public LeftDrawer(Context context) {
		super(context);
		initialize();
	}
	
	public LeftDrawer(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}
	
	public LeftDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initialize();
	}
	
	private void initialize() {
		LayoutInflater.from(getContext()).inflate(R.layout.left_drawer, this);
		initView();
	}
	
	private void initView() {
		pDrawerList = (ListView) findViewById(R.id.listView1);
		
		
		pAdapter = new PersonListAdapter(this.getContext());
		pDrawerList.setAdapter(pAdapter);
	}
	
	public void setOnClickListenerViews(OnClickListener l) {
		findViewById(R.id.leftdrawer_setting_btn).setOnClickListener(l);
		findViewById(R.id.FriendAddButton).setOnClickListener(l);
		findViewById(R.id.FriendDeleteButton).setOnClickListener(l);
	}

	public void refresh() {
		pAdapter.notifyDataSetChanged();
	}
}
