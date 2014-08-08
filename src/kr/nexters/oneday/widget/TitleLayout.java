package kr.nexters.oneday.widget;

import kr.nexters.oneday.R;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;


public class TitleLayout {

	private TextView title;
	private ImageButton buttonL;
	private ImageButton buttonR;

	public TitleLayout(Window window) {
		title   = (TextView) window.findViewById(R.id.header_text);
		buttonL = (ImageButton) window.findViewById(R.id.imageButton1);
		buttonR = (ImageButton) window.findViewById(R.id.imageButton2);
	}

	public void setButtonL(OnClickListener listener) {
		buttonL.setVisibility(View.VISIBLE);
		buttonL.setOnClickListener(listener);
	}
	
	public void setButtonR(OnClickListener listener) {
		buttonR.setVisibility(View.VISIBLE);
		buttonR.setOnClickListener(listener);
		buttonR.setBackgroundResource(R.drawable.title_bar_export_selector);
	}
	
	public void setButtonR2(OnClickListener listener) {
		buttonR.setVisibility(View.VISIBLE);
		buttonR.setOnClickListener(listener);
		buttonR.setBackgroundResource(R.drawable.title_bar_check_selector);
	}
	
	public void setTitle(String text) {
		title.setText(text);
	}
}