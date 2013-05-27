package fr.utbm.carpooling.view.widgets;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.model.DriverCar;
import fr.utbm.carpooling.view.HomeActivity;
import fr.utbm.carpooling.view.ProfileCarsFragment;
import fr.utbm.carpooling.view.TripDetailsDriverActivity;
import fr.utbm.carpooling.view.TripSearchResultsActivity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

public class DriverCarItem extends TableLayout {
	
	protected DriverCar mCar = null;
	
	protected TextView mName = null;
	protected ColorCheckBox mColor = null;
	protected TextView mSeats = null;
	protected TextView mTrunkSize = null;
	protected ImageView mDefaultCar = null;
	
	public DriverCarItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_driver_car_item, this, true);
        
        setColumnStretchable(0, true);
        setColumnStretchable(1, true);
        setColumnStretchable(2, true);
        setPadding(10, 10, 10, 10);
        setClickable(true);
        
		initItems();
	}
	
	public void setCar(DriverCar car) {
		mCar = car;
		initView();
	}
	
	protected void initItems() {
		mName = (TextView) ((ViewGroup) getChildAt(0)).getChildAt(0);
        mColor = (ColorCheckBox) ((ViewGroup) getChildAt(1)).getChildAt(0);
        mSeats = (TextView) ((ViewGroup) ((ViewGroup) getChildAt(1)).getChildAt(1)).getChildAt(1);
        mTrunkSize = (TextView) ((ViewGroup) ((ViewGroup) getChildAt(1)).getChildAt(2)).getChildAt(1);
        mDefaultCar = (ImageView) ((ViewGroup) getChildAt(1)).getChildAt(3);
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
			mDefaultCar.setVisibility(mCar.isDefaultCar() ? View.VISIBLE : View.INVISIBLE);
		}
	}
}