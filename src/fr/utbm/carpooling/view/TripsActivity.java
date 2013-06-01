package fr.utbm.carpooling.view;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import fr.utbm.carpooling.R;


public class TripsActivity extends FragmentActivity {

    TabsAdapter mTabsAdapter;
    ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayHomeAsUpEnabled(true);


        mViewPager = (ViewPager) findViewById(R.id.profile_pager);
        mTabsAdapter = new TabsAdapter(this, mViewPager);

        mViewPager.setAdapter(mTabsAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
        	@Override
        	public void onPageSelected(int position) {
        		actionBar.setSelectedNavigationItem(position);
        	}
        });

        mTabsAdapter.addTab(actionBar.newTab().setText(R.string.trips_driver_title), TripsDriverFragment.class, null);
        mTabsAdapter.addTab(actionBar.newTab().setText(R.string.trips_passenger_title), TripsPassengerFragment.class, null);
        mTabsAdapter.addTab(actionBar.newTab().setText(R.string.trips_alerts_title), TripsAlertsFragment.class, null);

        if (savedInstanceState != null) {
            actionBar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
    }
}