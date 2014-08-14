package kr.nexters.oneday.widget;

import kr.nexters.oneday.R;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;


public class TitleLayout {
	
	public static final int BUTTON_TOGGLE_RES 	= R.drawable.title_bar_drawerlayer_selector;
	public static final int BUTTON_CHECK_RES 	= R.drawable.title_bar_check_selector;
	public static final int BUTTON_EXPORT_RES 	= R.drawable.title_bar_export_selector;
	public static final int BUTTON_EXIT_RES 	= R.drawable.title_bar_exit_selector;
	
	private TextView title;
	private ImageButton buttonL;
	private ImageButton buttonR;

	public TitleLayout(Window window) {
		title   = (TextView) window.findViewById(R.id.header_text);
		buttonL = (ImageButton) window.findViewById(R.id.imageButtonL);
		buttonR = (ImageButton) window.findViewById(R.id.imageButtonR);
	}

	public void setButtonL(OnClickListener listener, int resid) {
		buttonL.setVisibility(View.VISIBLE);
		buttonL.setOnClickListener(listener);
		buttonL.setBackgroundResource(resid);
	}
	
	public void setButtonR(OnClickListener listener, int resid) {
		buttonR.setVisibility(View.VISIBLE);
		buttonR.setOnClickListener(listener);
		buttonR.setBackgroundResource(resid);
	}
	
	public void setTitle(String text) {
		title.setText(text);
	}
}