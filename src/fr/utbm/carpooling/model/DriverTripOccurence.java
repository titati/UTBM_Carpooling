package fr.utbm.carpooling.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DriverTripOccurence extends BaseTrip {
	
	private ArrayList<User> passengers;
	private Car car;

	public ArrayList<User> getPassengers() {
		return passengers;
	}

	public void setPassengers(ArrayList<User> passengers) {
		this.passengers = passengers;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	@Override
	public void deserializeJSON(JSONObject object) {
		super.deserializeJSON(object);
		
		try {
			setCar(new Car(object.getJSONObject("car")));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		ArrayList<User> passengers = new ArrayList<User>();
		
		try {
			for(int i = 0; i < object.getJSONArray("passengers").length(); ++i) {
				User u = new User((JSONObject) object.getJSONArray("passengers").get(i));
				passengers.add(u);
			}
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