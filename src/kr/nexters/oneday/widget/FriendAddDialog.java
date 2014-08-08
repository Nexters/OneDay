package kr.nexters.oneday.widget;

import kr.nexters.oneday.MyInfoAddActivity;
import kr.nexters.oneday.R;
import kr.nexters.oneday.R.id;
import kr.nexters.oneday.R.layout;
import kr.nexters.oneday.R.string;
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
				dialog.dismiss();
			
				Intent intent = new Intent(context, MyInfoAddActivity.class);
				context.startActivity(intent);
				
				
//				StringBuilder str = new StringBuilder();
//				str.append("name = " +name.getText());
//				str.append("\n");
//				str.append("number = " +phonenumber.getText());
//				Toast.makeText(context,str,Toast.LENGTH_SHORT).show();

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
