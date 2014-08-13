package kr.nexters.oneday.adapter;


import java.util.ArrayList;

import kr.nexters.oneday.Common;
import kr.nexters.oneday.R;
import kr.nexters.oneday.vo.Person;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class PersonListAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private ArrayList<Person> pList;

	public PersonListAdapter(Context context) {
		this.pList = new ArrayList<Person>(Common.getPersonSet()); 
		inflater = (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE);
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

		// button이벤트 리스너 등록 및 작성!!

		return convertView;
	}

	@Override
	public void notifyDataSetChanged() {
		pList.clear();
		pList.addAll(Common.getPersonSet());
		super.notifyDataSetChanged();
	}

	class PersonViewHolder {
		private TextView name = null;
		private ImageButton v_button = null;
	}
}

