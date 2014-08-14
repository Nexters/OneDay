package kr.nexters.oneday.widget;

import java.util.Iterator;
import java.util.Set;

import kr.nexters.oneday.R;
import kr.nexters.oneday.vo.Person;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TelDialog extends Dialog {
	
	private Set<Person> personSet;
	
	public TelDialog(Context context, Set<Person> personSet) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		setContentView(R.layout.tel_dialog);
		
		this.personSet = personSet;
		
		initialize();
	}
	
	private void initialize() {
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.person_list);
		LayoutInflater inflater = LayoutInflater.from(getContext());
		
		Iterator<Person> it = personSet.iterator();
		while(it.hasNext()) {
			Person p = it.next();
			
			View view = inflater.inflate(R.layout.tel_dialog_list_item, null);
			linearLayout.addView(view);
			Holder holder = new Holder(view);
			holder.load(p);
		}
	}
	
	private class Holder implements android.view.View.OnClickListener {
		private View root;
		private TextView nameTv;
		private TextView phoneNumberTv;
		
		private Holder(View root) {
			this.root = root;
			nameTv = (TextView) root.findViewById(R.id.tel_name);
			phoneNumberTv = (TextView) root.findViewById(R.id.tel_number);
		}
		
		private void load(Person p) {
			nameTv.setText(p.getName());
			
			String phoneNumber = p.getPhoneNumber();
			if(phoneNumber != null) {
				phoneNumberTv.setText(PhoneNumberUtils.formatNumber(phoneNumber));
			}
			
			if(!p.getName().equals("나")) {
				root.setOnClickListener(this);
			}
		}

		@Override
		public void onClick(View v) {
			String phoneNumber = phoneNumberTv.getText().toString();
			if(phoneNumber == null || phoneNumber.equals("")) {
				return;
			}
			Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
			
			getContext().startActivity(intent);
		}
	}
}
