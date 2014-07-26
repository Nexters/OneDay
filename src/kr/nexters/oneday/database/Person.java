package kr.nexters.oneday.database;

import java.util.List;

public class Person {
	private String name;
	private int phoneNumber;
	private List<Integer> timeList;
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
		return timeList;
	}
	public void setTimeList(List<Integer> timeList) {
		this.timeList = timeList;
	}
}
