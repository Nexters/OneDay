package kr.nexters.oneday.util;

import kr.nexters.oneday.Common;
import kr.nexters.oneday.MainActivity;
import kr.nexters.oneday.R;
import kr.nexters.oneday.vo.Person;
import android.util.TypedValue;
import android.view.View;

public class ViewUtil {
	public static float dipToPx(float dip) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, Common.getMainContext().getResources().getDisplayMetrics());
	}
	
	public static void setClickPersonItem(View v, Person p) {
		if(p != null) {
			if(p.selected) {
				v.setBackgroundResource(R.color.transparent);
				Common.removeSelectedPerson(p);
			} else {
				v.setBackgroundResource(R.drawable.bg_list_p);
				Common.addSelectedPerson(p);
			}
			p.selected = !p.selected;
			Common.dbAdapter.updatePerson(p);
			((MainActivity)MainActivity.context).checkTable();
		}
	}
}
