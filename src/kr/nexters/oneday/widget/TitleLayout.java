package kr.nexters.oneday.widget;

import kr.nexters.oneday.R;
import kr.nexters.oneday.R.id;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;


public class TitleLayout {

	private ImageButton buttonL;
	private ImageButton buttonR;

	public TitleLayout(Window window) {
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
	}
	
	public void setTitle() {
	}
}