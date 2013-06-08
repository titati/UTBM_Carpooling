package fr.utbm.carpooling.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import fr.utbm.carpooling.model.DriverCar;
import fr.utbm.carpooling.view.widgets.DriverCarItem;

public class DriverCarAdapter extends ArrayAdapter<DriverCar> {

	private ArrayList<DriverCar> items = null;

	public DriverCarAdapter(Context context, int layoutResourceId,
			ArrayList<DriverCar> data) {
		super(context, layoutResourceId, data);
		this.items = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DriverCarItem v = (DriverCarItem) convertView;
		
		if (v == null) {
			v = new DriverCarItem(getContext(), null);
		}

		DriverCar item = items.get(position);
		
		if (item != null) {
			v.setCar(item);
		}
		
		return v;
	}

}
