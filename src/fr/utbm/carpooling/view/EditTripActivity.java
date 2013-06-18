package fr.utbm.carpooling.view;


import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.adapter.SiteShortAdapter;
import fr.utbm.carpooling.model.wrapper.SiteShort;
import fr.utbm.carpooling.view.widgets.EditCheckpointView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class EditTripActivity extends Activity {

    private List<ToggleButton> mWeekdayToggleButtons = new ArrayList<ToggleButton>();
    private List<EditCheckpointView> mCheckpointViews = new ArrayList<EditCheckpointView>();
    private List<Spinner> mSiteSpinners = new ArrayList<Spinner>();
    private LinearLayout mMainLayout;
    private ImageButton mAddButton;
    private ArrayList<SiteShort> mSitesShort = Resources.getSitesShort();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);

        Locale locale = getResources().getConfiguration().locale;
        int firstDayOfWeek = Calendar.getInstance(locale).getFirstDayOfWeek();

        LinearLayout ll = (LinearLayout) findViewById(R.id.edit_trip_llayout_weekdays);

        for (int i = 0; i < ll.getChildCount(); ++i) {
            mWeekdayToggleButtons.add((ToggleButton) ll.getChildAt(i));
        }


        if (firstDayOfWeek == Calendar.SATURDAY || firstDayOfWeek == Calendar.SUNDAY) {

            ToggleButton sunday = mWeekdayToggleButtons.get(6);
            ll.removeView(sunday);
            ll.addView(sunday, 0);

            if (firstDayOfWeek == Calendar.SATURDAY) {
                ToggleButton saturday = mWeekdayToggleButtons.get(5);
                ll.removeView(saturday);
                ll.addView(saturday, 0);
            }
        }

        for (ToggleButton btn : mWeekdayToggleButtons) {
            btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    buttonView.setTextColor(getResources().getColor(isChecked ? android.R.color.holo_blue_dark : android.R.color.secondary_text_light_nodisable));
                    buttonView.setTypeface(null, isChecked ? Typeface.BOLD : Typeface.NORMAL);
                }
            });
            btn.setChecked(false);
        }

        final LinearLayout repeatBlock = (LinearLayout) findViewById(R.id.edit_trip_llayout_repeat);

        CheckBox repeatSwitch = (CheckBox) findViewById(R.id.edit_trip_checkbox_repeat);
        repeatSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                repeatBlock.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });
        repeatSwitch.setChecked(false);

        final LinearLayout advancedBlock = (LinearLayout) findViewById(R.id.edit_trip_llayout_advanced);
        final LinearLayout moreBlock = (LinearLayout) findViewById(R.id.edit_trip_llayout_more);

        advancedBlock.setVisibility(View.GONE);


        Button moreButton = (Button) findViewById(R.id.edit_trip_button_more);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advancedBlock.setVisibility(View.VISIBLE);
                moreBlock.setVisibility(View.GONE);
            }
        });

        mMainLayout = (LinearLayout) findViewById(R.id.edit_trip_llayout_main);
        mAddButton = (ImageButton) findViewById(R.id.edit_trip_button_add);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTripActivity.this.insertCheckpointView();
            }
        });

        Spinner departureSiteSpinner = (Spinner) findViewById(R.id.edit_trip_spinner_departure_site);
        Spinner arrivalSiteSpinner = (Spinner) findViewById(R.id.edit_trip_spinner_arrival_site);

        mSiteSpinners.add(departureSiteSpinner);
        mSiteSpinners.add(arrivalSiteSpinner);

        for (Spinner sp : mSiteSpinners) {
            SiteShortAdapter adapter = new SiteShortAdapter(this, 0, mSitesShort);
            sp.setAdapter(adapter);
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    EditTripActivity.this.updateSiteSpinners();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }

        departureSiteSpinner.setSelection(0);
        arrivalSiteSpinner.setSelection(1);


    }

    private void insertCheckpointView() {

        final EditCheckpointView checkpointView = new EditCheckpointView(this);

        mCheckpointViews.add(checkpointView);
        mMainLayout.addView(checkpointView, mCheckpointViews.size());


        ImageButton delButton = checkpointView.getDelButton();

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTripActivity.this.removeCheckpointView(checkpointView);
            }
        });

        Spinner siteSpinner = checkpointView.getSiteSpinner();
        SiteShortAdapter adapter = new SiteShortAdapter(this, 0, mSitesShort);
        siteSpinner.setAdapter(adapter);



        mSiteSpinners.add(siteSpinner);

        siteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EditTripActivity.this.updateSiteSpinners();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        if (mSiteSpinners.size() == mSitesShort.size())
            mAddButton.setVisibility(View.INVISIBLE);

        List<Integer> selectedSites = new ArrayList<Integer>();
        for (int i = 0; i < mSitesShort.size(); ++i) {
            selectedSites.add(i);
        }

        for (Spinner sp : mSiteSpinners) {
            if (sp == siteSpinner)
                continue;

            selectedSites.remove((Integer) sp.getSelectedItemPosition());
        }

        siteSpinner.setSelection(selectedSites.get(0));

    }

    private void removeCheckpointView(EditCheckpointView checkpointView) {

        mMainLayout.removeView(checkpointView);
        mCheckpointViews.remove(checkpointView);
        mSiteSpinners.remove(checkpointView.getSiteSpinner());
        updateSiteSpinners();

        if (mAddButton.getVisibility() == View.INVISIBLE)
            mAddButton.setVisibility(View.VISIBLE);

    }

    private void updateSiteSpinners() {

        for (Spinner sp : mSiteSpinners) {
            ((SiteShortAdapter) sp.getAdapter()).enableItems();
        }

        for (Spinner sp : mSiteSpinners) {

            int selected = sp.getSelectedItemPosition();

            for (Spinner sp0 : mSiteSpinners) {
                if (sp == sp0)
                    continue;

                ((SiteShortAdapter) sp0.getAdapter()).disableItem(selected);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_trip, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit_trip_menuitem_cancel:
                this.setResult(0);
                finish();
                return true;

            case R.id.edit_trip_menuitem_save:
                //TODO: create the trip
                this.setResult(1);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
