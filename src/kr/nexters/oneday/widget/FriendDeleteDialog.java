package kr.nexters.oneday.widget;

import kr.nexters.oneday.MyInfoAddActivity;
import kr.nexters.oneday.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FriendDeleteDialog extends AlertDialog.Builder{
	
	private Context context;
	private TextView dialogTitle;
	private TextView delDialogMassage;
	private TextView delDialogTitle;
	private EditText name;
	private EditText phonenumber;
	
	
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
				dialog.dismiss();		

			}
		});
		this.setNegativeButton(R.string.cancel, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
//				Toast.makeText(context,R.string.cancel,Toast.LENGTH_SHORT).show();
			}
		});
	}


}
