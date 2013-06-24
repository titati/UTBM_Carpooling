package fr.utbm.carpooling.view.widgets;

import android.content.Context;
import android.util.AttributeSet;
import fr.utbm.carpooling.model.PassengerTrip;
import fr.utbm.carpooling.utils.Resources;

public class TripDetailsPassengerBlock extends TripDetailBlock<PassengerTrip> {
	
	protected TripDriverBlock mDriver = null;
	
	public TripDetailsPassengerBlock(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void finishBuildView() {
		mDriver = new TripDriverBlock(getContext());
		mDriver.setData(mData.getDriver());
		addView(mDriver, 2);
	}

	@Override
	protected void initView() {
		if (mTripPath != null) {
			mTripPath.setCheckpoints(mData.getCheckpoints());
		}
		
		if (mSeats != null) {
            int totalSeats = mData.getCar().getSeats();
            int usedSeats = totalSeats - mData.getRemainingSeats();
			mSeats.setText(usedSeats + "/" + totalSeats);
		}
		
		if (mDate != null) {
			mDate.setText(DATE_FORMAT.format(mData.getCheckpoints().get(0).getDate()));
		}
		
		if (mCarName != null) {
			mCarName.setText(Resources.getBrand(mData.getCar().getBrandId()).getName() + " " + Resources.getModel(mData.getCar().getBrandId(), mData.getCar().getModelId()).getName());
		}
		
		if (mColor != null) {
			mColor.setColor(Resources.getColor(mData.getCar().getColorId()).getHex());
		}
		
		if (mTrunkSize != null) {
			mTrunkSize.setText(Resources.getTrunk(mData.getCar().getTrunkId()).getName());
		}
		
		if (mDriver != null) {
			mDriver.setFeedbackVisibility((mData.isFeedbackGiven()) ? GONE : VISIBLE);
		}
	}

}
