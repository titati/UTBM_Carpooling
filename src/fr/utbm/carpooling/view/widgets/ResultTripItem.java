package fr.utbm.carpooling.view.widgets;

import java.text.DateFormat;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.TripSearchResultShort;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class ResultTripItem extends LinearLayout {
	
	protected TripSearchResultShort data = null;
	
	protected TripPathRestrictedView pathView = null;
	protected TextView departurePoint = null;
	protected TextView departureTime = null;
	protected TextView arrivalPoint = null;
	protected TextView arrivalTime = null;
	protected TextView nbSeats = null;
	protected TextView trunkSize = null;
			
    protected final DateFormat TIME_FORMAT = android.text.format.DateFormat.getTimeFormat(getContext());
    protected final DateFormat DATE_FORMAT = android.text.format.DateFormat.getDateFormat(getContext());
	
	public ResultTripItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_result_trip_item, this, true);
        
		initItems();
	}
	
	public void setData(TripSearchResultShort mData) {
		data = mData;
		initView();
	}
	
	protected void initItems() {
		pathView = (TripPathRestrictedView) getChildAt(0);
        departurePoint = (TextView) ((ViewGroup) ((ViewGroup) getChildAt(1)).getChildAt(0)).getChildAt(0);
        departureTime = (TextView) ((ViewGroup) ((ViewGroup) getChildAt(1)).getChildAt(0)).getChildAt(1);
        nbSeats = (TextView) ((ViewGroup) ((ViewGroup) getChildAt(1)).getChildAt(1)).getChildAt(3);
        arrivalPoint = (TextView) ((ViewGroup) ((ViewGroup) getChildAt(1)).getChildAt(1)).getChildAt(0);
        arrivalTime = (TextView) ((ViewGroup) ((ViewGroup) getChildAt(1)).getChildAt(1)).getChildAt(1);
        trunkSize = (TextView) ((ViewGroup) ((ViewGroup) getChildAt(1)).getChildAt(1)).getChildAt(3);
	}
	
	protected void initView() {
		if (pathView != null) {
			//pathView.setLimits(null, null);
			//pathView.setCheckpoints(null);
		}
		
		if (departurePoint != null) {
			departurePoint.setText("");
		}
		
		if (departureTime != null) {
			departureTime.setText("");
		}
		
		if (nbSeats != null) {
			nbSeats.setText(data.getRemainingSeats() + "/" + data.getSeats());
		}
		
		if (arrivalPoint != null) {
			arrivalPoint.setText("");
		}
		
		if (arrivalTime != null) {
			arrivalTime.setText("");
		}
		
		if (trunkSize != null) {
			trunkSize.setText(data.getTrunk().getName());
		}
	}
}
