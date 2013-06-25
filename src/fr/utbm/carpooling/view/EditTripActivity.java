package fr.utbm.carpooling.view;


import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.adapter.CarAdapter;
import fr.utbm.carpooling.adapter.SiteShortAdapter;
import fr.utbm.carpooling.adapter.TrunkAdapter;
import fr.utbm.carpooling.model.Checkpoint;
import fr.utbm.carpooling.model.DriverCar;
import fr.utbm.carpooling.model.DriverTrip;
import fr.utbm.carpooling.model.Repeat;
import fr.utbm.carpooling.model.wrapper.SiteShort;
import fr.utbm.carpooling.model.wrapper.Trunk;
import fr.utbm.carpooling.utils.Resources;
import fr.utbm.carpooling.utils.TaskHandler;
import fr.utbm.carpooling.view.widgets.DatePickerSpinner;
import fr.utbm.carpooling.view.widgets.EditCheckpointView;
import fr.utbm.carpooling.view.widgets.LoadingDialog;
import fr.utbm.carpooling.view.widgets.TimePickerSpinner;
import fr.utbm.carpooling.webservices.DriverWebServices;

import java.util.*;

public class EditTripActivity extends Activity {

    private LinearLayout mMainLayout;
    private ImageButton mAddButton;
    private ArrayList<SiteShort> mSitesShort = Resources.getSitesShort();

    // Path stuff
    private List<EditCheckpointView> mCheckpointViews = new ArrayList<EditCheckpointView>();
    private List<Spinner> mSiteSpinners = new ArrayList<Spinner>();

    // Repeat stuff
    private CheckBox mRepeatCheckBox;
    private List<ToggleButton> mWeekdayToggleButtons = new ArrayList<ToggleButton>();
    private EditText mPeriodicityEditText;
    private DatePickerSpinner mRepeatEndSpinner;

    private EditText mDescriptionEditText;

    // Car stuff
    private Spinner mCarsSpinner;
    private Spinner mTrunksSpinner;
    private EditText mSeatsEditText;


    private TaskHandler<Boolean> mHandler = null;
    private LoadingDialog mLoader = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initHandler();
    }

    private void initView() {

        mLoader = new LoadingDialog(this);

        setContentView(R.layout.activity_edit_trip);

        // Replaces the first day of week according to the locale
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

        // Weekdays togglebuttons onClick callbacks
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

        // Display the repeat block according to the repeat checkbox state
        final LinearLayout repeatBlock = (LinearLayout) findViewById(R.id.edit_trip_llayout_repeat);

        mRepeatCheckBox = (CheckBox) findViewById(R.id.edit_trip_checkbox_repeat);
        mRepeatCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                repeatBlock.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });
        mRepeatCheckBox.setChecked(false);

        final LinearLayout advancedBlock = (LinearLayout) findViewById(R.id.edit_trip_llayout_advanced);
        final LinearLayout moreBlock = (LinearLayout) findViewById(R.id.edit_trip_llayout_more);

        advancedBlock.setVisibility(View.GONE);

        // Displays advanced block when "more" is clicked
        Button moreButton = (Button) findViewById(R.id.edit_trip_button_more);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advancedBlock.setVisibility(View.VISIBLE);
                moreBlock.setVisibility(View.GONE);
            }
        });


        // insert checkpoints when add button is clicked
        mMainLayout = (LinearLayout) findViewById(R.id.edit_trip_llayout_main);
        mAddButton = (ImageButton) findViewById(R.id.edit_trip_button_add);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTripActivity.this.insertCheckpointView();
            }
        });


        // Prevent user from choosing the same site twice in the checkpoints
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


        mPeriodicityEditText = (EditText) findViewById(R.id.edit_trip_edittext_periodicity);
        mRepeatEndSpinner = (DatePickerSpinner) findViewById(R.id.edit_trip_spinner_repeat_end);

        mDescriptionEditText = (EditText) findViewById(R.id.edit_trip_edittext_description);

        mCarsSpinner = (Spinner) findViewById(R.id.edit_trip_spinner_car);
        mTrunksSpinner = (Spinner) findViewById(R.id.edit_trip_spinner_trunk);
        mSeatsEditText = (EditText) findViewById(R.id.edit_trip_edittext_seats);


        mCarsSpinner.setAdapter(new CarAdapter(this, 0, Resources.getCars()));



        mTrunksSpinner.setAdapter(new TrunkAdapter(this, 0, Resources.getTrunks()));

        mCarsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DriverCar car = (DriverCar) mCarsSpinner.getItemAtPosition(position);
                mSeatsEditText.setText(String.valueOf(car.getSeats()));
                mTrunksSpinner.setSelection(car.getTrunkId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // initialize cars spinner with the default car (a better options should exist to do this...)
        int i = 0;
        for (DriverCar car : Resources.getCars()) {
            if (car.isDefaultCar()) {
                mCarsSpinner.setSelection(i);
                mSeatsEditText.setText(String.valueOf(car.getSeats()));
                mTrunksSpinner.setSelection(car.getTrunkId());
                break;
            }
            i++;
        }

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
                finish();
                return true;

            case R.id.edit_trip_menuitem_save:
                if (checkData()) {

                    mLoader.show();
                    DriverWebServices.createTrip(createTripFromData(), mHandler);

                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean checkData() {
        boolean ok = true;
        // Todo: Check checkpoints

        // Check repeat
        if (mRepeatCheckBox.isChecked()) {
        // Todo: check if at least one weekday is checked
        if (mPeriodicityEditText.getText().toString().isEmpty()) {
            mPeriodicityEditText.setError(getString(R.string.edit_trip_error_periodicity));
            ok = false;
        }
        // Todo: check if repeat end date if after today
        }

        // Check car
        if (mSeatsEditText.getText().toString().isEmpty()) {
            mSeatsEditText.setError(getString(R.string.edit_trip_error_seats));
            ok = false;
        }

        return ok;
    }

    private DriverTrip createTripFromData() {
        DriverTrip trip = new DriverTrip();

        // Create path
        ArrayList<Checkpoint> checkpoints = new ArrayList<Checkpoint>();

        // Create departure checkpoint
        Checkpoint departure = new Checkpoint();
        departure.setSiteId(((SiteShort) ((Spinner) findViewById(R.id.edit_trip_spinner_departure_site)).getSelectedItem()).getId());
        long date = ((DatePickerSpinner) findViewById(R.id.edit_trip_spinner_departure_date)).getDate().getTime().getTime();
        long time = ((TimePickerSpinner) findViewById(R.id.edit_trip_spinner_departure_time)).getTime().getTime().getTime();
        departure.setDate(new Date(date + time));
        departure.setNumCheckpoint(1);
        checkpoints.add(departure);

        // Create checkpoints
        int i = 2;
        for (EditCheckpointView v : mCheckpointViews) {
            Checkpoint checkpoint = new Checkpoint();
            checkpoint.setSiteId(((SiteShort) v.getSiteSpinner().getSelectedItem()).getId());
            date = v.getDateSpinner().getDate().getTime().getTime();
            time = v.getTimeSpinner().getTime().getTime().getTime();
            checkpoint.setDate(new Date(date + time));
            checkpoint.setNumCheckpoint(i);
            checkpoints.add(checkpoint);
            i++;
        }

        // Create arrival checkpoint
        Checkpoint arrival = new Checkpoint();
        arrival.setSiteId(((SiteShort) ((Spinner) findViewById(R.id.edit_trip_spinner_arrival_site)).getSelectedItem()).getId());
        date = ((DatePickerSpinner) findViewById(R.id.edit_trip_spinner_arrival_date)).getDate().getTime().getTime();
        time = ((TimePickerSpinner) findViewById(R.id.edit_trip_spinner_arrival_time)).getTime().getTime().getTime();
        arrival.setDate(new Date(date + time));
        arrival.setNumCheckpoint(i);
        checkpoints.add(arrival);

        trip.setCheckpoints(checkpoints);

        // Create repeat
        if (mRepeatCheckBox.isChecked()) {
            Repeat repeat = new Repeat();
            repeat.setPeriodicity(Integer.parseInt(mPeriodicityEditText.getText().toString()));
            repeat.setEndRepeat(mRepeatEndSpinner.getDate().getTime());

            repeat.setMonday(mWeekdayToggleButtons.get(0).isChecked());
            repeat.setTuesday(mWeekdayToggleButtons.get(1).isChecked());
            repeat.setWednesday(mWeekdayToggleButtons.get(2).isChecked());
            repeat.setThursday(mWeekdayToggleButtons.get(3).isChecked());
            repeat.setFriday(mWeekdayToggleButtons.get(4).isChecked());
            repeat.setSaturday(mWeekdayToggleButtons.get(5).isChecked());
            repeat.setSunday(mWeekdayToggleButtons.get(6).isChecked());

            trip.setRepeat(repeat);
        } else {
            trip.setRepeat(null);
        }

        trip.setDescription(mDescriptionEditText.getText().toString());

        // Create car
        DriverCar car = (DriverCar) mCarsSpinner.getSelectedItem();
        car.setSeats(Integer.parseInt(mSeatsEditText.getText().toString()));
        car.setTrunkId(((Trunk) mTrunksSpinner.getSelectedItem()).getId());

        return trip;
    }

    @Override
    protected void onPause() {
        super.onPause();

        mLoader.dismiss();
        finish();
    }

    private void initHandler() {
        mHandler = new TaskHandler<Boolean>() {

            @Override
            public void taskSuccessful(Boolean object) {
                if (object) {
                    setResult(1);
                    finish();
                } else {
                    taskFailed();
                }
            }

            @Override
            public void taskFailed() {
                mLoader.hide();
                Toast.makeText(getApplicationContext(), "Error while updating data", Toast.LENGTH_LONG).show();
            }

        };
    }

}
