package kr.nexters.oneday.widget;


import java.util.ArrayList;
import kr.nexters.oneday.Common;
import kr.nexters.oneday.R;
import kr.nexters.oneday.vo.Person;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class FriendDeleteDialog extends Dialog implements android.view.View.OnClickListener{
	
	private Context context;
	private ImageButton btn_exit, btn_check;
	private DeleteListAdapter DeleteAdapter = null;
	private ListView DelList;
	
	public FriendDeleteDialog(Context context) {
		super(context);
		this.context = context;				
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		setContentView(R.layout.delete_dialog);
		
//		DeleteAdapter = new DeleteListAdapter(context, R.layout.delete_dialog_list_item, pList);
		DeleteAdapter = new DeleteListAdapter(context);
		DelList = (ListView)findViewById(R.id.delete_list);
		DelList.setAdapter(DeleteAdapter);
		
		btn_exit = (ImageButton)findViewById(R.id.delete_dialog_exit);
		btn_check = (ImageButton)findViewById(R.id.delete_dialog_check);
		btn_exit.setOnClickListener(this);
		btn_check.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.delete_dialog_exit:
			dismiss();
			break;
			
		case R.id.delete_dialog_check:
			dismiss();
			break;
		}
	}
}

//어댑터 클래스
class DeleteListAdapter extends BaseAdapter {
	Context context;
	LayoutInflater inflater;
	ArrayList<Person> pList;
	int layout;
	
	public DeleteListAdapter(Context context) {
		this.context = context;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		pList.remove(new Person("나", null, null, true));
		this.pList = new ArrayList<Person>(Common.getPersonSet()); 
	}

	public int getCount() {
		return pList.size();
	}

	public String getItem(int position) {
		return pList.get(position).getName();
	}

	public long getItemId(int position) {
		return position;
	}

	// 각 항목의 뷰 생성
	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = position;
		// TODO Auto-generated method stub
		PersonViewHolder viewHolder;

		//캐시된 뷰가 없는 경우 새로 생성하고 뷰홀더 저장한다
		if(convertView == null)
		{
			convertView = inflater.inflate(R.layout.delete_dialog_list_item, parent, false);

			viewHolder = new PersonViewHolder();
			viewHolder.name = (TextView) convertView.findViewById(R.id.delete_name);
	
			convertView.setTag(viewHolder);
		}

		 // 캐시된 뷰가 있을 경우 저장된 뷰홀더를 사용한다
		else
		{
			viewHolder = (PersonViewHolder) convertView.getTag();
		}

		viewHolder.name.setText(pList.get(position).getName());

		return convertView;
	}
	
	class PersonViewHolder {
		private TextView name = null;
	}
}
