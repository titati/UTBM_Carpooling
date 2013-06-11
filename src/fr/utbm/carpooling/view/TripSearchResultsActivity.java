package fr.utbm.carpooling.view;

import java.util.ArrayList;
import java.util.Date;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.adapter.ResultTripAdapter;
import fr.utbm.carpooling.model.CheckpointShort;
import fr.utbm.carpooling.model.TripSearchResultShort;
import android.app.Activity;
import android.os.Bundle;
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

        trip1.setTrunkId(2);

        TripSearchResultShort trip2 = new TripSearchResultShort();
        trip2.setCheckpoints(checkpoints2);

        ArrayList<TripSearchResultShort> trips = new ArrayList<TripSearchResultShort>();
//        trips.add(0, trip1);
//        trips.add(1, trip2);
		
		final ResultTripAdapter adapter = new ResultTripAdapter(getBaseContext(), R.id.trips_alerts_listview_alerts, trips);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TripSearchResultsActivity.this, TripDetailsPassengerActivity.class);
                startActivity(intent);
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TripSearchResultsActivity.this);

                CharSequence items[] = new CharSequence[1];
                items[0] = getResources().getString(R.string.trips_details_suscribe);

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                // todo: suscribe
                                break;
                        }
                        // update list
                    }
                });

                builder.create().show();
                return false;
            }
        });

        if (adapter.getCount() == 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton(R.string.action_yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // todo: create alert
                    finish();
                }
            });

            builder.setNegativeButton(R.string.action_no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            builder.setTitle(R.string.search_results_no_results);
            builder.setMessage(R.string.search_results_message_create_alert);
            builder.create().show();


        }


	}
}
