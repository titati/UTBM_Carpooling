package fr.utbm.carpooling.view;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.adapter.AlertAdapter;
import fr.utbm.carpooling.model.Alert;
import fr.utbm.carpooling.utils.TaskHandler;
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

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                CharSequence items[] = new CharSequence[1];
                items[0] = getResources().getString(R.string.action_delete);

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                // todo: delete alert
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
				if (getUserVisibleHint()) Toast.makeText(getActivity().getApplicationContext(), "Error while fetching content", Toast.LENGTH_LONG).show();
		        mLoader.dismiss();
			}
		};
	}

	private void initView(ArrayList<Alert> object) {
    	AlertAdapter adapter = new AlertAdapter(getActivity(), R.id.trips_passenger_listview_trips, object);
		
		mListView.setAdapter(adapter);
        
        mListView.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				
				
				return false;
			}
		});
	}
    
    @Override
    public void onPause() {
    	super.onPause();
    	mLoader.dismiss();
    }
}
