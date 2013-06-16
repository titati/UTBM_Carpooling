package fr.utbm.carpooling.view;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import fr.utbm.carpooling.R;


public class TripsActivity extends FragmentActivity {
	
	private ViewPager mViewPager;
	private TabsAdapter mTabsAdapter;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        ActionBar.Tab driverTab = actionBar.newTab().setText(R.string.trips_driver_title);
        ActionBar.Tab passengerTab = actionBar.newTab().setText(R.string.trips_passenger_title);
        ActionBar.Tab alertTab = actionBar.newTab().setText(R.string.trips_alerts_title);
        
        mViewPager = (ViewPager) findViewById(R.id.fragment_container);
        mTabsAdapter = new TabsAdapter(this, mViewPager);

        mViewPager.setAdapter(mTabsAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
        	@Override
        	public void onPageSelected(int position) {
        		actionBar.setSelectedNavigationItem(position);
        	}
        });
        
        mTabsAdapter.addTab(driverTab, TripsDriverFragment.class, null);
        mTabsAdapter.addTab(passengerTab, TripsPassengerFragment.class, null);
        mTabsAdapter.addTab(alertTab, TripsAlertsFragment.class, null);
		
        if (savedInstanceState != null) {
            actionBar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
    }
}