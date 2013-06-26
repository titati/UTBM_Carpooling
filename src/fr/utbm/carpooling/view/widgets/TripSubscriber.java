package fr.utbm.carpooling.view.widgets;

import android.widget.ImageButton;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.UserShort;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

public class TripSubscriber extends TableRow {
	
	protected UserShort mUser = null;
	
	protected TextView mName = null;
	protected ImageButton mPhone = null;
	protected ImageButton mText = null;
	protected ImageButton mMail = null;
	protected ImageButton mDelete = null;

	public TripSubscriber(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_trip_subscriber, this, true);
        
        setGravity(Gravity.CENTER_VERTICAL);
        
		initItems();
	}
	
	public void setData(UserShort user) {
		mUser = user;
		initView();
	}
	
	protected void initItems() {
		mName = (TextView) findViewById(R.id.trip_suscriber_textview_name);
        mPhone = (ImageButton) findViewById(R.id.trip_suscriber_button_call);
        mText = (ImageButton) findViewById(R.id.trip_suscriber_button_chat);
        mMail = (ImageButton) findViewById(R.id.trip_suscriber_button_email);
        mDelete = (ImageButton) findViewById(R.id.trip_suscriber_button_remove);
	}
	
	protected void initView() {
		mName.setText(mUser.getFirstname() + " " + mUser.getLastname());

		mPhone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + mUser.getPhone()));
				getContext().startActivity(callIntent);
			}
		});

		mText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent callIntent = new Intent(Intent.ACTION_VIEW);
				callIntent.setData(Uri.parse("sms:" + mUser.getPhone()));
				getContext().startActivity(callIntent);
			}
		});

		mMail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//TODO : call email intent
			}
		});

		mDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// todo: delete suscriber
				
			}
		});
	}
}
