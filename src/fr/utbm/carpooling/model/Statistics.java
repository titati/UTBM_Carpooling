package fr.utbm.carpooling.model;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.utils.JSONParsableObject;

public class Statistics extends JSONParsableObject {

	private int driverTrips;
	private int passengerTrips;
	private int peopleCarried;
	private double rating;
	private ArrayList<Comment> comments;
	
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
	
	public double getRating() {
		return rating;
	}
	
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public ArrayList<Comment> getComments() {
		return comments;
	}
	
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	
	@Override
	protected void deserializeJSON(JSONObject object) {
		try {
			setDriverTrips(object.getInt("drivertrips"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setPassengerTrips(object.getInt("passengertrips"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setPeopleCarried(object.getInt("passengercarried"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setRating(object.getDouble("rating"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		comments = new ArrayList<Comment>();
		
		try {
			for(int i = 0; i < object.getJSONArray("comments").length(); ++i) {
				comments.add(new Comment((JSONObject) object.getJSONArray("comments").get(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}