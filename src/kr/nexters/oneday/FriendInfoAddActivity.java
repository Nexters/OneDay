package kr.nexters.oneday;

import kr.nexters.oneday.vo.Person;
import kr.nexters.oneday.widget.TimeTableView;
import kr.nexters.oneday.widget.TitleLayout;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FriendInfoAddActivity extends Activity {

	private TitleLayout titleLayout;
	private TimeTableView tableView;
	FriendAddDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_friend_add);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.custom_title);
		 
		titleLayout = new TitleLayout(getWindow());

		tableView = (TimeTableView) findViewById(R.id.tableView);

		titleLayout.setButtonR2(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog = new FriendAddDialog(FriendInfoAddActivity.this);
				dialog.setCancelable(false); //밖 터치시 종료되지 않게
				dialog.show();
			}
		});

	}

	public void saveFriendInfo() {
		Person friend = new Person();
		friend.setName(dialog.textName);
		friend.setTimeList(tableView.getAllSelectedTimeInfo());

		Common.addPerson(friend);
		Common.addSelectedPerson(friend);
	}

	public class FriendAddDialog extends Dialog implements OnClickListener {
		EditText name;
		EditText phone_number;
		Button okButton;
		Button cancelButton;
		Context mContext;
		public String textName, textPhone;

		public FriendAddDialog(Context context) {
			super(context);
			mContext = context;
			/** 'Window.FEATURE_NO_TITLE' - Used to hide the title */ 
			requestWindowFeature(Window.FEATURE_NO_TITLE);  
			setContentView(R.layout.add_dialog);

			name = (EditText)findViewById(R.id.dialog_edit_name);
			phone_number = (EditText)findViewById(R.id.dialog_edit_phonenumber);
			okButton = (Button)findViewById(R.id.btn_ok);
			cancelButton = (Button)findViewById(R.id.btn_cancel);

			okButton.setOnClickListener(this);
			cancelButton.setOnClickListener(this);
		}
		
		public String getTextName() {
			return textName;
		}

		public String getTextPhone() {
			return textPhone;
		}

		@Override
		public void onClick(View v) {
			if(v == okButton) {
				if(TextUtils.isEmpty(name.getText())) {
					Toast.makeText(mContext, "name is empty", Toast.LENGTH_SHORT).show();
				} else {
					textName = name.getText().toString();
					saveFriendInfo();  
					dismiss();
					finish();
				}

			} else if(v == cancelButton) {
				dismiss();
			}
		}

	}

}
