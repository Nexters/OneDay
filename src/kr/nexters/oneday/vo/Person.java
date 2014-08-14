package kr.nexters.oneday.vo;

import java.util.List;

public class Person {
	private String name;
	private String phoneNumber;

	private List<TimeInfo> timeList;
	
	public Person() {
		
	}
	
	public Person(String name, String phoneNumber, List<TimeInfo> timeList) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.timeList = timeList;
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
}

