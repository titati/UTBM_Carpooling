package fr.utbm.carpooling.view;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.adapter.SiteShortAdapter;
import fr.utbm.carpooling.adapter.TrunkAdapter;
import fr.utbm.carpooling.model.wrapper.SiteShort;
import fr.utbm.carpooling.model.wrapper.TripSearch;
import fr.utbm.carpooling.model.wrapper.Trunk;
import fr.utbm.carpooling.utils.Resources;
import fr.utbm.carpooling.view.widgets.DatePickerSpinner;
import fr.utbm.carpooling.view.widgets.TimePickerSpinner;


public class TripSearchActivity extends Activity {

    private Spinner mDepartureSiteSpinner;
    private Spinner mArrivalSiteSpinner;
    private Spinner mTrunksSpinner;
    private DatePickerSpinner mArrivalDateSpinner;
    private TimePickerSpinner mArrivalTimeSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_search);

        mDepartureSiteSpinner = (Spinner) findViewById(R.id.trip_search_spinner_departure_site);
        mArrivalSiteSpinner = (Spinner) findViewById(R.id.trip_search_spinner_arrival_site);
        mArrivalDateSpinner = (DatePickerSpinner) findViewById(R.id.trip_search_spinner_arrival_date);
        mArrivalTimeSpinner = (TimePickerSpinner) findViewById(R.id.trip_search_spinner_arrival_time);


        final SiteShortAdapter adapterFrom = new SiteShortAdapter(this, 0, Resources.getSitesShort());
        mDepartureSiteSpinner.setAdapter(adapterFrom);
        mDepartureSiteSpinner.setSelection(0);

        final SiteShortAdapter adapterTo = new SiteShortAdapter(this, 0, Resources.getSitesShort());
        mArrivalSiteSpinner.setAdapter(adapterTo);
        mArrivalSiteSpinner.setSelection(1);

//        mDepartureSiteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                adapterTo.enableItems();
//                adapterTo.disableItem(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//
//        mArrivalSiteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                adapterFrom.enableItems();
//                adapterFrom.disableItem(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });

        final LinearLayout advancedBlock = (LinearLayout) findViewById(R.id.trip_search_llayout_advanced);
        final LinearLayout moreBlock = (LinearLayout) findViewById(R.id.trip_search_llayout_more);

        advancedBlock.setVisibility(View.GONE);

        Button moreButton = (Button) findViewById(R.id.trip_search_button_more);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advancedBlock.setVisibility(View.VISIBLE);
                moreBlock.setVisibility(View.GONE);
            }
        });

        mTrunksSpinner = (Spinner) findViewById(R.id.trip_search_spinner_trunk);

        mTrunksSpinner.setAdapter(new TrunkAdapter(this, 0, Resources.getTrunks()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.trip_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.trip_search_menuitem_go:
            	Intent intent = new Intent(TripSearchActivity.this, TripSearchResultsActivity.class);
            	TripSearch search = new TripSearch();
            	Date date = new Date();
            	date.setTime(mArrivalDateSpinner.getDate().getTimeInMillis() + mArrivalTimeSpinner.getTime().getTimeInMillis());
            	search.setArrivalDate(date);
            	search.setFromSiteId(((SiteShort) mDepartureSiteSpinner.getSelectedItem()).getId());
            	search.setToSiteId(((SiteShort) mArrivalSiteSpinner.getSelectedItem()).getId());
            	search.setMinTrunkId(((Trunk) mTrunksSpinner.getSelectedItem()).getId());
				intent.putExtra("search", search);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
