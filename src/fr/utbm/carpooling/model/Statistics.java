package fr.utbm.carpooling.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsable;

public class Statistics extends JSONParsable {

	private int driverTrips;
	private int passengerTrips;
	private int peopleCarried;
	private int rating;
	private List<Comment> comments;
	
	public Statistics() {}
	
	public Statistics(JSONObject object) {
		super(object);
	}
	
	public int getDriverTrips() {
		return driverTrips;
	}
	
	public void setDriverTrips(int driverTrips) {
		this.driverTrips = driverTrips;
	}
	
	public int getPassengerTrips() {
		return passengerTrips;
	}
	
	public void setPassengerTrips(int passengerTrips) {
		this.passengerTrips = passengerTrips;
	}
	
	public int getPeopleCarried() {
		return peopleCarried;
	}
	
	public void setPeopleCarried(int peopleCarried) {
		this.peopleCarried = peopleCarried;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	@Override
	protected void deserializeJSON(JSONObject object) {
		try {
			setDriverTrips(object.getInt("driverTrips"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setPassengerTrips(object.getInt("passengerTrips"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setPeopleCarried(object.getInt("passengerCarried"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setRating(object.getInt("rating"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		ArrayList<Comment> comments = new ArrayList<Comment>();
		
		try {
			for(int i = 0; i < object.getJSONArray("comments").length(); ++i) {
				comments.add(new Comment((JSONObject) object.getJSONArray("comments").get(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		setComments(comments);
	}

}