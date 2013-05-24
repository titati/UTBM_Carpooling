package fr.utbm.carpooling.view.widgets;

import java.text.DateFormat;

import fr.utbm.carpooling.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
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
		pathView = (TripPathView) getChildAt(0);
        departurePoint = (TextView) ((ViewGroup) ((ViewGroup) getChildAt(1)).getChildAt(0)).getChildAt(0);
        departureTime = (TextView) ((ViewGroup) ((ViewGroup) getChildAt(1)).getChildAt(0)).getChildAt(1);
        departureDate = (TextView) ((ViewGroup) ((ViewGroup) getChildAt(1)).getChildAt(0)).getChildAt(2);
        arrivalPoint = (TextView) ((ViewGroup) ((ViewGroup) getChildAt(1)).getChildAt(1)).getChildAt(0);
        arrivalTime = (TextView) ((ViewGroup) ((ViewGroup) getChildAt(1)).getChildAt(1)).getChildAt(1);
        repeat = (ImageView) ((ViewGroup) ((ViewGroup) getChildAt(1)).getChildAt(1)).getChildAt(2);
	}
	
	protected abstract void initView();
}
