package kr.nexters.oneday.database;

import java.util.ArrayList;
import java.util.List;

import kr.nexters.oneday.vo.Person;
import kr.nexters.oneday.vo.TimeInfo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

	private static final String DATABASE_CREATE =

	"create table person (_id integer primary key autoincrement, "

	+ "title text not null, body text not null);";


	private static final String DATABASE_TABLE = "person";

	private static final int DATABASE_VERSION = 1;

	private final Context mCtx;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

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

		return this;

	}

	public void close() {

		mDbHelper.close();

	}

	public long createPerson(String title, String body) {

		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_TITLE, title);

		initialValues.put(KEY_BODY, body);

		return mDb.insert(DATABASE_TABLE, null, initialValues);

	}

	public boolean deletePerson(long rowId) {

		Log.i("Delete called", "value__" + rowId);

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

		mDb.query(true, DATABASE_TABLE, new String[] { KEY_ROWID, KEY_TITLE,

		KEY_BODY }, KEY_ROWID + "=" + rowId, null, null, null, null,

		null);

		if (mCursor != null) {

			mCursor.moveToFirst();

		}

		return mCursor;

	}

	public boolean updatePerson(long rowId, String title, String body) {

		ContentValues args = new ContentValues();

		args.put(KEY_TITLE, title);

		args.put(KEY_BODY, body);

		return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;

	}

	// select * from timetable where user_id = 'UserId'
	@Override
	public List<TimeInfo> getUserTimeInfos(String userId) {
		// TODO Auto-generated method stub

		String sql = "select timetable, name" + "from person "
				+ "where name = " + userId;

		return null;

	}

	@Override
	public void addTimeInfo(long userId, TimeInfo timeInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTimeInfo(TimeInfo time) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Person> getPeople() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPerson(Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePerson(Person person) {
		// TODO Auto-generated method stub

	}
}
