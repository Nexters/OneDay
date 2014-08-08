package kr.nexters.oneday.adapter;


import java.util.ArrayList;

import kr.nexters.oneday.R;
import kr.nexters.oneday.R.id;
import kr.nexters.oneday.vo.Person;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonListAdapter extends BaseAdapter{
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<Person> pList;
	private int layout;

	public PersonListAdapter(Context context, int layout, ArrayList<Person> pList ) {
		this.context = context;
		this.layout = layout;
		this.pList = pList; 
		inflater = (LayoutInflater)context.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return pList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
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
			convertView = inflater.inflate(layout, parent, false);

			viewHolder = new PersonViewHolder();
			viewHolder.name = (TextView) convertView.findViewById(R.id.textView1);
			viewHolder.v_button = (ImageButton)convertView.findViewById(R.id.SettingButton);
			viewHolder.checkbox = (CheckBox)convertView.findViewById(R.id.checkBox1);

			convertView.setTag(viewHolder);
		}

		// 캐시된 뷰가 있을 경우 저장된 뷰홀더를 사용한다
		else
		{
			viewHolder = (PersonViewHolder) convertView.getTag();
		}

		viewHolder.name.setText(pList.get(position).getName());

		//checkBox랑 button이벤트 리스너 등록 및 작성!!

		return convertView;
	}

}

class PersonViewHolder {
	public TextView name = null;
	public ImageButton v_button = null;
	public CheckBox checkbox = null;
}
