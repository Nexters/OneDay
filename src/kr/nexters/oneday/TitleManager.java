package kr.nexters.oneday;

import android.view.View;
import android.view.Window;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;


 public class TitleManager extends Activity {

        boolean mIsCheckbox; 
        TextView mTitletext; 
        static Context context;
		
	    
		// single ton 방식으로
		private static TitleManager TitleManager;  //1.private static변수 1개

		//2.생성자 아무데서나 안불려지게 private로 
		private TitleManager() {
		
		}

		//3.초기화
		public static TitleManager getTitleManager() {
			if (TitleManager == null) {
				TitleManager = new TitleManager();
			}
			return TitleManager;
		}
     
     
      //custom title bar setting
    
     public void customTitleBar(String title, Activity activity){
			activity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,  R.layout.sample12_titlebar);
			TextView tv_title = (TextView)activity.findViewById(R.id.sample12_tv_title);
			tv_title.setText(title);
			}
     
     
}