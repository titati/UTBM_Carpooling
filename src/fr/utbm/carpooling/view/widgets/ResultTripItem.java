package fr.utbm.carpooling.view.widgets;

import java.text.DateFormat;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.model.TripSearchResultShort;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultTripItem extends LinearLayout {
	
	protected TripSearchResultShort data = null;
	
	protected TripPathRestrictedView pathView = null;
	protected TextView departurePoint = null;
	protected TextView departureTime = null;
	protected TextView arrivalPoint = null;
	protected TextView arrivalTime = null;
	protected TextView nbSeats = null;
	protected TextView trunkSize = null;
			
    protected final DateFormat TIME_FORMAT = android.text.format.DateFormat.getTimeFormat(getContext());
	
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
			pathView.setCheckpoints(data.getCheckpoints(), data.getArrivalIsEnd());
		}
		
		if (departurePoint != null) {
			departurePoint.setText(Resources.getSiteShort(0).getName());
		}
		
		if (departureTime != null) {
			departureTime.setText(TIME_FORMAT.format(data.getCheckpoints().get(0).getDate()));
		}
		
		if (nbSeats != null) {
			nbSeats.setText(data.getRemainingSeats() + "/" + data.getSeats());
		}
		
		if (arrivalPoint != null) {
			arrivalPoint.setText(Resources.getSiteShort(0).getName());
		}
		
		if (arrivalTime != null) {
			arrivalTime.setText(TIME_FORMAT.format(data.getCheckpoints().get(0).getDate()));
		}
		
		if (trunkSize != null) {
			trunkSize.setText(data.getTrunk().getName());
		}
	}
}
