package fr.utbm.carpooling.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.TaskHandler;
import fr.utbm.carpooling.adapter.AlertAdapter;
import fr.utbm.carpooling.model.Alert;
import fr.utbm.carpooling.view.widgets.LoadingDialog;
import fr.utbm.carpooling.webservices.PassengerWebServices;

import java.util.ArrayList;
import java.util.Date;

public class TripsAlertsFragment extends Fragment {

    private ListView mListView = null;
    private LoadingDialog mLoader;
	private TaskHandler<ArrayList<Alert>> mHandler;
    
	private static Date lastUpdate;
	private static ArrayList<Alert> data;

    public TripsAlertsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	setHasOptionsMenu(true);
    	
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trips_alerts, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mListView = (ListView) getView().findViewById(R.id.trips_alerts_listview_alerts);

        initHandler();
        if (lastUpdate == null || (lastUpdate.getTime() < (new Date()).getTime() - 5 * 60000)) refreshData();
        if (data != null) initView(data);
    }
    
    private void refreshData() {
    	mLoader.show();
        PassengerWebServices.getAlerts(mHandler);
	}

	private void initHandler() {
		mLoader = new LoadingDialog(getActivity());
		mHandler = new TaskHandler<ArrayList<Alert>>() {
			
			@Override
			public void taskSuccessful(ArrayList<Alert> object) {
				initView(object);
				data = object;
		        lastUpdate = new Date();
		        
		        mLoader.dismiss();
			}
			
			@Override
			public void taskFailed() {
				Toast.makeText(getActivity().getApplicationContext(), "Error while fetching content", Toast.LENGTH_LONG).show();
		        mLoader.dismiss();
			}
		};
	}

	private void initView(ArrayList<Alert> object) {
    	AlertAdapter adapter = new AlertAdapter(getActivity(), R.id.trips_passenger_listview_trips, object);
		
		mListView.setAdapter(adapter);
        
        mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent(getActivity().getApplicationContext(), TripDetailsDriverActivity.class);
                startActivity(intent);
			}
		});
	}
}
