package fr.utbm.carpooling.view.widgets;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import fr.utbm.carpooling.R;
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
		
        Checkpoint from = data.getCheckpoints().get(0);
        Checkpoint to = data.getCheckpoints().get(data.getCheckpoints().size() - 1);
		
        if (pathView != null) {
            pathView.setCheckpoints(data.getCheckpoints());
        }
        
        if (departurePoint != null) {
            departurePoint.setText(from.getSite().getName());
        }
        
        if (departureDate != null) {
            if (DateUtils.isToday(from.getDate().getTime()))
                departureDate.setText(R.string.trips_caption_today);
            else
                departureDate.setText(DATE_FORMAT.format(from.getDate()));
        }
        
        if (arrivalPoint != null) {
            arrivalPoint.setText(to.getSite().getName());
        }
        
        if (arrivalTime != null) {
            arrivalTime.setText(TIME_FORMAT.format(to.getDate()));
        }
	}

}
