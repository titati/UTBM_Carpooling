package fr.utbm.carpooling.view.widgets;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.User;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class TripDriverBlock extends LinearLayout {

    protected User mDriver = null;

    protected TextView mName = null;
    protected ImageView mPhone = null;
    protected ImageView mText = null;
    protected ImageView mEMail = null;
    protected RatingBar mRating = null;
    protected Button mFeedback = null;


    public TripDriverBlock(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_trip_driver_block, this, true);

        setOrientation(VERTICAL);
        initItems();
    }

    public void setData(User driver) {
        mDriver = driver;
        initView();
    }

    protected void initItems() {
        mName = (TextView) findViewById(R.id.trip_driver_block_name);
        mPhone = (ImageView) findViewById(R.id.trip_driver_block_button_call);
        mText = (ImageView) findViewById(R.id.trip_driver_block_button_chat);
        mEMail = (ImageView) findViewById(R.id.trip_driver_block_button_email);
        mRating = (RatingBar) findViewById(R.id.trip_driver_block_rating);
        mFeedback = (Button) findViewById(R.id.trip_driver_block_button_feedback);
    }

    protected void initView() {

        mName.setText(mDriver.getFirstname() + " " + mDriver.getLastname());
        mRating.setMax(5);
        mRating.setStepSize((float) 0.5);
        mPhone.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        });

        mText.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        });

        mEMail.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        });

        mFeedback.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Dialog d = new TripFeedbackDialog(getContext());
                d.show();
            }
        });
    }

    protected void setFeedbackVisibility(int v) {
        mFeedback.setVisibility(v);
    }
}
