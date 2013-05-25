package fr.utbm.carpooling.view.widgets;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.DriverCar;

public class DriverCarAdapter extends ArrayAdapter<DriverCar> {

	private ArrayList<DriverCar> items = null;

	public DriverCarAdapter(Context context, int layoutResourceId,
			ArrayList<DriverCar> data) {
		super(context, layoutResourceId, data);
		this.items = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.view_driver_car_item, null);
		}
		DriverCar item = items.get(position);
		if (item != null) {
			TextView name = (TextView) v
					.findViewById(R.id.driver_car_item_textview_name);
			TextView seats = (TextView) v
					.findViewById(R.id.driver_car_item_textview_seats);
			TextView trunk = (TextView) v
					.findViewById(R.id.driver_car_item_textview_trunk);
			ColorCheckBox color = (ColorCheckBox) v
					.findViewById(R.id.driver_car_item_colorcheckbox_color);
			ImageView defaultCar = (ImageView) v
					.findViewById(R.id.driver_car_item_imageview_default);

			if (name != null) {
				name.setText(item.getBrand().getName() + ' '
						+ item.getModel().getName());
			}
			if (seats != null) {
				seats.setText(String.valueOf(item.getSeats()));
			}
			if (trunk != null) {
				trunk.setText(item.getTrunk().getName());
			}
			if (color != null) {
				color.setColor(item.getColor().getHex());
			}
			if (defaultCar != null) {
				defaultCar.setVisibility(item.isDefaultCar() ? View.VISIBLE
						: View.INVISIBLE);
			}
		}
		return v;
	}

}
