package fr.utbm.carpooling.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsableObject;
import fr.utbm.carpooling.webservices.ResourcesWebServices;


public abstract class BaseTrip extends JSONParsableObject {

	protected String abstractTripId;
	protected Date tripId;
	protected ArrayList<? extends CheckpointShort> checkpoints;

	public BaseTrip(JSONObject object) {
		super(object);
	}
	
	public BaseTrip() {}

	public String getAbstractTripId() {
		return abstractTripId;
	}
	
	public void setAbstractTripId(String id) {
		this.abstractTripId = id;
	}
	
	public Date getTripId() {
		return tripId;
	}

	public void setTripId(Date tripId) {
		this.tripId = tripId;
	}
	
	public ArrayList<? extends CheckpointShort> getCheckpoints() {
		return checkpoints;
	}
	
	public void setCheckpoints(ArrayList<? extends CheckpointShort> checkpoints) {
		this.checkpoints = checkpoints;
	}
	
	@Override
	public void deserializeJSON(JSONObject object) {
		try {
			setAbstractTripId(object.getString("abstracttripid"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setTripId(ResourcesWebServices.getStandardDateFormat().parse(object.getString("tripid")));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			deserializeJSONCheckpoints(object.getJSONArray("checkpoints"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	protected abstract void deserializeJSONCheckpoints(JSONArray checkpoints);
}