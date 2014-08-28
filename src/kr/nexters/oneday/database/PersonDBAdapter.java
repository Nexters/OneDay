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
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/* 
 * Modified by Daehyun on 2014/08/15
 */
public class PersonDBAdapter { 
	public static final String KEY_TITLE = "title"; 
	public static final String KEY_BODY = "body"; 
	public static final String KEY_ROWID = "_id"; 
	public static final String KEY_NAME = "name"; 

	private static final String TAG = "PersonDbAdapter"; 
	private DBHelper mDbHelper;
	private SQLiteDatabase mDb;
	
	public PersonDBAdapter(Context ctx){
		this.mDbHelper = new DBHelper(ctx);
		this.mDb = mDbHelper.getWritableDatabase();
	}
	
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
	
	public long addPersonInfo(Person person) {
        ContentValues cvalues = new ContentValues();
        if(contain(person)) {
        	List<Person> list = getPeople(person.getName());
        	for(Person p : list) {
        		deletePreson(p);
        	}
        }
        cvalues.put(DBHelper.KEY_NAME, person.getName());
		//cvalues.put(DBHelper.KEY_GROUPID, "0");
        cvalues.put(DBHelper.KEY_PHONENUMBER, person.getPhoneNumber());
        cvalues.put(DBHelper.KEY_SELECTED, person.getSelected()?1:0);
        return mDb.insert(DBHelper.DATABASE_TABLE_person, null, cvalues);
	}
	
	public boolean deletePreson(Person person){
		return (mDb.delete(DBHelper.DATABASE_TABLE_person, KEY_ROWID + "=" + person.getrowId(), null) > 0) &&
				(mDb.delete(DBHelper.DATABASE_TABLE_timetable,KEY_ROWID + "="+ person.getrowId(),null)>0);
	}
	
    public boolean updatePerson(Person person) {
        ContentValues cvalue = new ContentValues();
        cvalue.put(DBHelper.KEY_NAME, person.getName());
        cvalue.put(DBHelper.KEY_PHONENUMBER, person.getPhoneNumber());
        cvalue.put(DBHelper.KEY_SELECTED, person.getSelected()?1:0);
        return mDb.update(DBHelper.DATABASE_TABLE_person, cvalue, "name = ?", new String[]{person.getName()}) > 0;
    }
    
    public List<Person> getPeople() {
		Cursor mCursor = this.fetchAllPerson();
		return getPeopleFromCursor(mCursor);
	}

	public List<Person> getPeople(String name) {
		Cursor mCursor = this.fetchPerson(name);
		return getPeopleFromCursor(mCursor);
	}
	
	private List<Person> getPeopleFromCursor(Cursor cursor) {
		List<Person> personlist = new ArrayList<Person>();
		while(cursor.moveToNext()){
			Person persontmp = new Person();
			persontmp.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ROWID)));
			persontmp.setName(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME)));
			persontmp.setPhoneNumber(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PHONENUMBER)));
			
			switch((cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_SELECTED )))){
			case 0 :
				persontmp.setSelected(false);
				break;
			default : 
				persontmp.setSelected(true);
				break;
			}
			
			persontmp.setTimeList(this.getUserTimeInfoList(persontmp))	;
			personlist.add(persontmp);			
		}
		cursor.close();

		return personlist;
	}
	
	private List<TimeInfo> getUserTimeInfoList(Person person){
		List<TimeInfo> ti  = new ArrayList<TimeInfo>();
		Cursor mCursor = fetchTimeInfo(person);
		while(mCursor.moveToNext()){
			DAY  day = DAY.valueOf(mCursor.getString(mCursor.getColumnIndex(DBHelper.KEY_DAYNUMBER)));
			TIME time = TIME.valueOf(mCursor.getString(mCursor.getColumnIndex(DBHelper.KEY_TIMENUMBER)));
			ti.add(new TimeInfo(day,time));
		}
		mCursor.close();
		return ti;
	}
	
	//person객체로 TimeInfo 를 얻어
	private Cursor fetchTimeInfo(Person person) {
		/*
		 * String sql = "SELECT * From LEFT JOIN " +
		 * DBHelper.DATABASE_TABLE_timetable +" on "+
		 * DBHelper.DATABASE_TABLE_person+"."+DBHelper.KEY_ROWID +" = " +
		 * DBHelper.DATABASE_TABLE_timetable+"."+DBHelper.KEY_ROWID +
		 * " WHERE person._id = "+rowid;
		 */
		long rowid = person.getrowId();
		Cursor mCursor = mDb.query(true, DBHelper.DATABASE_TABLE_timetable,
				new String[] { DBHelper.KEY_ROWID, DBHelper.KEY_DAYNUMBER,
						DBHelper.KEY_TIMENUMBER, }, KEY_ROWID + "=" + rowid,
				null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	private  Cursor fetchPerson(String name) {
		return mDb.rawQuery("select * from " + DBHelper.DATABASE_TABLE_person + " where name = ?", new String[]{name});
	}
	
	private  Cursor fetchAllPerson() {
		return mDb.rawQuery("select * from " + DBHelper.DATABASE_TABLE_person, null);
	}
	
	public boolean contain(Person person) {
		Cursor cursor = mDb.rawQuery("select name from " + DBHelper.DATABASE_TABLE_person + " where name = ?", new String[]{person.getName()});
		return cursor.moveToFirst();
	}
}
