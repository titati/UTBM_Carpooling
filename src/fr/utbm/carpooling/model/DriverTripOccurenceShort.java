package fr.utbm.carpooling.model;

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
}