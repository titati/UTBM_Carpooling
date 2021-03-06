package fr.utbm.carpooling.view.widgets;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.UserShort;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class TripDriverBlock extends LinearLayout {

    protected UserShort mDriver = null;

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

    public void setData(UserShort driver, float rating) {
        mDriver = driver;
        mRating.setRating(rating);
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
        
        mPhone.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + mDriver.getPhone()));
				getContext().startActivity(callIntent);
            }
        });

        mText.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
				Intent callIntent = new Intent(Intent.ACTION_VIEW);
				callIntent.setData(Uri.parse("sms:" + mDriver.getPhone()));
				getContext().startActivity(callIntent);
            }
        });

        mEMail.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO : call email intent

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
