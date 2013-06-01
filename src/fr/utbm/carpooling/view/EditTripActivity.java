package fr.utbm.carpooling.view;


import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import fr.utbm.carpooling.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class EditTripActivity extends Activity {

    private List<ToggleButton> mWeekdayToggleButtons = new ArrayList<ToggleButton>();
    private List<TableRow> mAdvancedOptions = new ArrayList<TableRow>();
    private List<TableRow> mRepeatOptions = new ArrayList<TableRow>();

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
                ll.removeView(sunday);
                ll.addView(sunday, 0);
            }
        }

        for (ToggleButton btn : mWeekdayToggleButtons) {
            btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    buttonView.setTextColor(getResources().getColor(isChecked ? android.R.color.holo_blue_light : android.R.color.secondary_text_light_nodisable));
                    buttonView.setTypeface(null, isChecked ? Typeface.BOLD : Typeface.NORMAL);
                }
            });
            btn.setChecked(false);
        }

        mRepeatOptions.add((TableRow) findViewById(R.id.edit_trip_row_weekdays));
        mRepeatOptions.add((TableRow) findViewById(R.id.edit_trip_row_periodicity));
        mRepeatOptions.add((TableRow) findViewById(R.id.edit_trip_row_repeat_end));

        Switch repeatSwitch = (Switch) findViewById(R.id.edit_trip_switch_repeat);
        repeatSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for(TableRow r: mRepeatOptions) {
                    r.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                }
            }
        });
        repeatSwitch.setChecked(false);


        mAdvancedOptions.add((TableRow) findViewById(R.id.edit_trip_separator1));
        mAdvancedOptions.add((TableRow) findViewById(R.id.edit_trip_row_repeat));
        mAdvancedOptions.add((TableRow) findViewById(R.id.edit_trip_separator2));
        mAdvancedOptions.add((TableRow) findViewById(R.id.edit_trip_row_car));
        mAdvancedOptions.add((TableRow) findViewById(R.id.edit_trip_row_trunk));
        mAdvancedOptions.add((TableRow) findViewById(R.id.edit_trip_row_seats));
        mAdvancedOptions.add((TableRow) findViewById(R.id.edit_trip_separator3));
        mAdvancedOptions.add((TableRow) findViewById(R.id.edit_trip_row_description));

        for (TableRow r: mAdvancedOptions) {
            r.setVisibility(View.GONE);
        }

        Button moreButton = (Button) findViewById(R.id.edit_trip_button_more);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (TableRow r: mAdvancedOptions) {
                    r.setVisibility(View.VISIBLE);
                }
                v.setVisibility(View.GONE);
                findViewById(R.id.edit_trip_separator_more).setVisibility(View.GONE);
            }
        });

    }

}
