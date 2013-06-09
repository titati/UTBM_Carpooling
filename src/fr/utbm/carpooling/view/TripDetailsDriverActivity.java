package fr.utbm.carpooling.view;

import java.util.Date;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.TaskHandler;
import fr.utbm.carpooling.model.DriverTripOccurence;
import fr.utbm.carpooling.view.widgets.LoadingDialog;
import fr.utbm.carpooling.view.widgets.TripDetailsDriverBlock;
import fr.utbm.carpooling.webservices.DriverWebServices;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class TripDetailsDriverActivity extends Activity {
	
    private TaskHandler<DriverTripOccurence> mHandler = null;
    private LoadingDialog mLoader = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trip_details_driver);
		
		initHandler();
        loadData(getIntent().getExtras().getString("abstractTripId"), (Date) getIntent().getExtras().get("tripId"));
	}
	
	private void loadData(String abstractTripId, Date tripId) {
        mLoader.show();
        DriverWebServices.getTripOccurence(abstractTripId, tripId, mHandler);
    }
    
    private void initHandler() {
		mLoader = new LoadingDialog(this);
		mHandler = new TaskHandler<DriverTripOccurence>() {
			
			@Override
			public void taskSuccessful(DriverTripOccurence object) {
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
    
    private void initView(DriverTripOccurence object) {
    	TripDetailsDriverBlock v = (TripDetailsDriverBlock) findViewById(R.id.trip_details_driver_view);
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
		getMenuInflater().inflate(R.menu.trip_details_driver, menu);
		return true;
	}

}
