package fr.utbm.carpooling.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import fr.utbm.carpooling.model.SiteShort;

import java.util.ArrayList;
import java.util.List;


public class SiteShortAdapter extends ArrayAdapter<SiteShort> {

    private List<SiteShort> mItems;
    private List<Integer> mDisabled = new ArrayList<Integer>();

    public SiteShortAdapter(Context context, int textViewResourceId, List<SiteShort> items) {
        super(context, textViewResourceId, items);
        mItems = items;
    }

    public int getCount() {
        return mItems.size();
    }

    public SiteShort getItem(int position) {
        return mItems.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public void disableItem(int position) {
         mDisabled.add(position);
    }

    public void enableItems() {
         mDisabled.clear();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView label = (TextView) convertView;

        if (label == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            label = (TextView) inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        label.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
        label.setText(getItem(position).getName());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        TextView label = (TextView) convertView;

        if (label == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            label = (TextView) inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        boolean enable = !mDisabled.contains(position);
        label.setEnabled(enable);
        label.setClickable(!enable);

        label.setTextAppearance(getContext(), android.R.style.TextAppearance_Medium);
        label.setText(getItem(position).getName());
        return label;
    }
}