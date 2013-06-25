package fr.utbm.carpooling.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.adapter.CommentAdapter;
import fr.utbm.carpooling.model.Statistics;
import fr.utbm.carpooling.utils.TaskHandler;
import fr.utbm.carpooling.webservices.UserWebServices;

import java.util.Date;


public class ProfileStatsFragment extends Fragment {
	
	private TaskHandler<Statistics> mHandler;
	private boolean mLoading;
	
    private static Date lastUpdate;
    private static Statistics data;
    
    private TextView mDriverTrips;
    private TextView mPassengerTrips;
    private TextView mCarriedPeople;
    private RatingBar mRating;

	public ProfileStatsFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_profile_stats, container, false);
	}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        mDriverTrips = (TextView) getView().findViewById(R.id.profile_stats_textview_driver_trips);
        mPassengerTrips = (TextView) getView().findViewById(R.id.profile_stats_textview_passenger_trips);
        mCarriedPeople = (TextView) getView().findViewById(R.id.profile_stats_textview_people_carried);
        
        mRating = (RatingBar) getView().findViewById(R.id.profile_stats_ratingbar_rating);
        
        initHandler();
        if (lastUpdate == null || (lastUpdate.getTime() < (new Date()).getTime() - 5 * 60000)) refreshData();
        if (data != null) initView(data);
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    	super.onCreateOptionsMenu(menu, inflater);
    	if (mLoading) getActivity().setProgressBarIndeterminateVisibility(true);
    }

	private void initHandler() {
		mHandler = new TaskHandler<Statistics>() {

            @Override
            public void taskSuccessful(Statistics object) {
                initView(object);
                data = object;
                lastUpdate = new Date();
                getActivity().setProgressBarIndeterminateVisibility(false);
                mLoading = false;
            }

			@Override
            public void taskFailed() {
                if (getUserVisibleHint())
                    Toast.makeText(getActivity().getApplicationContext(), R.string.error_fetching_data, Toast.LENGTH_LONG).show();
                getActivity().setProgressBarIndeterminateVisibility(false);
                mLoading = false;
            }
        };
	}

    private void refreshData() {
    	if (getUserVisibleHint()) getActivity().setProgressBarIndeterminateVisibility(true);
        mLoading = true;
        UserWebServices.getStatistics(mHandler);
    }

    private void initView(Statistics object) {
    	ListView commentList = (ListView) getView().findViewById(R.id.profile_stats_listview_comments);

        CommentAdapter adapter = new CommentAdapter(getActivity(), R.layout.view_comment_item, object.getComments());

        commentList.setAdapter(adapter);

        mDriverTrips.setText(String.valueOf(object.getDriverTrips()));
        mPassengerTrips.setText(String.valueOf(object.getPassengerTrips()));
        mCarriedPeople.setText(String.valueOf(object.getPeopleCarried()));
        
        mRating.setRating((float) object.getRating());
	}
}
