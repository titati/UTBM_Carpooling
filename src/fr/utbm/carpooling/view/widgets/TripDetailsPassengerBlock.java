package fr.utbm.carpooling.view.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.model.PassengerTrip;

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
			mSeats.setVisibility(INVISIBLE);
			((ViewGroup) ((ViewGroup) getChildAt(0)).getChildAt(1)).getChildAt(0).setVisibility(INVISIBLE);
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
