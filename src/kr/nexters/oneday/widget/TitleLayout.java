package kr.nexters.oneday.widget;

import kr.nexters.oneday.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class TitleLayout {
	
	public static final int BUTTON_TOGGLE_RES 	= R.drawable.title_bar_drawerlayer_selector;
	public static final int BUTTON_CHECK_RES 	= R.drawable.title_bar_check_selector;
	public static final int BUTTON_EXPORT_RES 	= R.drawable.title_bar_export_selector;
	public static final int BUTTON_EXIT_RES 	= R.drawable.title_bar_exit_selector;
	public static final int BUTTON_NAVI_RES 	= R.drawable.title_bar_navi_selector;

	private ImageView titleimage;
	private ImageButton buttonL;
	private ImageButton buttonR;
	private TextView title;
	private Drawable drawable;
	private Context context;
	

	public TitleLayout(Window window) {
		title = (TextView)window.findViewById(R.id.title);
//		titleimage  = (ImageView) window.findViewById(R.id.header_icon);
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
		 Drawable transparent = new ColorDrawable(Color.TRANSPARENT);
		 title.setText(text);
		 title.setBackgroundDrawable(transparent);
		 //title.setVisibility(View.VISIBLE);
		 /*
		 if(text != null ){
			 title.setVisibility(View.INVISIBLE);
		 }
		 */
	}
	
	public void setImageDrawable(Context context){
		Drawable titleLogo =  context.getResources().getDrawable( R.drawable.icon_logo );
		title.setText("");
		title.setBackgroundDrawable(titleLogo);
		//this.drawable = drawable;
		//titleimage.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_logo));
		
	}


}
