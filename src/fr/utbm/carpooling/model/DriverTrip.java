package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

public class DriverTrip extends BaseDriverTrip {

	public DriverTrip(JSONObject object) {
		super(object);
	}

	private Repeat repeat;

	public Repeat getRepeat() {
		return repeat;
	}

	public void setRepeat(Repeat repeat) {
		this.repeat = repeat;
	}
	
	@Override
	public void deserializeJSON(JSONObject object) {
		super.deserializeJSON(object);
		
		try {
			setRepeat(new Repeat(object.getJSONObject("repeat")));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}