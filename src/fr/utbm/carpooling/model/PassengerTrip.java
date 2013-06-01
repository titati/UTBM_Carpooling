package fr.utbm.carpooling.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PassengerTrip extends BasePassengerTrip {
	
	private boolean feedbackGiven;

	public PassengerTrip() {
		super();
	}
	
	public PassengerTrip(JSONObject object) {
		super(object);
	}

	public boolean isFeedbackGiven() {
		return feedbackGiven;
	}

	public void setFeedbackGiven(boolean feedbackGiven) {
		this.feedbackGiven = feedbackGiven;
	}
	
	@Override
	public void deserializeJSON(JSONObject object) {
		super.deserializeJSON(object);
		
		try {
			setFeedbackGiven(object.getBoolean("feedbackGiven"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void deserializeJSONCheckpoints(JSONArray checkpoints) {
		ArrayList<Checkpoint> liCheckpoints = new ArrayList<Checkpoint>();
		
		try {
			for(int i = 0; i < checkpoints.length(); ++i) {
				liCheckpoints.add(new Checkpoint((JSONObject) checkpoints.get(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		setCheckpoints(liCheckpoints);
	}
}