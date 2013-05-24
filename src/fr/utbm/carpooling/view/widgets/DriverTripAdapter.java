package fr.utbm.carpooling.view.widgets;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import fr.utbm.carpooling.model.DriverTripOccurenceShort;

import java.util.ArrayList;

public class DriverTripAdapter extends ArrayAdapter<DriverTripOccurenceShort> {

    private ArrayList<DriverTripOccurenceShort> mItems = null;

    public DriverTripAdapter(Context context, int layoutResourceId,
                             ArrayList<DriverTripOccurenceShort> data) {
        super(context, layoutResourceId, data);
        mItems = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DriverTripItem v = (DriverTripItem) convertView;
        if (v == null) {
            v = new DriverTripItem(getContext(), null);
        }
        
        DriverTripOccurenceShort item = mItems.get(position);
        
        if (item != null) {
        	v.setData(item);
        }
        return v;
    }

}
