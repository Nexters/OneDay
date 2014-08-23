package kr.nexters.oneday.database;


import java.util.ArrayList;
import java.util.List;

import kr.nexters.oneday.vo.Person;
import kr.nexters.oneday.vo.TimeInfo;
import kr.nexters.oneday.widget.TimeTableView.DAY;
import kr.nexters.oneday.widget.TimeTableView.TIME;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
   /* Modified by Daehyun on 2014/08/15
    * 
    * 
    */
public class PersonDBAdapter { 
   

    public static final String KEY_TITLE = "title"; 
    public static final String KEY_BODY = "body"; 
    public static final String KEY_ROWID = "_id"; 
   
    private static final String TAG = "PersonDbAdapter"; 
    //private DatabaseHelper mDbHelper; 
    private DBHelper mDbHelper;
    private SQLiteDatabase mDb;
	public void addTimeInfo(Person person){
		ContentValues cvalue = new ContentValues();
		long rowid = person.getrowId();
		for(TimeInfo timeinfo : person.getTimeList()){
		cvalue.put(DBHelper.KEY_ROWID, rowid);
		cvalue.put(DBHelper.KEY_DAYNUMBER, timeinfo.getDay().name());
		cvalue.put(DBHelper.KEY_TIMENUMBER, timeinfo.getTime().name());
		mDb.insert(DBHelper.DATABASE_TABLE_timetable, null,cvalue);
		Log.i("timetable_db","id : "+rowid + ", DayNumber : "+ timeinfo.getDay().name() +", TimeNumber : " + timeinfo.getTime().name() +" inserted on timetable.table");
		cvalue.clear();
		}
	}
	public List<TimeInfo> getUserTimeInfos(long rowid){
		List<TimeInfo> ti  = new ArrayList<TimeInfo>();
		Cursor mCursor  = fetchTimeInfoById(rowid);
		while(mCursor.moveToNext()){
			DAY  day = DAY.valueOf(mCursor.getString(mCursor.getColumnIndex(DBHelper.KEY_DAYNUMBER)));
			TIME time = TIME.valueOf(mCursor.getString(mCursor.getColumnIndex(DBHelper.KEY_TIMENUMBER)));
			ti.add(new TimeInfo(day,time));
		}
		mCursor.close();
		return ti;
	}
	public Cursor fetchTimeInfoById(long rowid){
		Cursor mCursor =
      		  mDb.query(true, DBHelper.DATABASE_TABLE_timetable, new String[] { 
      				  DBHelper.KEY_ROWID,
      				  DBHelper.KEY_DAYNUMBER,
      				  DBHelper.KEY_TIMENUMBER,
      		  }, KEY_ROWID + "=" + rowid, null, null, null, null,null);
		
      if (mCursor != null) {

          mCursor.moveToFirst();

      }
      return mCursor;
	}
	public Cursor fetchTimeInfo(Person person){
		
		/*String sql = "SELECT * From LEFT JOIN " + DBHelper.DATABASE_TABLE_timetable +" on "+
		DBHelper.DATABASE_TABLE_person+"."+DBHelper.KEY_ROWID +" = " +
		DBHelper.DATABASE_TABLE_timetable+"."+DBHelper.KEY_ROWID +
		" WHERE person._id = "+rowid;*/
		long rowid = person.getrowId();
        Cursor mCursor =
        		  mDb.query(true, DBHelper.DATABASE_TABLE_timetable, new String[] { 
        				  DBHelper.KEY_ROWID,
        				  DBHelper.KEY_DAYNUMBER,
        				  DBHelper.KEY_TIMENUMBER,
        		  }, KEY_ROWID + "=" + rowid, null, null, null, null,null);
        if (mCursor != null) {

            mCursor.moveToFirst();

        }
        return mCursor;
	}
	

	
	
	public List<Person> getPeople() {
		// TODO Auto-generated method stub
		List<Person> personlist = new ArrayList<Person>();
		Cursor mCursor = this.fetchAllPerson();
		while(mCursor.moveToNext()){
			Person persontmp = new Person();
			persontmp.setId(mCursor.getInt(mCursor.getColumnIndex(DBHelper.KEY_ROWID)));
			persontmp.setName(mCursor.getString(mCursor.getColumnIndex(DBHelper.KEY_NAME)));
			persontmp.setPhoneNumber(mCursor.getString(mCursor.getColumnIndex(DBHelper.KEY_PHONENUMBER)));
			persontmp.setTimeList(this.getUserTimeInfos(persontmp.getrowId()));
			
			
			personlist.add(persontmp);			
		}
		mCursor.close();
	
		return personlist;
	}
	public static long addPerson(Person friend) {
		// TODO Auto-generated method stub
		return 0;
	}
	public static Cursor fetchAllPerson() {
		// TODO Auto-generated method stub
		return null;
	}
	public Cursor fetchPerson(Person friend) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
