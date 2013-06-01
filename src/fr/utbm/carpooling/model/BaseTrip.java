package fr.utbm.carpooling.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsable;


public abstract class BaseTrip extends JSONParsable {

	protected String id;
	protected ArrayList<? extends CheckpointShort> checkpoints;

	public BaseTrip(JSONObject object) {
		super(object);
	}
	
	public BaseTrip() {}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
			setId(object.getString("id"));
		} catch (JSONException e) {
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