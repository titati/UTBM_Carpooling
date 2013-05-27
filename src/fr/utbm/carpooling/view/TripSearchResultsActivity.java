package fr.utbm.carpooling.view;

import java.util.ArrayList;
import java.util.Date;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.adapter.ResultTripAdapter;
import fr.utbm.carpooling.model.CheckpointShort;
import fr.utbm.carpooling.model.TripSearchResultShort;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class TripSearchResultsActivity extends Activity {
	
	private ListView mListView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_search_results);
		
		mListView = (ListView) findViewById(R.id.trips_result_listview);
		
		CheckpointShort c1 = new CheckpointShort();
        c1.setNumCheckpoint(0);
        c1.setDate(new Date(113, 4, 20, 13, 0));

        CheckpointShort c2 = new CheckpointShort();
        c2.setNumCheckpoint(1);
        c2.setDate(new Date(113, 4, 20, 13, 15));

        CheckpointShort c3 = new CheckpointShort();
        c3.setNumCheckpoint(2);
        c3.setDate(new Date(113, 4, 20, 13, 40));

        ArrayList<CheckpointShort> checkpoints1 = new ArrayList<CheckpointShort>();
        checkpoints1.add(c1);
        checkpoints1.add(c3);

        ArrayList<CheckpointShort> checkpoints2 = new ArrayList<CheckpointShort>();
        checkpoints2.add(c2);
        checkpoints2.add(c3);

        TripSearchResultShort trip1 = new TripSearchResultShort();
        trip1.setCheckpoints(checkpoints1);

        TripSearchResultShort trip2 = new TripSearchResultShort();
        trip2.setCheckpoints(checkpoints2);

        ArrayList<TripSearchResultShort> trips = new ArrayList<TripSearchResultShort>();
        trips.add(0, trip1);
        trips.add(1, trip2);
		
		ResultTripAdapter adapter = new ResultTripAdapter(getBaseContext(), R.id.trips_alerts_listview_alerts, trips);

        mListView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_results, menu);
		return true;
	}
}
