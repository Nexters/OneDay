package kr.nexters.oneday.vo;

import kr.nexters.oneday.widget.TimeTableView.DAY;
import kr.nexters.oneday.widget.TimeTableView.TIME;

public class TimeInfo {
	private DAY day;
	private TIME time;

	public TimeInfo(DAY day, TIME time) {
		this.day = day;
		this.time = time;
	}

	public DAY getDay() {
		return day;
	}

	public void setDay(DAY day) {
		this.day = day;
	}

	public TIME getTime() {
		return time;
	}

	public void setTime(TIME time) {
		this.time = time;
	}
	
	
}
