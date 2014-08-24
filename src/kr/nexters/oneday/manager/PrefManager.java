package kr.nexters.oneday.manager;

import kr.nexters.oneday.Common;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PrefManager {

	public static final String PREF_NAME = "name";

	private static volatile PrefManager instance;
	private SharedPreferences prefs;
	private Editor editor;

	private PrefManager() {
		prefs = Common.getMainContext().getSharedPreferences(PREF_NAME, 0); //파일이름 & 모드
		editor = prefs.edit(); //파일 쓰기
	}

	//3.초기화
	public static PrefManager getInstance() {
		if (instance == null) {
			synchronized (PrefManager.class) {
				if(instance == null) {
					instance = new PrefManager();
				}
			}
		}
		return instance;
	}

	public void putInt(String key, int value) {
		editor.putInt(key, value);
		editor.commit();
	}

	public void putString(String key, String value) {
		editor.putString(key, value);
		editor.commit();
	}

	public void putBoolean(String key, boolean value) {
		editor.putBoolean(key, value);
		editor.commit();
	}

	public int getInt(String key, int defValue) {
		return prefs.getInt(key, defValue);
	}

	public String getString(String key, String defValue) {
		return prefs.getString(key, defValue);
	}

	public boolean getBoolean(String key, boolean defValue) {
		return prefs.getBoolean(key, defValue);
	}

}
