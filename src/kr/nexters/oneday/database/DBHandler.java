package kr.nexters.oneday.database;

import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHandler  {
	private DBHelper helper;

	// 초기화 작업
	public DBHandler(Context context) {
		helper = new DBHelper(context, "sample", null, 1);
	}

	//open
	public static DBHandler open(Context context) {
		return new DBHandler(context);
	}
	
	public void loadPersonInfo() {
		helper.loadPersonInfo();
	}

	//close
	public void close() {
		helper.close();
	}

	//저장
	public void insert(String name, int number) {
		helper.insertPerson();
		//	  db = helper.getWritableDatabase();
		//	  ContentValues values = new ContentValues();
		//	        values.put("name", name);
		//	        values.put("number", number);
		//	        db.insert("person", null, values);
	}//end insert

	//수정
	public void update(String name, int number) {
		//	  db = helper.getWritableDatabase();
		//	  ContentValues values = new ContentValues();
		//	        values.put("number", number);
		//	        db.update("person", values, "name=?", new String[]{name});
	}//end update

	//삭제
	public void delete(String name) {
		//	  db = helper.getWritableDatabase();
		//	        db.delete("person", "name=?", new String[]{name});
	}//end delete

	//검색
	//	 public Cursor select() {
	//	  db = helper.getReadableDatabase();
	//	  Cursor c = db.query("person", null, null, null, null, null, null);
	//	  return c;
	//	 }//end select
}//end class