package fr.utbm.carpooling.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.model.DriverCar;

import java.util.ArrayList;


public class CarAdapter extends ArrayAdapter<DriverCar> {

    private ArrayList<DriverCar> mItems;

    public CarAdapter(Context context, int textViewResourceId, ArrayList<DriverCar> items) {
        super(context, textViewResourceId, items);
        mItems = items;
    }

    public int getCount() {
        return mItems.size();
    }

    public DriverCar getItem(int position) {
        return mItems.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView label = (TextView) convertView;

        if (label == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            label = (TextView) inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        label.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);

        DriverCar item = getItem(position);
        String brand = Resources.getBrand(item.getBrandId()).getName();
        String model = Resources.getModel(item.getBrandId(), item.getModelId()).getName();

        label.setText(brand + " " + model);
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        TextView label = (TextView) convertView;

        if (label == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            label = (TextView) inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        label.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);

        DriverCar item = getItem(position);
        String brand = Resources.getBrand(item.getBrandId()).getName();
        String model = Resources.getModel(item.getBrandId(), item.getModelId()).getName();

        label.setText(brand + " " + model);

        return label;
    }
}