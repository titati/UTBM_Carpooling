package fr.utbm.carpooling.view.widgets;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.Checkpoint;
import fr.utbm.carpooling.model.DriverTripOccurenceShort;
import fr.utbm.carpooling.utils.Resources;
import android.content.Context;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.View;

public class DriverTripItem extends TripItem<DriverTripOccurenceShort> {

	public DriverTripItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void initView() {
		Checkpoint from = (Checkpoint) data.getCheckpoints().get(0);
        Checkpoint to = (Checkpoint) data.getCheckpoints().get(1);

        if (pathView != null) {
        	pathView.setSurroundBounds(true);
            pathView.setCheckpoints(data.getCheckpoints());
        }
        
        if (departurePoint != null) {
            departurePoint.setText(Resources.getSite(from.getSiteId()).getName());
        }
        
        if (departureTime != null) {
            departureTime.setText(TIME_FORMAT.format(from.getDate()));
        }
        
        if (departureDate != null) {
            if (DateUtils.isToday(from.getDate().getTime()))
                departureDate.setText(R.string.trips_caption_today);
            else
                departureDate.setText(DATE_FORMAT.format(from.getDate()));
        }
        
        if (arrivalPoint != null) {
            arrivalPoint.setText(Resources.getSite(to.getSiteId()).getName());
        }
        
        if (arrivalTime != null) {
            arrivalTime.setText(TIME_FORMAT.format(to.getDate()));
        }
        
        if (repeat != null) {
            repeat.setVisibility(data.isRepeat() ? View.VISIBLE : View.GONE);
        }
	}

}
