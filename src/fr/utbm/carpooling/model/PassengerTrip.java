package fr.utbm.carpooling.model;

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
}