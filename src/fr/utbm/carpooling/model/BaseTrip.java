package fr.utbm.carpooling.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.utils.JSONParsableObject;
import fr.utbm.carpooling.webservices.ResourcesWebServices;


public abstract class BaseTrip extends JSONParsableObject {

	protected int abstractTripId;
	protected Date tripId;
	protected ArrayList<? extends CheckpointShort> checkpoints;

	public BaseTrip(JSONObject object) {
		super(object);
	}
	
	public BaseTrip() {}

	public int getAbstractTripId() {
		return abstractTripId;
	}
	
	public void setAbstractTripId(int id) {
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
			setAbstractTripId(object.getInt("abstracttripid"));
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