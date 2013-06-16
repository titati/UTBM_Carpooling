package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsableObject;

public class TripFeedback extends JSONParsableObject {

	private int rating;
	private String comment;
	
	public TripFeedback(JSONObject object) {
		super(object);
	}
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	protected void deserializeJSON(JSONObject object) {
		try {
			setRating(object.getInt("rating"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setComment(object.getString("feedback"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}