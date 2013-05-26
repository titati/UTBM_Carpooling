package fr.utbm.carpooling.view.widgets;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.User;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

public class TripSubscriber extends TableRow {
	
	protected User mUser = null;
	
	protected TextView mName = null;
	protected ImageView mPhone = null;
	protected ImageView mText = null;
	protected ImageView mMail = null;
	protected ImageView mDelete = null;

	public TripSubscriber(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_trip_details, this, true);
        
		initItems();
	}
	
	public void setData(User user) {
		mUser = user;
		initView();
	}
	
	protected void initItems() {
		mName = (TextView) getChildAt(0);
        mPhone = (ImageView) ((ViewGroup) ((ViewGroup) getChildAt(0)).getChildAt(1)).getChildAt(1);
        mText = (ImageView) ((ViewGroup) getChildAt(0)).getChildAt(2);
        mMail = (ImageView) ((ViewGroup) getChildAt(1)).getChildAt(1);
        mDelete = (ImageView) ((ViewGroup) getChildAt(1)).getChildAt(1);
	}
	
	protected void initView() {
		
	}
}
