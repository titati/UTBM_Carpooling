package fr.utbm.carpooling.view.widgets;

import java.text.DateFormat;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.model.Checkpoint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

public class TripCheckpoint extends TableRow {
	
	protected Checkpoint mCheckpoint = null;
	
	protected TextView mName = null;
	protected ImageView mCoords = null;
	protected TextView mTime = null;
	
	protected final DateFormat TIME_FORMAT = android.text.format.DateFormat.getTimeFormat(getContext());
	
	public TripCheckpoint(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_checkpoint_trip, this, true);
        
		initItems();
	}
	
	public void setCheckpoint(Checkpoint checkpoint) {
		mCheckpoint = checkpoint;
		initView();
	}
	
	protected void initItems() {
		mName = (TextView) getChildAt(0);
		mCoords = (ImageView) getChildAt(1);
		mTime = (TextView) getChildAt(2);
	}
	
	protected void initView() {
		if (mName != null) {
			mName.setText(Resources.getSiteShort(mCheckpoint.getSiteId()).getName());
		}
		
		if (mCoords != null) {
			mCoords.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//Resources.getSite(mCheckpoint.getSiteId()).getCoords();
				}
			});
		}
		
		if (mTime != null) {
			mTime.setText(TIME_FORMAT.format(mCheckpoint.getDate()));
		}
	}

}
