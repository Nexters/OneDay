package kr.nexters.oneday.adapter;


import java.util.ArrayList;

import kr.nexters.oneday.Common;
import kr.nexters.oneday.FriendStorageActivity;
import kr.nexters.oneday.MainActivity;
import kr.nexters.oneday.R;
import kr.nexters.oneday.vo.Person;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class PersonListAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private ArrayList<Person> pList;
	Context context;

	public PersonListAdapter(Context context) {
		this.context = context;
		this.pList = new ArrayList<Person>(Common.getPersonSet()); 
		pList.remove(new Person("나", null, null, true));

		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return pList.size();
	}

	@Override
	public Object getItem(int position) {
		return pList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	//뷰홀더 적용하는 부분
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = position;
		// TODO Auto-generated method stub
		PersonViewHolder viewHolder;

		//캐시된 뷰가 없는 경우 새로 생성하고 뷰홀더 저장한다
		if(convertView == null)
		{
			convertView = inflater.inflate(R.layout.person_item, parent, false);

			viewHolder = new PersonViewHolder();
			viewHolder.name = (TextView) convertView.findViewById(R.id.leftdrawer_name);
			viewHolder.v_button = (ImageButton)convertView.findViewById(R.id.leftdrawer_setting_btn);

			convertView.setTag(viewHolder);
		}

		 // 캐시된 뷰가 있을 경우 저장된 뷰홀더를 사용한다
		else
		{
			viewHolder = (PersonViewHolder) convertView.getTag();
		}

		viewHolder.name.setText(pList.get(position).getName());

		// set click listener
		viewHolder.name.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (pList.get(pos).selected == true) {
					v.setBackgroundResource(R.color.transparent);
					toggleTable(pList.get(pos), false);
					((MainActivity)MainActivity.context).checkTable();
					pList.get(pos).selected = false;
				} else {
					v.setBackgroundResource(R.drawable.bg_list_p);
					toggleTable(pList.get(pos), true);
					((MainActivity)MainActivity.context).checkTable();
					pList.get(pos).selected = true;
				}
			}
		});
		
		viewHolder.v_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, FriendStorageActivity.class);
				intent.putExtra("TextIn1", pList.get(pos).getName().toString());
				context.startActivity(intent);
			}
		});

		return convertView;
	}
	
	@Override
	public void notifyDataSetChanged() {
		pList.clear();
		pList.addAll(Common.getPersonSet());
		pList.remove(new Person("나", null, null, true));
		super.notifyDataSetChanged();
	}
	
	private void toggleTable(Person p, boolean check) {
		if (check == true) {
			Common.addSelectedPerson(p);
		} else {
			Common.removeSelectedPerson(p);
		}
	}

	class PersonViewHolder {
		private TextView name = null;
		private ImageButton v_button = null;
	}
}

