package fr.utbm.carpooling.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import fr.utbm.carpooling.model.PassengerTripShort;
import fr.utbm.carpooling.view.widgets.PassengerTripItem;

import java.util.ArrayList;

public class PassengerTripAdapter extends ArrayAdapter<PassengerTripShort> {

    private ArrayList<PassengerTripShort> mItems = null;

    public PassengerTripAdapter(Context context, int layoutResourceId,
                                ArrayList<PassengerTripShort> data) {
        super(context, layoutResourceId, data);
        mItems = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	PassengerTripItem v = (PassengerTripItem) convertView;
        
    	if (v == null) {
            v = new PassengerTripItem(getContext(), null);
        }
        
        PassengerTripShort item = mItems.get(position);
        
        if (item != null) {
        	v.setData(item);
        }
        
        return v;
    }

}
