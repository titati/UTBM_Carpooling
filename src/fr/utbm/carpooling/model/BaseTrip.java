package fr.utbm.carpooling.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsable;


public abstract class BaseTrip extends JSONParsable {

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
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss", Locale.ENGLISH);
		try {
			setTripId(df.parse(object.getString("tripid")));
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