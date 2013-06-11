package fr.utbm.carpooling.view.widgets;

import java.text.DateFormat;

import fr.utbm.carpooling.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class TripItem<E> extends LinearLayout {
	
	protected E data = null;
	
	protected TripPathView pathView = null;
	protected TextView departurePoint = null;
	protected TextView departureDate = null;
	protected TextView departureTime = null;
	protected TextView arrivalPoint = null;
	protected TextView arrivalTime = null;
	protected ImageView repeat = null;
	
    protected final DateFormat TIME_FORMAT = android.text.format.DateFormat.getTimeFormat(getContext());
    protected final DateFormat DATE_FORMAT = android.text.format.DateFormat.getDateFormat(getContext());
	
	public TripItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_trip_item, this, true);
        
		initItems();
	}
	
	public void setData(E mData) {
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
	
	protected abstract void initView();
}
