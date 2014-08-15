package kr.nexters.oneday.widget;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import kr.nexters.oneday.R;
import kr.nexters.oneday.database.DBAdapter;
import kr.nexters.oneday.util.ViewUtil;
import kr.nexters.oneday.vo.Person;
import kr.nexters.oneday.vo.TimeInfo;
//import kr.nexters.oneday.util.ViewUtil;
import android.content.Context;
import android.graphics.Color;
import android.provider.Telephony;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimeTableView extends LinearLayout {

	private final static int[] TIME_SECTOR_COLOR_RES = {
			R.color.time_cell_1,
			R.color.time_cell_2,
			R.color.time_cell_3,
			R.color.time_cell_4,
			R.color.time_cell_5};
	
	private List<TimeSectorHolder> ref = new ArrayList<TimeTableView.TimeSectorHolder>();; 
	private static final int MAX_CELL_CNT = 20; // 하루에 30분 단위로 쪼갤 때 셀의 갯수 
	
	private LinearLayout innerLayout;
	
	public enum DAY {
		MON(0), TUE(1), WED(2), THU(3), FRI(4);
		private int number;
		private DAY(int num) {
			this.number = num;
		}
	}
	
	/*
	 * _900 : 9:00 ~ 9:30
	 * _1830 : 18:30 ~ 19:00
	 * */
	public enum TIME {
		_900("09:00"), _930("09:30"), _1000("10:00"), _1030("10:30"),
		_1100("11:00"), _1130("11:30"), _1200("12:00"), _1230("12:30"),
		_1300("13:00"), _1330("13:30"), _1400("14:00"), _1430("14:30"),
		_1500("15:00"), _1530("15:30"), _1600("16:00"), _1630("16:30"),
		_1700("17:00"), _1730("17:30"), _1800("18:00"), _1830("18:30");
		private String time;
		private TIME(String time) {
			this.time = time;
		}
	}
	
	public TimeTableView(Context context) {
		super(context);
		initialize();
	}

	public TimeTableView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
		
		// 유저 정보랑 시간표정보는 이런식으로 가져온다.
//		DBAdapter dbAdapter = new PersonDBAdapter(getContext());
//		List<Person> userList = dbAdapter.getPeople();
//		List<TimeInfo> timeInfoList = dbAdapter.getUserTimeInfos("1");
	}

	private void initialize() {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		inflater.inflate(R.layout.timetable, this);
		
		innerLayout = (LinearLayout) findViewById(R.id.timetable_inner_layout);
		LinearLayout linear = null;
		for (int i = 0; i < MAX_CELL_CNT * 5; i++) {
			if(i % MAX_CELL_CNT == 0) {
				linear = new LinearLayout(getContext());
				linear.setOrientation(LinearLayout.VERTICAL);
				innerLayout.addView(linear);
				((LayoutParams) linear.getLayoutParams()).weight = 1;
			}
			TimeSectorHolder holder = new TimeSectorHolder(getContext(), R.layout.time_sector);
			holder.setInfo(i);
			ref.add(holder);
			linear.addView(holder.root);
			((LayoutParams) holder.root.getLayoutParams()).weight = 1;
		}
		
		findViewById(R.id.bg_bar_time).scrollBy(0, -(int) ViewUtil.dipToPx(5));
	}
	
	private void addCountSector(Person p, DAY day, TIME time) {
		TimeSectorHolder holder = getHolder(day, time);
		String currentCnt = holder.text.getText().toString();
		
		if(currentCnt.equals("")) {
			setSectorColor(day, time, 1);			
		} else {
			setSectorColor(day, time, Integer.parseInt(currentCnt) + 1);
		}
		
		holder.personSet.add(p);
	}
	
	/**
	 * @param colorNumber : 겹치는 숫자를 넣으면 됨 (1~5이상)
	 */
	private void setSectorColor(DAY day, TIME time, int colorNumber) {
		int number = colorNumber;
		if(colorNumber > 5) {
			number = 5;
		}
		
		TimeSectorHolder holder = getHolder(day, time);
		
		int color = (colorNumber > 0) ? getContext().getResources().getColor(TIME_SECTOR_COLOR_RES[number - 1]) : Color.TRANSPARENT;
		String text = (colorNumber > 0) ? String.valueOf(colorNumber) : "";
		
		holder.text.setBackgroundColor(color);
		holder.text.setText(String.valueOf(text));
	}
	
	private TimeSectorHolder getHolder(DAY day, TIME time) {
		TimeSectorHolder dummy = new TimeSectorHolder();
		dummy.day = day;
		dummy.time = time;
		
		int location = ref.indexOf(dummy);
		if(location == -1) {
			throw new IllegalArgumentException();
		}
		TimeSectorHolder holder = ref.get(location);
		return holder;
	}
	
	public void setSelectedMode(boolean isSelectedMode) {
		Iterator<TimeSectorHolder> it = ref.iterator();
		while(it.hasNext()) {
			TimeSectorHolder holder = it.next();
			holder.setSelectedMode(isSelectedMode);
		}
	}
	
	public List<TimeInfo> getAllSelectedTimeInfo() {
		List<TimeInfo> ret = new ArrayList<TimeInfo>();
		
		Iterator<TimeSectorHolder> it = ref.iterator();
		
		while(it.hasNext()) {
			TimeSectorHolder holder = it.next();
			if(holder.root.isSelected()) {
				ret.add(new TimeInfo(holder.day, holder.time));
			}
		}
		
		return ret;
	}
	
	public void addPerson(Person person) {
		for(TimeInfo info : person.getTimeList()) {
			addCountSector(person, info.getDay(), info.getTime());
		}
	}
	
	public void setPerson(Set<Person> personSelectedSet) {
		clearSector();
		
		Iterator<Person> it = personSelectedSet.iterator();
		while(it.hasNext()) {
			addPerson(it.next());
		}
	}
	
	public void clearSector() {
		for(TimeSectorHolder holder : ref) {
			holder.root.setSelected(false);
			holder.personSet.clear();
			setSectorColor(holder.day, holder.time, 0);
		}
	}
	
	private class TimeSectorHolder {
		private View root;
		private TextView text;
		private DAY day;
		private TIME time;
		
		private Set<Person> personSet = new HashSet<Person>();
		
		private TimeSectorHolder() { }
		
		private TimeSectorHolder(Context context, int resource) {
			root = LayoutInflater.from(context).inflate(R.layout.time_sector, null);
			text = (TextView) root.findViewById(R.id.timesector_text);
			
			root.setOnClickListener(selectorListener);
		}
		
		private void setSelectedMode(boolean isSelectedMode) {
			root.setSelected(false);
			if(isSelectedMode) {
				root.setOnClickListener(selectorListener);
			} else {
				root.setOnClickListener(popupListener);
			}
		}
		
		private void setInfo(final int position) {
			final int dayIndex = position / MAX_CELL_CNT;
			switch(dayIndex) {
			case 0: day = DAY.MON; break;
			case 1: day = DAY.TUE; break;
			case 2: day = DAY.WED; break;
			case 3: day = DAY.THU; break;
			case 4: day = DAY.FRI; break;
			}
			
			final int timeIndex = position - (dayIndex * MAX_CELL_CNT);
			switch(timeIndex) {
			case 0: time = TIME._900; break;
			case 1: time = TIME._930; break;
			case 2: time = TIME._1000; break;
			case 3: time = TIME._1030; break;
			case 4: time = TIME._1100; break;
			case 5: time = TIME._1130; break;
			case 6: time = TIME._1200; break;
			case 7: time = TIME._1230; break;
			case 8: time = TIME._1300; break;
			case 9: time = TIME._1330; break;
			case 10: time = TIME._1400; break;
			case 11: time = TIME._1430; break;
			case 12: time = TIME._1500; break;
			case 13: time = TIME._1530; break;
			case 14: time = TIME._1600; break;
			case 15: time = TIME._1630; break;
			case 16: time = TIME._1700; break;
			case 17: time = TIME._1730; break;
			case 18: time = TIME._1800; break;
			case 19: time = TIME._1830; break;
			}
		}
		
		@Override
		public boolean equals(Object o) {
			if(!(o instanceof TimeSectorHolder)) {
				return false;
			}
			TimeSectorHolder other = (TimeSectorHolder) o;
			if(time == other.time && day == other.day) {
				return true;
			}
			return false;
		}
		
		private OnClickListener selectorListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				root.setSelected(!root.isSelected());
			}
		};
		
		private OnClickListener popupListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(personSet.size() > 0) {
					TelDialog dialog = new TelDialog(TimeTableView.this.getContext(), personSet);
					dialog.show();
				}
			}
		};
	}
}
