package kr.nexters.oneday.widget;

import kr.nexters.oneday.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TimeTableView extends LinearLayout {

	public TimeTableView(Context context) {
		super(context);
		initialize();
	}

	public TimeTableView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}

	private void initialize() {
		setOrientation(LinearLayout.VERTICAL);
		
		LinearLayout linear = null;
		for (int i = 0; i < 90; i++) {
			if(i % 5 == 0) {
				linear = new LinearLayout(getContext());
				linear.setOrientation(LinearLayout.HORIZONTAL);
				addView(linear);
			}
			TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.time_sector, null);
			textView.setText("Aaa");
			linear.addView(textView);
		}

	}
}
