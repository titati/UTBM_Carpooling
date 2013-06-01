package fr.utbm.carpooling.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DriverTripOccurenceShort extends BaseTrip {

	private boolean repeat;

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}
	
	@Override
	public void deserializeJSON(JSONObject object) {
		super.deserializeJSON(object);
		
		try {
			setRepeat(object.getBoolean("repeat"));
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