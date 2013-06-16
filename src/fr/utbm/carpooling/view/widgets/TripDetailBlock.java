package fr.utbm.carpooling.view.widgets;

import java.text.DateFormat;
import java.util.ArrayList;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.BaseTrip;
import fr.utbm.carpooling.model.Checkpoint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public abstract class TripDetailBlock<E extends BaseTrip> extends LinearLayout {
	
	protected E mData = null;
	
	protected TextView mSeats = null;
	protected TextView mDate = null;
	protected TripPathView mTripPath = null;
	protected TextView mCarName = null;
	protected ColorCheckBox mColor = null;
	protected TextView mTrunkSize = null;
	protected TextView mDesc = null;
	protected TableLayout mRootCheckpoint = null;
	protected ArrayList<TripCheckpoint> mListCheckpoint = new ArrayList<TripCheckpoint>();

    protected final DateFormat TIME_FORMAT = android.text.format.DateFormat.getTimeFormat(getContext());
    protected final DateFormat DATE_FORMAT = android.text.format.DateFormat.getDateFormat(getContext());
	
	public TripDetailBlock(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_trip_details, this, true);
        
        setOrientation(VERTICAL);
        
		initItems();
	}
	
	public void setData(E data) {
		mData = data;
		finishBuildView();
		initSecondItems();
		initView();
	}
	
	protected void initItems() {
		mTripPath = (TripPathView) findViewById(R.id.trip_details_trippathview);
        mSeats = (TextView) findViewById(R.id.trip_details_textview_seats);
        mDate = (TextView) findViewById(R.id.trip_details_textview_date);
        mRootCheckpoint = (TableLayout) findViewById(R.id.trip_details_tlayout_checkpoints);

	}
	
	protected void initSecondItems() {
		mCarName = (TextView) findViewById(R.id.trip_details_textview_car);
        mColor = (ColorCheckBox) findViewById(R.id.trip_details_checkbox_color);
        mTrunkSize = (TextView) findViewById(R.id.trip_details_textview_trunk);
        mDesc = (TextView) findViewById(R.id.trip_details_textview_description);

		if (mRootCheckpoint != null) {
			for(int i = 0; i < mData.getCheckpoints().size(); ++i) {
				TripCheckpoint t = new TripCheckpoint(getContext(), null);
				t.setCheckpoint((Checkpoint) mData.getCheckpoints().get(i));
				mListCheckpoint.add(t);
				mRootCheckpoint.addView(t, i);
			}
		}
	}
	
	protected abstract void finishBuildView();
	
	protected abstract void initView();

}
