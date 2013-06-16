package fr.utbm.carpooling.view;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import fr.utbm.carpooling.R;

public class ProfileActivity extends FragmentActivity {

    TabsAdapter mTabsAdapter;
    ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.profile_pager);
        mTabsAdapter = new TabsAdapter(this, mViewPager);

        mViewPager.setAdapter(mTabsAdapter);

        mTabsAdapter.addTab(actionBar.newTab().setText(R.string.profile_info_title), ProfileInfoFragment.class, null);
        mTabsAdapter.addTab(actionBar.newTab().setText(R.string.profile_cars_title), ProfileCarsFragment.class, null);
        mTabsAdapter.addTab(actionBar.newTab().setText(R.string.profile_stats_title), ProfileStatsFragment.class, null);

        if (savedInstanceState != null) {
            actionBar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
        }
    }


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
    }
}
