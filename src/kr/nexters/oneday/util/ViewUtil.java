package kr.nexters.oneday.util;

import kr.nexters.oneday.Common;
import android.util.TypedValue;

public class ViewUtil {
	public static float dipToPx(float dip) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, Common.getMainContext().getResources().getDisplayMetrics());
	}
}
