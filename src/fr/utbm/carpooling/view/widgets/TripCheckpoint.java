package fr.utbm.carpooling.view.widgets;

import java.text.DateFormat;
import java.util.Locale;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.Checkpoint;
import fr.utbm.carpooling.utils.Resources;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

public class TripCheckpoint extends TableRow {
	
	protected Checkpoint mCheckpoint = null;
	
	protected TextView mName = null;
	protected TextView mTime = null;
	
	protected final DateFormat TIME_FORMAT = android.text.format.DateFormat.getTimeFormat(getContext());
	
	public TripCheckpoint(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_checkpoint_trip, this, true);
        
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(0, 10, 0, 10);

		initItems();
	}
	
	public void setCheckpoint(Checkpoint checkpoint) {
		mCheckpoint = checkpoint;
		initView();
	}
	
	protected void initItems() {
		mName = (TextView) findViewById(R.id.checkpoint_trip_textview_site);
		mTime = (TextView) findViewById(R.id.checkpoint_trip_textview_time);
	}
	
	protected void initView() {
		if (mName != null) {
			mName.setText(Resources.getSite(mCheckpoint.getSiteId()).getName());
            mName.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					float latitude = (float) Resources.getSite(mCheckpoint.getSiteId()).getLocation().getLatitude();
					float longitude = (float) Resources.getSite(mCheckpoint.getSiteId()).getLocation().getLongitude();
					String desc = Resources.getSite(mCheckpoint.getSiteId()).getName() + ", " + Resources.getSite(mCheckpoint.getSiteId()).getTown();
					String uri = String.format(Locale.ENGLISH, "geo:%f,%f?q=%f,%f (%s)", latitude, longitude, latitude, longitude, desc);
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
					getContext().startActivity(intent);
				}
			});
		}
		
		if (mTime != null) {
			mTime.setText(TIME_FORMAT.format(mCheckpoint.getDate()));
		}
	}

}