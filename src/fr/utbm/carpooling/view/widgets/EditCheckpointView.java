package fr.utbm.carpooling.view.widgets;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import fr.utbm.carpooling.R;

public class EditCheckpointView extends LinearLayout {

    private Spinner mSiteSpinner = null;
    private ImageButton mDelButton = null;
    private DatePickerSpinner mDateSpinner = null;
    private TimePickerSpinner mTimeSpinner = null;

    public EditCheckpointView(Context context) {
        super(context);
        init();
    }

    public EditCheckpointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_edit_checkpoint, this, true);

        mSiteSpinner = (Spinner) findViewById(R.id.edit_trip_spinner_checkpoint_site);
        mDelButton = (ImageButton) findViewById(R.id.edit_trip_button_del);
        mDateSpinner = (DatePickerSpinner) findViewById(R.id.edit_trip_spinner_checkpoint_date);
        mTimeSpinner = (TimePickerSpinner) findViewById(R.id.edit_trip_spinner_checkpoint_time);

    }


    public ImageButton getDelButton() {
        return mDelButton;
    }

    public Spinner getSiteSpinner() {
        return mSiteSpinner;
    }

    public DatePickerSpinner getDateSpinner() {
        return mDateSpinner;
    }

    public TimePickerSpinner getTimeSpinner() {
        return mTimeSpinner;
    }



}