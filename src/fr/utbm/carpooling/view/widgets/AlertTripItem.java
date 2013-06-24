package fr.utbm.carpooling.view.widgets;

import java.text.DateFormat;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.Alert;
import fr.utbm.carpooling.utils.Resources;

public class AlertTripItem extends LinearLayout {
	
	protected Alert data = null;
	
	protected TripPathView pathView = null;
	protected TextView departurePoint = null;
	protected TextView departureDate = null;
	protected TextView departureTime = null;
	protected TextView arrivalPoint = null;
	protected TextView arrivalTime = null;
	protected ImageView repeat = null;
	
    protected final DateFormat TIME_FORMAT = android.text.format.DateFormat.getTimeFormat(getContext());
    protected final DateFormat DATE_FORMAT = android.text.format.DateFormat.getDateFormat(getContext());

    public AlertTripItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_trip_item, this, true);
        
		initItems();
	}
	
	public void setData(Alert mData) {
		data = mData;
		initView();
	}
	
	protected void initItems() {
		pathView = (TripPathView) findViewById(R.id.item_trippathview);
        departurePoint = (TextView) findViewById(R.id.item_textview_departure_point);
        departureTime = (TextView) findViewById(R.id.trip_item_textview_departure_time);
        departureDate = (TextView) findViewById(R.id.item_textview_departure_date);
        arrivalPoint = (TextView) findViewById(R.id.item_textview_arrival_point);
        arrivalTime = (TextView) findViewById(R.id.item_textview_arrival_time);
        repeat = (ImageView) findViewById(R.id.trip_item_imageview_repeat);
	}
	
	protected void initView() {
		repeat.setVisibility(GONE);
		departureTime.setVisibility(INVISIBLE);
		departureDate.setVisibility(INVISIBLE);
		
        if (pathView != null) {
            pathView.setCheckpoints(data.getCheckpoints());
        }
        
        if (departurePoint != null) {
            departurePoint.setText(Resources.getSite(data.getFromSiteId()).getName());
        }
        
        if (arrivalPoint != null) {
            arrivalPoint.setText(Resources.getSite(data.getToSiteId()).getName());
        }
        
        if (arrivalTime != null) {
            arrivalTime.setText(TIME_FORMAT.format(data.getArrivalDate()));
        }
	}

}
