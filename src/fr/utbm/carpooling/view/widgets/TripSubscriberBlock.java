package fr.utbm.carpooling.view.widgets;

import java.util.ArrayList;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.UserShort;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class TripSubscriberBlock extends LinearLayout {
	
	protected ArrayList<UserShort> mSubscribers = null;
	protected int mNbSeats = 0;
	
	protected TextView mSeats = null;
	protected TableLayout mTable = null;
	
	public TripSubscriberBlock(Context context) {
		super(context);
		
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_trip_subscriber_block, this, true);
		
		setOrientation(VERTICAL);
		initItems();
	}
	
	public void setData(ArrayList<UserShort> users, int nbSeats) {
		mSubscribers = users;
		mNbSeats = nbSeats;
		
		initView();
	}
	
	protected void initItems() {
		mSeats = (TextView) ((ViewGroup) getChildAt(0)).getChildAt(2);
        mTable = (TableLayout) getChildAt(1);
	}
	
	protected void initView() {
		mSeats.setText(mSubscribers.size() + "/" + mNbSeats);
        
        for(int i = 0; i < mSubscribers.size(); ++i) {
        	TripSubscriber t = new TripSubscriber(getContext(), null);
        	t.setData(mSubscribers.get(i));
        	mTable.addView(t, i);
        }
	}
}
