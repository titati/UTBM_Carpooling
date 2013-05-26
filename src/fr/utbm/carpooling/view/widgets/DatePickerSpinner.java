package fr.utbm.carpooling.view.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import fr.utbm.carpooling.R;

import java.text.DateFormat;
import java.util.Calendar;


public class DatePickerSpinner extends Button {

    private class DatePickerDialog extends android.app.DatePickerDialog {

        private String title = null;

        public DatePickerDialog(Context context, OnDateSetListener callBack, int year, int month, int day) {
            super(context, callBack, year, month, day);
        }

        @Override
        public void setTitle(CharSequence title) {
            super.setTitle(title);
            this.title = title.toString();
        }

        @Override
        public void onDateChanged(DatePicker view, int year, int month, int day) {
            updateTitle();
        }

        private void updateTitle() {
            if (title != null)
                super.setTitle(title);
        }
    }

    private Calendar cal = (Calendar) Calendar.getInstance().clone();
    private DatePickerDialog dialog = null;


    public DatePickerSpinner(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.spinnerStyle);
        dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, day);

                dialog.dismiss();
                updateDate();
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

        this.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        updateDate();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DatePickerSpinner, 0, 0);
        try {
            dialog.setTitle(a.getString(R.styleable.DatePickerSpinner_prompt));
        } finally {
            a.recycle();
        }

        dialog.getDatePicker().setCalendarViewShown(true);
        dialog.getDatePicker().setSpinnersShown(false);
    }


    public Calendar getDate() {
        return cal;
    }


    private void updateDate() {
        DateFormat df = android.text.format.DateFormat.getDateFormat(getContext());
        this.setText(df.format(cal.getTime()));
    }
}
