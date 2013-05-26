package fr.utbm.carpooling.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.Alert;
import fr.utbm.carpooling.model.Checkpoint;
import fr.utbm.carpooling.model.SiteShort;
import fr.utbm.carpooling.view.widgets.AlertAdapter;

import java.util.ArrayList;
import java.util.Date;

public class TripsAlertsFragment extends Fragment {

    private ListView mListView = null;

    public TripsAlertsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trips_alerts, container,
                false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mListView = (ListView) getView().findViewById(R.id.trips_alerts_listview_alerts);

        Checkpoint c1 = new Checkpoint();
        c1.setSiteId(0);
        c1.setNumCheckpoint(0);
        c1.setDate(new Date(113, 4, 20, 13, 0));

        Checkpoint c2 = new Checkpoint();
        c2.setSiteId(1);
        c2.setNumCheckpoint(1);
        c2.setDate(new Date(113, 4, 20, 13, 15));

        Checkpoint c3 = new Checkpoint();
        c3.setSiteId(2);
        c3.setNumCheckpoint(2);
        c3.setDate(new Date(113, 4, 20, 13, 40));

        ArrayList<Checkpoint> checkpoints1 = new ArrayList<Checkpoint>();
        checkpoints1.add(c1);
        checkpoints1.add(c3);

        ArrayList<Checkpoint> checkpoints2 = new ArrayList<Checkpoint>();
        checkpoints2.add(c2);
        checkpoints2.add(c3);

        Alert trip1 = new Alert();
        trip1.setCheckpoints(checkpoints1);

        Alert trip2 = new Alert();
        trip2.setCheckpoints(checkpoints2);

        ArrayList<Alert> trips = new ArrayList<Alert>();
        trips.add(0, trip1);
        trips.add(1, trip2);

        AlertAdapter adapter = new AlertAdapter(getActivity(),
                R.id.trips_alerts_listview_alerts, trips);

        mListView.setAdapter(adapter);
    }
}
