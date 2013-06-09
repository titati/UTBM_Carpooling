package fr.utbm.carpooling.view.widgets;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.UserShort;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

public class TripSubscriber extends TableRow {
	
	protected UserShort mUser = null;
	
	protected TextView mName = null;
	protected ImageView mPhone = null;
	protected ImageView mText = null;
	protected ImageView mMail = null;
	protected ImageView mDelete = null;

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
		mName = (TextView) getChildAt(0);
        mPhone = (ImageView) getChildAt(1);
        mText = (ImageView) getChildAt(2);
        mMail = (ImageView) getChildAt(3);
        mDelete = (ImageView) getChildAt(4);
	}
	
	protected void initView() {
		mName.setText(mUser.getFirstname() + " " + mUser.getName());
		mPhone.setClickable(true);
		mPhone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});

		mText.setClickable(true);
		mText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mMail.setClickable(true);
		mMail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mDelete.setClickable(true);
		mDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}