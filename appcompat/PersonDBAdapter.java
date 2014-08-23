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
<<<<<<< HEAD

public class PersonDBAdapter implements DBAdapter {

	public static final String KEY_TITLE = "title";
	public static final String KEY_BODY = "body";
	public static final String KEY_ROWID = "_id";

	private static final String TAG = "PersonDbAdapter";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	/**
	 * 
	 * Database creation sql statement
	 */
=======
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

    /**
>>>>>>> ac0196cd302c95baefbe49b05e35081064cbe35f

	private static final String DATABASE_CREATE =

	"create table person (_id integer primary key autoincrement, "

<<<<<<< HEAD
	+ "title text not null, body text not null);";


	private static final String DATABASE_TABLE = "person";

	private static final int DATABASE_VERSION = 1;

	private final Context mCtx;
=======
    private static final String DATABASE_NAME = "data";
>>>>>>> ac0196cd302c95baefbe49b05e35081064cbe35f

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

<<<<<<< HEAD
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "

			+ newVersion + ", which will destroy all old data");

			db.execSQL("DROP TABLE IF EXISTS person");

			onCreate(db);

		}

	}

	public PersonDBAdapter(Context ctx) {

		this.mCtx = ctx;

	}

	public PersonDBAdapter open() throws SQLException {

		mDbHelper = new DatabaseHelper(mCtx);

		mDb = mDbHelper.getWritableDatabase();
=======
    private static final String DATABASE_CREATE =
            "create table person (_id integer primary key autoincrement, "
              + "title text not null, body text not null);";
   
    private final Context mCtx;

    public PersonDBAdapter(Context ctx) {

        this.mCtx = ctx;
        this.mDbHelper = new DBHelper(mCtx);
        this.mDb = mDbHelper.getWritableDatabase();
        
    }   

    public PersonDBAdapter open() throws SQLException {
    	    
        return this;

    }

   

    public void close() {

        mDbHelper.close();

    }

   

    public long addPerson(Person person) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(DBHelper.KEY_NAME, person.getName());
        initialValues.put(DBHelper.KEY_GROUPID, "0");
        initialValues.put(DBHelper.KEY_PHONENUMBER, person.getPhoneNumber());
        
        return mDb.insert(DBHelper.DATABASE_TABLE_person, null, initialValues);
>>>>>>> ac0196cd302c95baefbe49b05e35081064cbe35f

		return this;

	}

<<<<<<< HEAD
	public void close() {
=======
    public boolean deletePerson(Person person) {
>>>>>>> ac0196cd302c95baefbe49b05e35081064cbe35f

		mDbHelper.close();

<<<<<<< HEAD
	}

	public long createPerson(String title, String body) {
=======
        Log.i("Delete called", "value__" + person.getrowId() + ", " +person.getName());

        return mDb.delete(DBHelper.DATABASE_TABLE_person, KEY_ROWID + "=" + person.getrowId(), null) > 0;
>>>>>>> ac0196cd302c95baefbe49b05e35081064cbe35f

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_TITLE, title);

		initialValues.put(KEY_BODY, body);

<<<<<<< HEAD
		return mDb.insert(DATABASE_TABLE, null, initialValues);

	}

	public boolean deletePerson(long rowId) {
=======
        return mDb.query(DBHelper.DATABASE_TABLE_person, new String[] {
        		DBHelper.KEY_ROWID,
        		DBHelper.KEY_NAME,
        		DBHelper.KEY_GROUPID,
        		DBHelper.KEY_PHONENUMBER }, null, null, null, null, null);
>>>>>>> ac0196cd302c95baefbe49b05e35081064cbe35f

		Log.i("Delete called", "value__" + rowId);

<<<<<<< HEAD
		return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;

	}

	public Cursor fetchAllPerson() {

		return mDb.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_TITLE,

		KEY_BODY }, null, null, null, null, null);

	}

	private Cursor fetchAllUsers() {
		return mDb.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_TITLE,

		KEY_BODY }, null, null, null, null, null);
	}

	public Cursor fetchPerson(long rowId) throws SQLException {

		Cursor mCursor =
=======

    public Cursor fetchPerson(Person person) throws SQLException {
    	long rowId = person.getrowId();
        Cursor mCursor =
        		  mDb.query(true, DBHelper.DATABASE_TABLE_person, new String[] { 
        				  DBHelper.KEY_ROWID,
        				  DBHelper.KEY_NAME,
        				  DBHelper.KEY_GROUPID,
        				  DBHelper.KEY_PHONENUMBER
        		  }, KEY_ROWID + "=" + rowId, null, null, null, null,null);
        if (mCursor != null) {
>>>>>>> ac0196cd302c95baefbe49b05e35081064cbe35f

		mDb.query(true, DATABASE_TABLE, new String[] { KEY_ROWID, KEY_TITLE,

<<<<<<< HEAD
		KEY_BODY }, KEY_ROWID + "=" + rowId, null, null, null, null,

		null);

		if (mCursor != null) {

			mCursor.moveToFirst();
=======
        }
        return mCursor;
    }
>>>>>>> ac0196cd302c95baefbe49b05e35081064cbe35f

		}

<<<<<<< HEAD
		return mCursor;

	}

	public boolean updatePerson(long rowId, String title, String body) {

		ContentValues args = new ContentValues();

		args.put(KEY_TITLE, title);

		args.put(KEY_BODY, body);

		return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;

	}

	// select * from timetable where user_id = 'UserId'
=======
    public boolean updatePerson(Person person) {
    	
        ContentValues cvalue = new ContentValues();
        cvalue.put(DBHelper.KEY_NAME, person.getName());
        cvalue.put(DBHelper.KEY_GROUPID, "null");
        cvalue.put(DBHelper.KEY_PHONENUMBER, person.getPhoneNumber());
        return mDb.update(DBHelper.DATABASE_TABLE_person, cvalue, 
        				  KEY_ROWID + "=" + person.getrowId(), null) > 0;

    }
   
   

  /*  // select * from timetable where user_id = 'UserId'
>>>>>>> ac0196cd302c95baefbe49b05e35081064cbe35f
	@Override
	public List<TimeInfo> getUserTimeInfos(String userId) {
		// TODO Auto-generated method stub

		String sql = "select timetable, name" + "from person "
				+ "where name = " + userId;

		return null;
<<<<<<< HEAD

	}

	@Override
	public void addTimeInfo(long userId, TimeInfo timeInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTimeInfo(TimeInfo time) {
		// TODO Auto-generated method stub

	}
=======
	}*/
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
>>>>>>> ac0196cd302c95baefbe49b05e35081064cbe35f

          mCursor.moveToFirst();

<<<<<<< HEAD
	@Override
	public void addPerson(Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePerson(Person person) {
		// TODO Auto-generated method stub

=======
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
>>>>>>> ac0196cd302c95baefbe49b05e35081064cbe35f
	}

	
	
}
