package kr.nexters.oneday.widget;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kr.nexters.oneday.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TimeTableView extends LinearLayout {

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
	}

	private void initialize() {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		inflater.inflate(R.layout.timetable, this);
		
		innerLayout = (LinearLayout) findViewById(R.id.timetable_inner_layout);
//		setOrientation(LinearLayout.HORIZONTAL);
		
		LinearLayout linear = null;
		for (int i = 0; i < MAX_CELL_CNT * 5; i++) {
			if(i % MAX_CELL_CNT == 0) {
				linear = new LinearLayout(getContext());
				linear.setOrientation(LinearLayout.VERTICAL);
				innerLayout.addView(linear);
			}
			TimeSectorHolder holder = new TimeSectorHolder(getContext(), R.layout.time_sector);
			holder.setInfo(i);
			ref.add(holder);
			linear.addView(holder.root);
		}
	}
	
	public void setText(DAY day, TIME time, String text) {
		TimeSectorHolder dummy = new TimeSectorHolder();
		dummy.day = day;
		dummy.time = time;
		
		int location = ref.indexOf(dummy);
		if(location == -1) {
			throw new IllegalArgumentException();
		}
		TimeSectorHolder holder = ref.get(location);
		holder.text.setText(text);
	}
	
	public void setSelectedMode(boolean isSelectedMode) {
		Iterator<TimeSectorHolder> it = ref.iterator();
		while(it.hasNext()) {
			TimeSectorHolder holder = it.next();
			holder.setSelectedMode(isSelectedMode);
		}
	}
	
	private class TimeSectorHolder implements OnClickListener {
		private View root;
		private TextView text;
		private DAY day;
		private TIME time;
		
		private TimeSectorHolder() {
			
		}
		
		private TimeSectorHolder(Context context, int resource) {
			root = LayoutInflater.from(context).inflate(R.layout.time_sector, null);
			text = (TextView) root.findViewById(R.id.timesector_text);
			
			root.setOnClickListener(this);
			text.setText("zzzzzd ");
		}
		
		private void setSelectedMode(boolean isSelectedMode) {
			root.setSelected(false);
			if(isSelectedMode) {
				root.setOnClickListener(this);
			} else {
				root.setOnClickListener(null);
			}
		}
		
		private void setInfo(final int position) {
			final int dayIndex = position / MAX_CELL_CNT;
			switch(dayIndex) {
			case 0:
				day = DAY.MON;
				break;
			case 1:
				day = DAY.TUE;
				break;
			case 2:
				day = DAY.WED;
				break;
			case 3:
				day = DAY.THU;
				break;
			case 4:
				day = DAY.FRI;
				break;
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
		
		@Override
		public void onClick(View v) {
			root.setSelected(!root.isSelected());
		}
	}
}
