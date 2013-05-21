package fr.utbm.carpooling.view.widgets;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.Checkpoint;
import fr.utbm.carpooling.model.DriverTripOccurenceShort;

import java.text.DateFormat;
import java.util.ArrayList;

public class DriverTripAdapter extends ArrayAdapter<DriverTripOccurenceShort> {

    private ArrayList<DriverTripOccurenceShort> mItems = null;
    private final DateFormat TIME_FORMAT = android.text.format.DateFormat.getTimeFormat(getContext());
    private final DateFormat DATE_FORMAT = android.text.format.DateFormat.getDateFormat(getContext());

    public DriverTripAdapter(Context context, int layoutResourceId,
                             ArrayList<DriverTripOccurenceShort> data) {
        super(context, layoutResourceId, data);
        mItems = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.view_driver_trip_item, null);
        }
        DriverTripOccurenceShort item = mItems.get(position);
        if (item != null) {

            TripPathView pathView = (TripPathView) v
                    .findViewById(R.id.driver_trip_item_trippathview);
            TextView departurePoint = (TextView) v
                    .findViewById(R.id.driver_trip_item_textview_departure_point);
            TextView departureTime = (TextView) v
                    .findViewById(R.id.driver_trip_item_textview_departure_time);
            TextView departureDate = (TextView) v
                    .findViewById(R.id.driver_trip_item_textview_departure_date);
            TextView arrivalPoint = (TextView) v
                    .findViewById(R.id.driver_trip_item_textview_arrival_point);
            TextView arrivalTime = (TextView) v
                    .findViewById(R.id.driver_trip_item_textview_arrival_time);
            ImageView repeat = (ImageView) v
                    .findViewById(R.id.driver_trip_item_imageView_repeat);

            Checkpoint from = item.getCheckpoints().get(0);
            Checkpoint to = item.getCheckpoints().get(item.getCheckpoints().size() - 1);

            if (pathView != null) {
                pathView.setCheckpoints(item.getCheckpoints());
            }
            if (departurePoint != null) {
                departurePoint.setText(from.getSite().getName());
            }
            if (departureTime != null) {
                departureTime.setText(TIME_FORMAT.format(from.getDate()));
            }
            if (departureDate != null) {
                if (DateUtils.isToday(from.getDate().getTime()))
                    departureDate.setText(R.string.trips_caption_today);
                else
                    departureDate.setText(DATE_FORMAT.format(from.getDate()));
            }
            if (arrivalPoint != null) {
                arrivalPoint.setText(to.getSite().getName());
            }
            if (arrivalTime != null) {
                arrivalTime.setText(TIME_FORMAT.format(to.getDate()));
            }
            if (repeat != null) {
                repeat.setVisibility(item.isRepeat() ? View.VISIBLE
                        : View.GONE);
            }
        }
        return v;
    }

}
