package fr.utbm.carpooling.view.widgets;

import java.text.DateFormat;
import java.util.Date;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.TripSearchResultShort;
import fr.utbm.carpooling.model.wrapper.TripSearch;
import fr.utbm.carpooling.utils.Resources;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
		pathView = (TripPathRestrictedView) findViewById(R.id.result_item_trippathview);
        departurePoint = (TextView) findViewById(R.id.result_item_textview_departure_point);
        departureTime = (TextView) findViewById(R.id.result_item_textview_departure_time);
        nbSeats = (TextView) findViewById(R.id.result_item_textview_nb_seats);
        arrivalPoint = (TextView) findViewById(R.id.result_item_textview_arrival_point);
        arrivalTime = (TextView) findViewById(R.id.result_item_textview_arrival_time);
        trunkSize = (TextView) findViewById(R.id.result_item_textview_trunk_size);
	}
	
	protected void initView() {
		if (pathView != null) {
			pathView.setCheckpoints(data.getCheckpoints(), data.getArrivalIsEnd());
		}
		
		if (departureTime != null) {
			departureTime.setText(TIME_FORMAT.format(data.getCheckpoints().get(0).getDate()));
		}
		
		if (nbSeats != null) {
			nbSeats.setText(data.getSeats() - data.getRemainingSeats() + "/" + data.getSeats());
		}
		
		if (arrivalTime != null) {
			arrivalTime.setText(TIME_FORMAT.format(data.getCheckpoints().get(0).getDate()));
		}
		
		if (trunkSize != null) {
			trunkSize.setText(Resources.getTrunk(data.getTrunkId()).getName());
		}
	}

	public int getAbstractTripId() {
		return data.getAbstractTripId();
	}

	public Date getTripId() {
		return data.getTripId();
	}

	public void setSearchInfo(TripSearch mSearch) {
		if (departurePoint != null) {
			departurePoint.setText(Resources.getSite(mSearch.getFromSiteId()).getName());
		}
		
		if (arrivalPoint != null) {
			arrivalPoint.setText(Resources.getSite(mSearch.getToSiteId()).getName());
		}
	}
}
