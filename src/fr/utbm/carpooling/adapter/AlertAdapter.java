package fr.utbm.carpooling.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import fr.utbm.carpooling.model.Alert;
import fr.utbm.carpooling.view.widgets.AlertTripItem;

import java.util.ArrayList;

public class AlertAdapter extends ArrayAdapter<Alert> {

    private ArrayList<Alert> mItems = null;

    public AlertAdapter(Context context, int layoutResourceId, ArrayList<Alert> data) {
        super(context, layoutResourceId, data);
        mItems = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	AlertTripItem v = (AlertTripItem)convertView;
        
        if (v == null) {
        	v = new AlertTripItem(getContext(), null);
        }
        
        Alert item = mItems.get(position);
        
        if (item != null) {
        	v.setData(item);
        }
        
        return v;
    }

}
