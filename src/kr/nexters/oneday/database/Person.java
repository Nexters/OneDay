package kr.nexters.oneday.database;

import java.util.List;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.EditText;

public class Person {

	private String name;
	private int phoneNumber;
	private List<Integer> timeTable;
	//private DBHandler dbhandler;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<Integer> getTimeList() {
		return timeTable;
	}
	public void setTimeList(List<Integer> timeTable) {
		this.timeTable = timeTable;
	}
	
	
}
	
	


