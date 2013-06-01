package fr.utbm.carpooling.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.TaskHandler;
import fr.utbm.carpooling.adapter.PassengerTripAdapter;
import fr.utbm.carpooling.model.PassengerTripShort;
import fr.utbm.carpooling.view.widgets.LoadingDialog;
import fr.utbm.carpooling.webservices.PassengerWebServices;

import java.util.ArrayList;

public class TripsPassengerFragment extends Fragment {

    private ListView mListView = null;
    private TaskHandler<ArrayList<PassengerTripShort>> mHandler = null;
    private LoadingDialog mLoader = null;

    public TripsPassengerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trips_passenger, container,
                false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mListView = (ListView) getView().findViewById(R.id.trips_passenger_listview_trips);
        
        initHandler();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	
        mLoader.show();
        PassengerWebServices.getNextTripsShort(0, mHandler);
    }

	private void initHandler() {
		mLoader = new LoadingDialog(getActivity());
		mHandler = new TaskHandler<ArrayList<PassengerTripShort>>() {
			
			@Override
			public void taskSuccessful(ArrayList<PassengerTripShort> object) {
				PassengerTripAdapter adapter = new PassengerTripAdapter(getActivity(), R.id.trips_passenger_listview_trips, object);
				
				mListView.setAdapter(adapter);
		        
		        mListView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						Intent intent = new Intent(getActivity().getApplicationContext(), TripDetailsPassengerActivity.class);
		                startActivity(intent);
					}
				});
		        
		        mLoader.hide();
			}
			
			@Override
			public void taskFailed() {
				Log.e("Fail", "loading trips");
		        mLoader.hide();
			}
		};
	}

}
