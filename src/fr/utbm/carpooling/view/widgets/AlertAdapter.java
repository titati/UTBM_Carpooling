package fr.utbm.carpooling.view.widgets;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.Alert;
import fr.utbm.carpooling.model.Checkpoint;

import java.text.DateFormat;
import java.util.ArrayList;

public class AlertAdapter extends ArrayAdapter<Alert> {

    private ArrayList<Alert> mItems = null;
    private final DateFormat TIME_FORMAT = android.text.format.DateFormat.getTimeFormat(getContext());
    private final DateFormat DATE_FORMAT = android.text.format.DateFormat.getDateFormat(getContext());

    public AlertAdapter(Context context, int layoutResourceId,
                        ArrayList<Alert> data) {
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
