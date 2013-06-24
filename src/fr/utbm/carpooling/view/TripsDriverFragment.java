package fr.utbm.carpooling.view;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.adapter.DriverTripAdapter;
import fr.utbm.carpooling.model.DriverTripOccurenceShort;
import fr.utbm.carpooling.utils.TaskHandler;
import fr.utbm.carpooling.view.widgets.DriverTripItem;
import fr.utbm.carpooling.view.widgets.LoadingDialog;
import fr.utbm.carpooling.webservices.DriverWebServices;

import java.util.ArrayList;
import java.util.Date;


public class TripsDriverFragment extends Fragment {

    private ListView mListView = null;
    private TaskHandler<ArrayList<DriverTripOccurenceShort>> mHandler = null;
    private LoadingDialog mLoader = null;
    
    private static Date lastUpdate;
    private static ArrayList<DriverTripOccurenceShort> data;

    public TripsDriverFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	setHasOptionsMenu(true);
    	
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trips_driver, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mListView = (ListView) getView().findViewById(R.id.trips_driver_listview_trips);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                CharSequence items[] = new CharSequence[2];
                items[0] = getResources().getString(R.string.action_share);
                items[1] = getResources().getString(R.string.action_delete);

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                // todo: share trip
                                break;

                            case 1:
                                // todo: delete trip
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
    
    private void initView(ArrayList<DriverTripOccurenceShort> object) {
    	DriverTripAdapter adapter = new DriverTripAdapter(getActivity(), R.id.trips_passenger_listview_trips, object);
		
		mListView.setAdapter(adapter);
        
        mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent(getActivity().getApplicationContext(), TripDetailsDriverActivity.class);
				DriverTripItem view = (DriverTripItem) arg1;
				intent.putExtra("abstractTripId", view.getAbstractTripId());
				intent.putExtra("tripId", view.getTripId());
                startActivity(intent);
			}
		});
	}

	private void refreshData() {
        mLoader.show();
        DriverWebServices.getNextTripsShort(mHandler);
    }
    
    private void initHandler() {
		mLoader = new LoadingDialog(getActivity());
		mHandler = new TaskHandler<ArrayList<DriverTripOccurenceShort>>() {
			
			@Override
			public void taskSuccessful(ArrayList<DriverTripOccurenceShort> object) {
				initView(object);
				data = object;
		        lastUpdate = new Date();
		        
		        mLoader.dismiss();
			}
			
			@Override
			public void taskFailed() {
				if (getUserVisibleHint()) Toast.makeText(getActivity().getApplicationContext(), "Error while fetching content", Toast.LENGTH_LONG).show();
		        mLoader.dismiss();
			}
		};
	}


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.trips_driver, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	mLoader.dismiss();
    }
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.trips_menuitem_new:
                startActivity(new Intent(getActivity(), EditTripActivity.class));
                return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
