package fr.utbm.carpooling.view.widgets;

import java.util.ArrayList;

import fr.utbm.carpooling.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public abstract class TripDetailItem<E> extends LinearLayout {
	
	protected E mData = null;
	
	protected TextView mSeats = null;
	protected TextView mDate = null;
	protected TripPathView mTripPath = null;
	protected TextView mCarName = null;
	protected ColorCheckBox mColor = null;
	protected TextView mTrunkSize = null;
	protected TextView mDesc = null;
	protected TableLayout mRootCheckpoint = null;
	protected ArrayList<TripCheckpoint> mListCheckpoint = null;
	
	public TripDetailItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_trip_details, this, true);
        
		initItems();
		finishBuildView();
		initSecondItems();
	}
	
	public void setData(E data) {
		mData = data;
		initView();
	}
	
	protected void initItems() {
		mTripPath = (TripPathRestrictedView) getChildAt(0);
        mSeats = (TextView) ((ViewGroup) ((ViewGroup) getChildAt(0)).getChildAt(1)).getChildAt(1);
        mDate = (TextView) ((ViewGroup) getChildAt(0)).getChildAt(2);
        mRootCheckpoint = (TableLayout) ((ViewGroup) getChildAt(1)).getChildAt(1);
	}
	
	protected void initSecondItems() {
		mCarName = (TextView) ((ViewGroup) getChildAt(3)).getChildAt(1);
        mColor = (ColorCheckBox) ((ViewGroup) getChildAt(3)).getChildAt(2);
        mTrunkSize = (TextView) ((ViewGroup) getChildAt(3)).getChildAt(4);
        mDesc = (TextView) getChildAt(5);
	}
	
	protected abstract void finishBuildView();
	
	protected abstract void initView();

}
