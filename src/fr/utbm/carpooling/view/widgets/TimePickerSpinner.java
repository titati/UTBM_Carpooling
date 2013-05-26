package fr.utbm.carpooling.view.widgets;

import java.text.DateFormat;
import java.util.Calendar;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import fr.utbm.carpooling.R;


public class TimePickerSpinner extends Button {

    private Calendar cal = (Calendar) Calendar.getInstance().clone();
    private TimePickerDialog dialog = null;


    public TimePickerSpinner(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.spinnerStyle);
        dialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hours, int minutes) {
                cal.set(Calendar.HOUR, hours);
                cal.set(Calendar.MINUTE, minutes);

                dialog.dismiss();
                updateTime();
            }
        }, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(getContext()));

        this.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        updateTime();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TimePickerSpinner, 0, 0);
        try {
            //dialog.setTitle(a.getString(R.styleable.TimePickerSpinner_prompt));
        } finally {
            a.recycle();
        }
    }


    public Calendar getTime() {
        return cal;
    }


    private void updateTime() {
        DateFormat df = android.text.format.DateFormat.getTimeFormat(getContext());
        this.setText(df.format(cal.getTime()));
    }

}