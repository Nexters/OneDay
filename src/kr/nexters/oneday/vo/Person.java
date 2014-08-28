package kr.nexters.oneday.vo;

import java.util.Comparator;
import java.util.List;

public class Person implements Comparable<Person> {
	long rowid;
	private String name;
	public boolean selected;
	private String phoneNumber;

	private List<TimeInfo> timeList;
	
	public Person() {
		this.selected = true;
	}
	
	public Person(String name, String phoneNumber, List<TimeInfo> timeList, boolean selected) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.timeList = timeList;
		this.selected = selected;
		
	}
	public long getrowId(){
		return this.rowid;
	}
	public void setId(long id){
		this.rowid = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<TimeInfo> getTimeList() {
		return timeList;
	}

	public void setTimeList(List<TimeInfo> timeList) {
		this.timeList = timeList;
	}
	
	public boolean getSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "Person [rowid=" + rowid + ", name=" + name + ", selected="
				+ selected + ", phoneNumber=" + phoneNumber + ", timeList="
				+ timeList + "]";
	}

	@Override
	public int compareTo(Person another) {
		return name.compareTo(another.name);
	}
	
}

