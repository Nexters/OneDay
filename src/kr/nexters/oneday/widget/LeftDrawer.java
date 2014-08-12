package kr.nexters.oneday.widget;

import java.util.ArrayList;

import kr.nexters.oneday.Common;
import kr.nexters.oneday.FriendInfoAddActivity;
import kr.nexters.oneday.FriendInfoAddActivity.FriendAddDialog;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;


public class LeftDrawer extends RelativeLayout {

	private ListView pDrawerList;
	ArrayList<Person> pItem = null;
	PersonListAdapter pAdapter = null;
	Person person;
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
		//		imgView = (ImageView)findViewById(R.id.imageView1);
		pDrawerList = (ListView) findViewById(R.id.listView1);

		pItem = new ArrayList<Person>();

//		person = new Person("박준회", 1234);
//		pItem.add(person);
		person = new Person();
		person.setName("친구");
		pItem.add(person);
		
		findViewById(R.id.FriendAddButton).setOnClickListener(fClickListener);
		findViewById(R.id.FriendDeleteButton).setOnClickListener(fClickListener);

		findViewById(R.id.SettingButton).setOnClickListener(fClickListener);
		
		pAdapter = new PersonListAdapter(this.getContext(), R.layout.person_item, pItem);
		pDrawerList.setAdapter(pAdapter);

		pDrawerList.setOnItemClickListener(pItemClickListener);
		
	}
	
	public void setDrawerLayout(DrawerLayout layout) {
		drawerLayout = layout;
	}

	AdapterView.OnItemClickListener pItemClickListener = 
			new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, 
				int position, long id) {

		}
	};
		
	private View.OnClickListener fClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.FriendAddButton) {
				//FriendAddDialog dialog = new FriendAddDialog(getContext());
				//dialog.show();
			} else if (id == R.id.FriendDeleteButton) {

			}
		}
		
	};

}
