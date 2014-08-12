package kr.nexters.oneday;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;


public class LeftDrawer extends RelativeLayout {

	//	private ImageView imgView;
	//	private TextView textView;
	private ListView pDrawerList;
	ArrayList<Person> pItem = null;
	PersonListAdapter pAdapter = null;
	private LayoutInflater inflater;
	Person person;

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

		person = new Person(1, "박준회");
		pItem.add(person);

		View footerView =  ((LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer, null, false);
		pDrawerList.addFooterView(footerView);

		footerView.findViewById(R.id.FriendAddButton).setOnClickListener(fClickListener);
		footerView.findViewById(R.id.FriendDeleteButton).setOnClickListener(fClickListener);

		pAdapter = new PersonListAdapter(this.getContext(), R.layout.person_item, pItem);
		pDrawerList.setAdapter(pAdapter);

		pDrawerList.setOnItemClickListener(pItemClickListener);

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
				FriendAddDialog dialog = new FriendAddDialog(getContext());
				dialog.show();
			} else if (id == R.id.FriendDeleteButton) {
			}
		}
	};




}
