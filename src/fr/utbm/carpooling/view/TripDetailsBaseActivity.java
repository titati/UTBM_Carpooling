package fr.utbm.carpooling.view;

import java.util.Date;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.BasePassengerTrip;
import fr.utbm.carpooling.model.PassengerTrip;
import fr.utbm.carpooling.utils.TaskHandler;
import fr.utbm.carpooling.view.widgets.LoadingDialog;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public abstract class TripDetailsBaseActivity<E extends BasePassengerTrip> extends Activity {

    protected TaskHandler<PassengerTrip> mHandler;
    protected LoadingDialog mLoader;
    protected E mData;
    protected Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoader = new LoadingDialog(this);
        initHandler();
        if (mData == null) loadData(getIntent().getExtras().getInt("abstractTripId"), (Date) getIntent().getExtras().get("tripId"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLoader.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
        mMenu = menu;
        return true;
    }
    
    protected void displayContentView() {
    	setContentView(R.layout.activity_trip_details_passenger);
    }

    protected abstract void loadData(int abstractTripId, Date tripId);

    protected abstract void initHandler();
    
    protected abstract void initView(E object);

}
