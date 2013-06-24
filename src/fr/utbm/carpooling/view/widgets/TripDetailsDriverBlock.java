package fr.utbm.carpooling.view.widgets;

import android.content.Context;
import android.util.AttributeSet;
import fr.utbm.carpooling.model.DriverTripOccurence;
import fr.utbm.carpooling.utils.Resources;

public class TripDetailsDriverBlock extends TripDetailBlock<DriverTripOccurence> {
	
	protected TripSubscriberBlock sub = null;
	
	public TripDetailsDriverBlock(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void finishBuildView() {
		sub = new TripSubscriberBlock(getContext());
		sub.setData(mData.getPassengers(), mData.getCar().getSeats());
		addView(sub, 2);
	}

	@Override
	protected void initView() {
		if (mTripPath != null) {
			mTripPath.setCheckpoints(mData.getCheckpoints());
		}
		
		if (mSeats != null) {
			mSeats.setVisibility(INVISIBLE);
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
	}



}
