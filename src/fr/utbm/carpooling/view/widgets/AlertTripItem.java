package fr.utbm.carpooling.view.widgets;

import java.util.List;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.model.Alert;
import fr.utbm.carpooling.model.Checkpoint;

public class AlertTripItem extends TripItem<Alert> {

	public AlertTripItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void initView() {
		repeat.setVisibility(GONE);
		departureTime.setVisibility(INVISIBLE);
		
        Checkpoint from = (Checkpoint) data.getCheckpoints().get(0);
        Checkpoint to = (Checkpoint) data.getCheckpoints().get(1);
		
        if (pathView != null) {
            pathView.setCheckpoints(data.getCheckpoints());
        }
        
        if (departurePoint != null) {
            departurePoint.setText(Resources.getSiteShort(from.getSiteId()).getName());
        }
        
        if (departureDate != null) {
            if (DateUtils.isToday(from.getDate().getTime()))
                departureDate.setText(R.string.trips_caption_today);
            else
                departureDate.setText(DATE_FORMAT.format(from.getDate()));
        }
        
        if (arrivalPoint != null) {
            arrivalPoint.setText(Resources.getSiteShort(to.getSiteId()).getName());
        }
        
        if (arrivalTime != null) {
            arrivalTime.setText(TIME_FORMAT.format(to.getDate()));
        }
	}

}
