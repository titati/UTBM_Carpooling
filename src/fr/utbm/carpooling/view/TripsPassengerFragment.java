package fr.utbm.carpooling.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.adapter.PassengerTripAdapter;
import fr.utbm.carpooling.model.PassengerTripShort;
import fr.utbm.carpooling.utils.TaskHandler;
import fr.utbm.carpooling.view.widgets.PassengerTripItem;
import fr.utbm.carpooling.webservices.PassengerWebServices;

import java.util.ArrayList;
import java.util.Date;

public class TripsPassengerFragment extends Fragment {

    private ListView mListView = null;
    private TaskHandler<ArrayList<PassengerTripShort>> mHandler = null;
	protected boolean mLoading;

    private static Date lastUpdate;
    private static ArrayList<PassengerTripShort> data;

    public TripsPassengerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trips_passenger, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mListView = (ListView) getView().findViewById(R.id.trips_passenger_listview_trips);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                CharSequence items[] = new CharSequence[1];
                items[0] = getResources().getString(R.string.trips_details_unsuscribe);

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                // todo: unsuscribe
                                break;
                        }
                        // update list
                    }
                });

                builder.create().show();
                return false;
            }
        });

        initHandler();
        if (lastUpdate == null || (lastUpdate.getTime() < (new Date()).getTime() - 5 * 60000)) refreshData();
        if (data != null) initView(data);
    }

    private void refreshData() {
        if (getUserVisibleHint()) getActivity().setProgressBarIndeterminateVisibility(true);
        mLoading = true;
        PassengerWebServices.getNextTripsShort(mHandler);
    }

    private void initHandler() {

        mHandler = new TaskHandler<ArrayList<PassengerTripShort>>() {

            @Override
            public void taskSuccessful(ArrayList<PassengerTripShort> object) {
                initView(object);
                data = object;
                lastUpdate = new Date();
                getActivity().setProgressBarIndeterminateVisibility(false);
                mLoading = false;
            }

            @Override
            public void taskFailed() {
                if (getUserVisibleHint())
                    Toast.makeText(getActivity().getApplicationContext(), "Error while fetching content", Toast.LENGTH_LONG).show();
                getActivity().setProgressBarIndeterminateVisibility(false);
                mLoading = false;
            }
        };
    }

    private void initView(ArrayList<PassengerTripShort> object) {
        PassengerTripAdapter adapter = new PassengerTripAdapter(getActivity(), R.id.trips_passenger_listview_trips, object);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(getActivity().getApplicationContext(), TripDetailsPassengerActivity.class);
                intent.putExtra("abstractTripId", ((PassengerTripItem) arg1).getAbstractTripId());
                intent.putExtra("tripId", ((PassengerTripItem) arg1).getTripId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.trips_passenger, menu);
        super.onCreateOptionsMenu(menu, inflater);
        
        if (mLoading) getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.trips_menuitem_search:
                Intent intent = new Intent(getActivity(), TripSearchActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
