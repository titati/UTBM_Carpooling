package fr.utbm.carpooling.view;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.adapter.SiteShortAdapter;
import fr.utbm.carpooling.view.widgets.DatePickerSpinner;
import fr.utbm.carpooling.view.widgets.TimePickerSpinner;


public class TripSearchActivity extends Activity {

    private Spinner mDepartureSiteSpinner;
    private Spinner mArrivalSiteSpinner;
    private DatePickerSpinner mArrivalDateSpinner;
    private TimePickerSpinner mArrivalTimeSpinner;
    private Button mLessButton;
    private Button mMoreButton;

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

        mDepartureSiteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapterTo.enableItems();
                adapterTo.disableItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mArrivalSiteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapterFrom.enableItems();
                adapterFrom.disableItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mLessButton = (Button) findViewById(R.id.trip_search_button_less);
        mLessButton.setVisibility(View.GONE);
        mMoreButton = (Button) findViewById(R.id.trip_search_button_more);

        mLessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mLessButton.setVisibility(View.GONE);
                mMoreButton.setVisibility(View.VISIBLE);
            }
        });

        

        mMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMoreButton.setVisibility(View.GONE);
                mLessButton.setVisibility(View.VISIBLE);
            }
        });


    }
}
