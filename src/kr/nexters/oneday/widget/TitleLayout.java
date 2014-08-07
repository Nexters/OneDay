package kr.nexters.oneday.widget;

import kr.nexters.oneday.R;
import kr.nexters.oneday.R.id;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;


public class TitleLayout {

	private Button buttonL;

	public TitleLayout(Window window) {
		buttonL = (Button) window.findViewById(R.id.btn);
	}

	public void setButtonL(OnClickListener listener) {
		buttonL.setVisibility(View.VISIBLE);
		buttonL.setOnClickListener(listener);
	}
	
	public void setTitle() {
	}
}