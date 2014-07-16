package kr.nexters.oneday;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

	public static final String PREF_KEY = "key";
	public static final String PREF_VALUE = "value";
	public static final String PREF_DEFVALUE = "defvalue";

	static Context context;
// single ton 방식으로
	
	public PrefManager(Context context) {
		this.context = context;
	}

	//putInt putString 요런것들 중복 부분 빼면될듯요!!
	public void putInt(String key, int value) {
		SharedPreferences pref = context.getSharedPreferences(PREF_KEY, 0);
		SharedPreferences.Editor editor = pref.edit();

		editor.putInt(key, value);
		editor.commit();
	}

	public void putString(String key, String value) {
		SharedPreferences pref = context.getSharedPreferences(PREF_KEY, 0);
		SharedPreferences.Editor editor = pref.edit();

		editor.putString(key, value);
		editor.commit();
	}

	public void putBoolean(String key, boolean value) {
		SharedPreferences pref = context.getSharedPreferences(PREF_KEY, 0);
		SharedPreferences.Editor editor = pref.edit();

		editor.putBoolean(key, value);
		editor.commit();
	}

	public int getInt(String key, int defValue) {
		SharedPreferences pref = context.getSharedPreferences(PREF_KEY, 0);

		// 여긴 왜 try catch()??
		try {
			return pref.getInt(key, defValue);
		} catch (Exception e) {
			return defValue;
		}
	}

	public String getString(String key, String defValue) {
		SharedPreferences pref = context.getSharedPreferences(PREF_KEY, 0);

		try {
			return pref.getString(key, defValue);
		} catch (Exception e) {
			return defValue;
		}
	}

	public boolean getBoolean(String key, boolean defValue) {
		SharedPreferences pref = context.getSharedPreferences(PREF_KEY, 0);

		try {
			return pref.getBoolean(key, defValue);
		} catch (Exception e) {
			return defValue;
		}
	}

}
