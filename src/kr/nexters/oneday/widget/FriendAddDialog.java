package kr.nexters.oneday.widget;

import kr.nexters.oneday.Common;
import kr.nexters.oneday.FriendInfoAddActivity;
import kr.nexters.oneday.R;
import kr.nexters.oneday.R.id;
import kr.nexters.oneday.R.layout;
import kr.nexters.oneday.R.string;
import kr.nexters.oneday.vo.Person;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FriendAddDialog extends AlertDialog.Builder{
	private Context context;
	private TextView dialogTitle;
	private EditText name;
	private EditText phonenumber;
	public FriendInfoAddActivity friendInfo;
	
	public FriendAddDialog(Context context) {
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
		View dialoglayout = inflater.inflate(R.layout.add_dialog, null);
		
		dialogTitle = (TextView) dialoglayout.findViewById(R.id.dialog_title);
		name = (EditText) dialoglayout.findViewById(R.id.dialog_edit_name);
		phonenumber = (EditText) dialoglayout.findViewById(R.id.dialog_edit_phonenumber);
		this.setView(dialoglayout);
	}
	private void initControls(){
		dialogTitle.setText(R.string.title_add);
		this.setPositiveButton(R.string.confirm, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
//				dialog.dismiss();
			
				//테이블 뷰 세이브 
				friendInfo.saveFriendInfo();
			
				dialog.dismiss();
				
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
