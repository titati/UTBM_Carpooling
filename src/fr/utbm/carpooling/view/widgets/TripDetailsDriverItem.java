package fr.utbm.carpooling.view.widgets;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import fr.utbm.carpooling.model.Checkpoint;
import fr.utbm.carpooling.model.DriverTripOccurence;

public class TripDetailsDriverItem extends TripDetailItem<DriverTripOccurence> {
	
	protected ArrayList<TripSubscriber> mListSubscribers = null;
	
	public TripDetailsDriverItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void finishBuildView() {
		for(int i = 0; i < mData.getCheckpoints().size(); ++i) {
			TripCheckpoint t = new TripCheckpoint(getContext(), null);
			t.setCheckpoint((Checkpoint) mData.getCheckpoints().get(i));
			mListCheckpoint.add(t);
			mRootCheckpoint.addView(t, i);
		}
		
		addView(null, 2);
	}

	@Override
	protected void initSecondItems() {
		
	}

	@Override
	protected void initView() {
		
	}

}
