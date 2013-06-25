package fr.utbm.carpooling.adapter;

import java.util.ArrayList;

import fr.utbm.carpooling.model.TripSearchResultShort;
import fr.utbm.carpooling.model.wrapper.TripSearch;
import fr.utbm.carpooling.view.widgets.ResultTripItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ResultTripAdapter extends ArrayAdapter<TripSearchResultShort> {
	
	private ArrayList<TripSearchResultShort> mItems = null;
	private TripSearch mSearch;
	
	public ResultTripAdapter(Context context, int layoutResourceId, ArrayList<TripSearchResultShort> items, TripSearch search) {
		super(context, layoutResourceId, items);
		mItems = items;
		mSearch = search;
	}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	ResultTripItem v = (ResultTripItem)convertView;
        
        if (v == null) {
        	v = new ResultTripItem(getContext(), null);
        }
        
        TripSearchResultShort item = mItems.get(position);
        
        if (item != null) {
        	v.setData(item);
        	v.setSearchInfo(mSearch);
        }
        
        return v;
    }
}
