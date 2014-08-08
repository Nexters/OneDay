package kr.nexters.oneday.database;

import java.util.ArrayList;
import java.util.HashSet;

import kr.nexters.oneday.Common;
import kr.nexters.oneday.vo.Person;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	public void onCreate(SQLiteDatabase db) {

		String sql = "create table person ( " +
				" _id integer primary key autoincrement , " +
				" name text , " +
				" number integer )";
		db.execSQL(sql);
	}//end onCreate

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		String sql = "drop table if exists person";
		db.execSQL(sql);

		onCreate(db);
	}//end onUpgrade
	
	public void loadPersonInfo() {
		
	}
	
	public void insertPerson() {
		
	}
	
	public Person select() {
		String sql = "select * from tableName";
		
		return null;
	}
}//end class
