package kr.nexters.oneday.database;

import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHandler  {

	public static final String KEY_NAME = "name"; 
	public static final String KEY_PHONENUMBER = "phonenumber"; 
	public static final String KEY_TIMETABLE = "timetable"; 
	public static final String KEY_GROUPID = "groupid"; 
	public static final String KEY_ROWID = "rowid"; 

	//database sql creation statement

	private static final String DATABASE_NAME = "data";

	private static final String DATABASE_TABLE = "person";
	
	private static final int DATABASE_VERSION = 2;


	private SQLiteDatabase mDb;
	private DBHelper helper;

	
//	helper = new DBHelper(context, "sample", null, 1);

	
	  public DBHandler(Context context) {
	        DBHandler.this.helper = new DBHelper(context, null, null, 1);
	        this.mDb = helper.getWritableDatabase();
	    }

	  //open
	    public static DBHandler open(Context context) throws SQLException {
	        DBHandler handler = new DBHandler(context);

	        return handler;
	    }

	  
	//close
	  public void close(){
		helper.close();
	}


	public long insertPerson(String name, int phonenumber, String table) {

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_NAME, name);

		initialValues.put(KEY_PHONENUMBER, phonenumber);



		return mDb.insert(DATABASE_TABLE, null, initialValues);

	}
	//delete
	public boolean deletePerson(long rowId) {


		return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;

	}




	public void loadPersonInfo() {
		helper.loadPersonInfo();
	}

	

	public boolean updatePerson(String name, int phonenumber, String timetable, long rowId){
		
		ContentValues args = new ContentValues();

		args.put(KEY_NAME, name);

		args.put(KEY_PHONENUMBER, phonenumber);

		args.put(KEY_TIMETABLE, timetable);



		return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;

	}


}//end class