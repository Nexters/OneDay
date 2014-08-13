package kr.nexters.oneday.widget;

import java.util.ArrayList;
import java.util.List;

import kr.nexters.oneday.Common;
import kr.nexters.oneday.FriendInfoAddActivity;
import kr.nexters.oneday.MyInfoAddActivity;
import kr.nexters.oneday.R;
import kr.nexters.oneday.adapter.PersonListAdapter;
import kr.nexters.oneday.vo.Person;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;


public class LeftDrawer extends RelativeLayout {
	
	private ListView pDrawerList;
	private PersonListAdapter pAdapter = null;
	private DrawerLayout drawerLayout;
	
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
		
		findViewById(R.id.leftdrawer_setting_btn).setOnClickListener(fClickListener);
		findViewById(R.id.FriendAddButton).setOnClickListener(fClickListener);
		findViewById(R.id.FriendDeleteButton).setOnClickListener(fClickListener);
		
		pAdapter = new PersonListAdapter(this.getContext());
		pDrawerList.setAdapter(pAdapter);
	}
	
	public void setDrawerLayout(DrawerLayout layout) {
		drawerLayout = layout;
	}
	
	private View.OnClickListener fClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			
			int id = v.getId();
			if (id == R.id.FriendAddButton) {
				//FriendAddDialog dialog = new FriendAddDialog(getContext());
				//dialog.show();
			} else if (id == R.id.FriendDeleteButton) {
				if(drawerLayout.isDrawerOpen(LeftDrawer.this)) {
					drawerLayout.closeDrawers();
				}
			}
			switch (v.getId()) {
			case R.id.FriendAddButton:
				Intent intent = new Intent(getContext(), FriendInfoAddActivity.class);
				getContext().startActivity(intent);
				break;
				
			case R.id.FriendDeleteButton:
				//삭제 다이얼로그 호출
				FriendDeleteDialog dialog2 = new FriendDeleteDialog(getContext());
				dialog2.show();
				break;
				
			case R.id.leftdrawer_setting_btn:
				Intent intent2 = new Intent(getContext(), MyInfoAddActivity.class);
				getContext().startActivity(intent2);
				break;
			}
		}
		
	};
	
	public void refresh() {
		pAdapter.notifyDataSetChanged();
	}
}
