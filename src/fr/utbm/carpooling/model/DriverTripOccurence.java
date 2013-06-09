package fr.utbm.carpooling.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DriverTripOccurence extends BaseTrip {
	
	private ArrayList<UserShort> mPassengers;
	private Car mCar;

	public DriverTripOccurence(JSONObject object) {
		super(object);
	}

	public DriverTripOccurence() {}

	public ArrayList<UserShort> getPassengers() {
		return mPassengers;
	}

	public void setPassengers(ArrayList<UserShort> passengers) {
		this.mPassengers = passengers;
	}

	public Car getCar() {
		return mCar;
	}

	public void setCar(Car car) {
		this.mCar = car;
	}
	
	@Override
	public void deserializeJSON(JSONObject object) {
		super.deserializeJSON(object);
		
		try {
			setCar(new Car(object.getJSONObject("car")));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		ArrayList<UserShort> passengers = new ArrayList<UserShort>();
		
		try {
			for(int i = 0; i < object.getJSONArray("passengers").length(); ++i) {
				passengers.add(new UserShort((JSONObject) object.getJSONArray("passengers").get(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		setPassengers(passengers);
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