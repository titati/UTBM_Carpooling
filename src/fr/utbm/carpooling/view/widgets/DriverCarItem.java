package fr.utbm.carpooling.view.widgets;

import android.widget.*;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.model.DriverCar;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

public class DriverCarItem extends LinearLayout {
	
	protected DriverCar mCar = null;
	
	protected TextView mName = null;
	protected ColorCheckBox mColor = null;
	protected TextView mSeats = null;
	protected TextView mTrunkSize = null;
	protected TextView mDefaultCar = null;
	
	public DriverCarItem(Context context, AttributeSet attrs) {
		super(context, attrs);

        setOrientation(LinearLayout.VERTICAL);

		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_driver_car_item, this, true);
        
		initItems();
	}
	
	public void setCar(DriverCar car) {
		mCar = car;
		initView();
	}
	
	protected void initItems() {
		mName = (TextView) findViewById(R.id.driver_car_item_textview_name);
        mColor = (ColorCheckBox) findViewById(R.id.driver_car_item_colorcheckbox_color);
        mSeats = (TextView) findViewById(R.id.driver_car_item_textview_seats);
        mTrunkSize = (TextView) findViewById(R.id.driver_car_item_textview_trunk);
        mDefaultCar = (TextView) findViewById(R.id.driver_car_item_imageview_default);
	}
	
	protected void initView() {
		if (mName != null) {
			mName.setText(Resources.getBrand(mCar.getBrandId()).getName() + " " + Resources.getModel(mCar.getBrandId(), mCar.getModelId()).getName());
		}
	
		if (mSeats != null) {
			mSeats.setText(String.valueOf(mCar.getSeats()));
		}
		
		if (mTrunkSize != null) {
			mTrunkSize.setText(Resources.getTrunk(mCar.getTrunkId()).getName());
		}
		
		if (mColor != null) {
			mColor.setColor(Resources.getColor(mCar.getColorId()).getHex());
		}
		
		if (mDefaultCar != null) {
			mDefaultCar.setVisibility(mCar.isDefaultCar() ? View.VISIBLE : View.GONE);
		}
	}
}