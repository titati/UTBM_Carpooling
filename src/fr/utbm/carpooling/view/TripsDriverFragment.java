package fr.utbm.carpooling.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.Checkpoint;
import fr.utbm.carpooling.model.DriverTripOccurenceShort;
import fr.utbm.carpooling.model.SiteShort;
import fr.utbm.carpooling.view.widgets.DriverTripAdapter;

import java.util.ArrayList;
import java.util.Date;


public class TripsDriverFragment extends Fragment {

    private ListView mListView = null;

    public TripsDriverFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trips_driver, container,
                false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mListView = (ListView) getView().findViewById(R.id.trips_driver_listview_trips);

        SiteShort A = new SiteShort();
        A.setName("Belfort");
        SiteShort B = new SiteShort();
        B.setName("Sevenans");
        SiteShort C = new SiteShort();
        C.setName("Montbeliard");

        Checkpoint c1 = new Checkpoint();
        c1.setSite(A);
        c1.setDate(new Date(113, 4, 20, 13, 0));

        Checkpoint c2 = new Checkpoint();
        c2.setSite(B);
        c2.setDate(new Date(113, 4, 20, 13, 15));

        Checkpoint c3 = new Checkpoint();
        c3.setSite(C);
        c3.setDate(new Date(113, 4, 20, 13, 40));

        ArrayList<Checkpoint> checkpoints1 = new ArrayList<Checkpoint>();
        checkpoints1.add(c1);
        checkpoints1.add(c2);
        checkpoints1.add(c3);

        ArrayList<Checkpoint> checkpoints2 = new ArrayList<Checkpoint>();
        checkpoints2.add(c2);
        checkpoints2.add(c3);

        DriverTripOccurenceShort trip1 = new DriverTripOccurenceShort();
        trip1.setCheckpoints(checkpoints1);
        trip1.setRepeat(true);

        DriverTripOccurenceShort trip2 = new DriverTripOccurenceShort();
        trip2.setCheckpoints(checkpoints2);
        trip2.setRepeat(false);

        ArrayList<DriverTripOccurenceShort> trips = new ArrayList<DriverTripOccurenceShort>();
        trips.add(0, trip1);
        trips.add(1, trip2);

        DriverTripAdapter adapter = new DriverTripAdapter(getActivity(),
                R.id.trips_driver_listview_trips, trips);

        mListView.setAdapter(adapter);



    }

}
