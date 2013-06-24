package fr.utbm.carpooling.view;

import java.util.Date;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.PassengerTrip;
import fr.utbm.carpooling.utils.TaskHandler;
import fr.utbm.carpooling.view.widgets.LoadingDialog;
import fr.utbm.carpooling.view.widgets.TripDetailsPassengerBlock;
import fr.utbm.carpooling.webservices.PassengerWebServices;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class TripDetailsPassengerActivity extends Activity {
	
    private TaskHandler<PassengerTrip> mHandler = null;
    private LoadingDialog mLoader = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trip_details_passenger);
		
		initHandler();
        loadData(getIntent().getExtras().getInt("abstractTripId"), (Date) getIntent().getExtras().get("tripId"));
	}
	
	private void loadData(int abstractTripId, Date tripId) {
        mLoader.show();
        PassengerWebServices.getPassengerTrip(abstractTripId, tripId, mHandler);
    }
    
    private void initHandler() {
		mLoader = new LoadingDialog(this);
		mHandler = new TaskHandler<PassengerTrip>() {
			
			@Override
			public void taskSuccessful(PassengerTrip object) {
				initView(object);
		        
		        mLoader.hide();
			}
			
			@Override
			public void taskFailed() {
				Toast.makeText(getApplicationContext(), "Error while fetching content", Toast.LENGTH_LONG).show();
		        mLoader.hide();
			}
		};
	}
    
    private void initView(PassengerTrip object) {
		TripDetailsPassengerBlock v = (TripDetailsPassengerBlock) findViewById(R.id.trip_details_driver_view);
		v.setData(object);
	}
    
    @Override
    protected void onPause() {
    	super.onPause();
    	mLoader.dismiss();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trip_details_passenger, menu);
		return true;
	}

}
