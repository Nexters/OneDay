package kr.nexters.oneday.widget;

import kr.nexters.oneday.Common;
import kr.nexters.oneday.R;
import kr.nexters.oneday.adapter.PersonListAdapter;
import kr.nexters.oneday.util.ViewUtil;
import kr.nexters.oneday.vo.Person;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class LeftDrawer extends RelativeLayout {
	
	private ListView pDrawerList;
	private PersonListAdapter pAdapter = null;
	private TextView meText;
	
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

		meText = (TextView)findViewById(R.id.leftdrawer_name);
		meText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewUtil.setClickPersonItem(meText, Common.getPerson("나"));
			}
		});
	}
	
	public void setOnClickListenerViews(OnClickListener l) {
		findViewById(R.id.leftdrawer_setting_btn).setOnClickListener(l);
		findViewById(R.id.FriendAddButton).setOnClickListener(l);
		findViewById(R.id.FriendDeleteButton).setOnClickListener(l);
	}

	public void refresh() {
		pAdapter.notifyDataSetChanged();
		Person me = Common.getPerson("나");
		if(me != null) {
			if(me.selected) {
				meText.setBackgroundResource(R.drawable.bg_list_p);
			} else {
				meText.setBackgroundResource(R.color.transparent);
			}
		}
	}
}
