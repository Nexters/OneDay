package kr.nexters.oneday;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import kr.nexters.oneday.adapter.PersonListAdapter;
import kr.nexters.oneday.database.DBHelper;
import kr.nexters.oneday.database.PersonDBAdapter;
import kr.nexters.oneday.util.ViewUtil;
import kr.nexters.oneday.vo.Person;
import kr.nexters.oneday.widget.FriendDeleteDialog;
import kr.nexters.oneday.widget.LeftDrawer;
import kr.nexters.oneday.widget.TimeTableView;
import kr.nexters.oneday.widget.TitleLayout;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private DrawerLayout drawerLayout;
	private LeftDrawer drawerView;
	private TimeTableView tableView;
	private TitleLayout titleLayout;

	public static Context context;

	private int futureTask = -1;
	private static final int FUTURE_TASK_START_ACT_MY_INFO = 1;
	private static final int FUTURE_TASK_START_ACT_FRIEND_INFO = 2;
	private static final int FUTURE_TASK_DELETE_FRIEND_INFO = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//LoadCommonFromDatabase();

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.custom_title);

		context = this;

		titleLayout = new TitleLayout(getWindow());

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		drawerView = (LeftDrawer) findViewById(R.id.left_drawer);
		drawerView.setOnClickListenerViews(clicker);
		tableView = (TimeTableView) findViewById(R.id.tableView);
		tableView.setSelectedMode(false);

		drawerLayout.setDrawerListener(myDrawerListener);

		titleLayout.setButtonL(new OnClickListener() {

			@Override
			public void onClick(View v) {
				toggleDrawer();
			}
		}, TitleLayout.BUTTON_TOGGLE_RES);

		titleLayout.setButtonR(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//테이블뷰 캡쳐
				tableView.setDrawingCacheEnabled(false);
				tableView.buildDrawingCache();
				tableView.setDrawingCacheEnabled(true);

				Bitmap captureView = tableView.getDrawingCache();
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/capture.PNG");
					captureView.compress(Bitmap.CompressFormat.PNG, 100, fos);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
//				Toast.makeText(getApplicationContext(), "Captured!", Toast.LENGTH_LONG).show();

				//공유 인텐트 작성
				Intent intentSend  = new Intent(Intent.ACTION_SEND);

				intentSend.setType("image/*");

				intentSend.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(Environment.getExternalStorageDirectory().toString()+"/capture.PNG")));

				startActivity(Intent.createChooser(intentSend, "BINN"));

			}
		}, TitleLayout.BUTTON_EXPORT_RES);
	}

	@Override
	protected void onResume() {
		super.onResume();
		tableView.setPerson(Common.getPersonSelectedSet());
		leftDrawerRefresh();

		//sd카드 파일 삭제
		File file = new File(Environment.getExternalStorageDirectory().toString()+"/capture.PNG");
		if(file.exists()) //파일인지 확인하고 삭제
		file.delete();
	}

	public void checkTable() {
		tableView.setPerson(Common.getPersonSelectedSet());
		
	}
	
	public void leftDrawerRefresh() {
	    drawerView.refresh();
	}

	public void toggleDrawer() {
		if (drawerLayout.isDrawerOpen(drawerView) == false){
			drawerLayout.openDrawer(drawerView);
		} else {
			drawerLayout.closeDrawer(drawerView);
		}
	}

	@Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && drawerLayout.isDrawerOpen(drawerView)) {
			drawerLayout.closeDrawer(drawerView);
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	private DrawerListener myDrawerListener = new DrawerListener() {
		private float lastTranslate = 0.0f;

		@Override
		public void onDrawerClosed(View drawerView) {
			switch(futureTask) {
			case FUTURE_TASK_START_ACT_MY_INFO:
				Intent intent2 = new Intent(MainActivity.this, MyInfoAddActivity.class);
				startActivity(intent2);
				break;
			case FUTURE_TASK_START_ACT_FRIEND_INFO:
				Intent intent = new Intent(MainActivity.this, FriendInfoAddActivity.class);
				startActivity(intent);
				break;
			case FUTURE_TASK_DELETE_FRIEND_INFO:
				//삭제 다이얼로그 띄우는 부분
				FriendDeleteDialog dialog2 = new FriendDeleteDialog(MainActivity.this);
				dialog2.setCancelable(false); //밖 터치시 종료되지 않게
				dialog2.show();
				break;
			}
			futureTask = -1;
		}
		@Override public void onDrawerOpened(View drawerView) {  }
		@Override public void onDrawerStateChanged(int newState) {  }

		@SuppressLint("NewApi")
		@Override
		public void onDrawerSlide(View v, float slideOffset) {
			float moveFactor = (drawerView.getWidth() * slideOffset) / 2;

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				tableView.setTranslationX(moveFactor);
			} else {
				TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
				anim.setDuration(0);
				anim.setFillAfter(true);
				tableView.startAnimation(anim);

				lastTranslate = moveFactor;
			}
		}
	};

	private OnClickListener clicker = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if(drawerLayout.isDrawerOpen(drawerView)) {
				drawerLayout.closeDrawers();
			}
			switch (v.getId()) {
			case R.id.FriendAddButton:
				futureTask = FUTURE_TASK_START_ACT_FRIEND_INFO;
				break;

			case R.id.FriendDeleteButton:
				futureTask = FUTURE_TASK_DELETE_FRIEND_INFO;
				break;

			case R.id.leftdrawer_setting_btn:
				futureTask = FUTURE_TASK_START_ACT_MY_INFO;
				break;
			}
		}
	};

}

