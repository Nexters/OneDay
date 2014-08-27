package kr.nexters.oneday.database;

import android.content.ContentValues;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashSet;
import kr.nexters.oneday.Common;
import kr.nexters.oneday.vo.Person;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.SensorManager;

public class DBHelper extends SQLiteOpenHelper {
	
	// column's name for "person" table
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "name"; 
	//public static final String KEY_GROUPID = "groupid";
	public static final String KEY_PHONENUMBER = "phonenumber";
	public static final String KEY_SELECTED = "selected"; 
	
	// column's name for "timetable" table
	public static final String KEY_DAYNUMBER = "day";
	public static final String KEY_TIMENUMBER = "time";
	
	// DATABASE name
	public static final String DATABASE_NAME = "data.db";

	//database sql creation statement
	public static final String DATABASE_TABLE_person = "person";
	public static final String DATABASE_TABLE_timetable ="timetable";
	
	
	public static final int DATABASE_VERSION = 3;
	
	private static final String DATABASE_CREATE_PERSON = "CREATE TABLE person (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT not null, phonenumber TEXT, selected INTEGER);";
	private static final String DATABASE_CREATE_TIMETABLE ="CREATE TABLE timetable (_id INTEGER, day TEXT, time TEXT);";
	
	public DBHelper(Context context){
		super(context,DATABASE_NAME,null, DATABASE_VERSION);
	}
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}


	public void onCreate(SQLiteDatabase db) {

		db.execSQL(DATABASE_CREATE_PERSON);
		db.execSQL(DATABASE_CREATE_TIMETABLE);
	}//end onCreate





	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		String sql = "drop table if exists person";
		db.execSQL(sql);
		onCreate(db);
	}//end onUpgrade
	

}//end class
