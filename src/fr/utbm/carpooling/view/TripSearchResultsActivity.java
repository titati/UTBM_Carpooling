package fr.utbm.carpooling.view;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.adapter.ResultTripAdapter;
import fr.utbm.carpooling.model.TripSearchResultShort;
import fr.utbm.carpooling.model.wrapper.TripSearch;
import fr.utbm.carpooling.utils.TaskHandler;
import fr.utbm.carpooling.view.widgets.LoadingDialog;
import fr.utbm.carpooling.view.widgets.ResultTripItem;
import fr.utbm.carpooling.webservices.PassengerWebServices;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class TripSearchResultsActivity extends Activity {
	
	private ListView mListView = null;
	private TaskHandler<ArrayList<TripSearchResultShort>> mHandler;
	private LoadingDialog mLoader;
	private TripSearch mSearch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mSearch = (TripSearch) getIntent().getExtras().get("search");
		mLoader = new LoadingDialog(this);
		initHandler();
        
		mLoader.show();
        PassengerWebServices.searchTrips(mSearch, mHandler);
	}
	
	private void initHandler() {
        mHandler = new TaskHandler<ArrayList<TripSearchResultShort>>() {

            @Override
            public void taskSuccessful(ArrayList<TripSearchResultShort> object) {
        		setContentView(R.layout.activity_search_results);
                initView(object);
                mLoader.dismiss();
            }

            @Override
            public void taskFailed() {
                finish();
                Toast.makeText(getApplicationContext(), R.string.error_fetching_data, Toast.LENGTH_LONG).show();
                mLoader.dismiss();
            }
        };
    }

    protected void initView(ArrayList<TripSearchResultShort> object) {
		mListView = (ListView) findViewById(R.id.trips_result_listview);
		
    	final ResultTripAdapter adapter = new ResultTripAdapter(getBaseContext(), R.id.trips_alerts_listview_alerts, object, mSearch);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TripSearchResultsActivity.this, TripDetailsSearchActivity.class);
                intent.putExtra("abstractTripId", ((ResultTripItem) view).getAbstractTripId());
                intent.putExtra("tripId", ((ResultTripItem) view).getTripId());
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

	@Override
    protected void onPause() {
        super.onPause();
        mLoader.dismiss();
    }
}
