package kr.nexters.oneday;

import kr.nexters.oneday.database.DBHelper;
import kr.nexters.oneday.database.PersonDBAdapter;
import kr.nexters.oneday.vo.Person;
import kr.nexters.oneday.widget.LeftDrawer;
import kr.nexters.oneday.widget.TimeTableView;
import kr.nexters.oneday.widget.TitleLayout;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class FriendInfoAddActivity extends Activity {

	private TitleLayout titleLayout;
	private TimeTableView tableView;
	private DrawerLayout drawerLayout;
	private LeftDrawer drawerView;
	private FriendAddDialog dialog;

	private String text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_friend_add);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.custom_title);

		titleLayout = new TitleLayout(getWindow());

		titleLayout.setTitle("친구 추가");

		tableView = (TimeTableView) findViewById(R.id.tableView);

		titleLayout.setButtonL(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		}, TitleLayout.BUTTON_EXIT_RES);

		titleLayout.setButtonR(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog = new FriendAddDialog();
				dialog.setCancelable(false); //밖 터치시 종료되지 않게
				dialog.show();
			}
		}, TitleLayout.BUTTON_CHECK_RES);

		Intent intent = getIntent();
		text = intent.getStringExtra("TextIn1");

		if(text != null) {
//			titleLayout.setTitle(text);
			titleLayout.setTitle("친구수정 (" + text + ")");

			Person p = Common.getPerson(text);
			tableView.addPerson(p);
			tableView.setModifiedMode();
		}
	}

	public void toggleDrawer() {
		if (drawerLayout.isDrawerOpen(drawerView) == false){
			drawerLayout.openDrawer(drawerView);
		} else {
			drawerLayout.closeDrawer(drawerView);
		}
	}

	public void saveFriendInfo() {
		Person friend = new Person();
		friend.setName(dialog.name.getText().toString());
		friend.setPhoneNumber(dialog.phoneNumber.getText().toString());
		friend.setTimeList(tableView.getAllSelectedTimeInfo());

		Common.addPerson(friend);
		Common.addSelectedPerson(friend);
	}

	public class FriendAddDialog extends Dialog implements OnClickListener {
		private EditText name;
		private EditText phoneNumber;
		private EditText group;
		private View saveContinue;

		private FriendAddDialog() {
			super(FriendInfoAddActivity.this);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.add_dialog);

			name = (EditText)findViewById(R.id.add_dialog_name);
			phoneNumber = (EditText)findViewById(R.id.add_dialog_tel);
			saveContinue = findViewById(R.id.saveContinue);
			
			if(text != null) {
				Person p = Common.getPerson(text);
				name.setText(p.getName());
				phoneNumber.setText(p.getPhoneNumber());
				saveContinue.setVisibility(View.GONE);
			}

			findViewById(R.id.add_dialog_check).setOnClickListener(this);
			findViewById(R.id.add_dialog_exit).setOnClickListener(this);
			saveContinue.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			switch(v.getId()) {
			case R.id.add_dialog_check:
				if(TextUtils.isEmpty(name.getText())) {
					Toast.makeText(Common.getMainContext(), "name is empty", Toast.LENGTH_SHORT).show();
				} else {
					saveFriendInfo();
					dismiss();
					finish();
				}
				break;
			case R.id.add_dialog_exit:
				dismiss();
				break;
			case R.id.saveContinue:
				saveFriendInfo();
				dismiss();
				tableView.clearAllSector();
				break;
			}
		}
	}

}
