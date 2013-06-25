package fr.utbm.carpooling.view;

import java.util.Date;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.BasePassengerTrip;
import fr.utbm.carpooling.model.PassengerTrip;
import fr.utbm.carpooling.utils.TaskHandler;
import fr.utbm.carpooling.view.widgets.TripDetailsPassengerBlock;
import fr.utbm.carpooling.webservices.PassengerWebServices;
import android.view.View;
import android.widget.Toast;

public class TripDetailsPassengerActivity extends TripDetailsBaseActivity<PassengerTrip> {
	
	@Override
	protected void loadData(int abstractTripId, Date tripId) {
        mLoader.show();
        PassengerWebServices.getPassengerTrip(abstractTripId, tripId, mHandler);
    }

	@Override
    protected void initHandler() {
        mHandler = new TaskHandler<PassengerTrip>() {

            @Override
            public void taskSuccessful(PassengerTrip object) {
                displayContentView();
                getMenuInflater().inflate(R.menu.trip_details_passenger, mMenu);
                initView(object);
                mData = object;
                mLoader.dismiss();
            }

            @Override
            public void taskFailed() {
                finish();
                Toast.makeText(getApplicationContext(), R.string.error_fetching_data, Toast.LENGTH_LONG).show();
                mLoader.dismiss();
            }
        };
    }

	@Override
    protected void initView(PassengerTrip object) {
		TripDetailsPassengerBlock v = (TripDetailsPassengerBlock) findViewById(R.id.trip_details_driver_view);
		v.setData((BasePassengerTrip) object);
		if (object.isFeedbackGiven()) v.setFeedbackVisibility(View.GONE);
		else v.setFeedbackVisibility(View.VISIBLE);
	}

}
