package kr.nexters.oneday.widget;

import java.util.ArrayList;
import kr.nexters.oneday.MyInfoAddActivity;
import kr.nexters.oneday.R;
import kr.nexters.oneday.adapter.PersonListAdapter;
import kr.nexters.oneday.vo.Person;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FriendDeleteDialog extends AlertDialog.Builder{
	
	private Context context;
	private TextView dialogTitle;
	private TextView delDialogMassage;
	private TextView delDialogTitle;
	private EditText name;
	private EditText phonenumber;
	private ArrayList<Person> pItem = null;
	private PersonListAdapter pAdapter = null;
	
	
	public FriendDeleteDialog(Context context) {
		super(context);
		this.context = context;
		initialize();
		// TODO Auto-generated constructor stub
	}
	
	private void initialize(){
		this.setTitle(null);
		initView();
		initControls();
	}
	private void initView(){
		LayoutInflater inflater = LayoutInflater.from(context);
		View dialoglayout = inflater.inflate(R.layout.delete_dialog, null);
		
		delDialogTitle = (TextView) dialoglayout.findViewById(R.id.delete_dialog_title);
		delDialogMassage = (TextView) dialoglayout.findViewById(R.id.delete_dialog_message);
		this.setView(dialoglayout);

	}
	private void initControls(){
		delDialogTitle.setText(R.string.title_del);
		this.setPositiveButton(R.string.confirm, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//				dialog.dismiss();		
				//드로워레이어에서 뭐가 선택됬는지 알아온다음 삭제
//				if (pos != ListView.INVALID_POSITION) {
//					Person person = (Person)pItem.get(pos);		
//					pItem.remove(pos);
//				}
//				pAdapter.isSeleted(0);
				pAdapter.notifyDataSetChanged();
			}
		});
		this.setNegativeButton(R.string.cancel, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Toast.makeText(context,R.string.cancel,Toast.LENGTH_SHORT).show();
			}
		});
	}

}
