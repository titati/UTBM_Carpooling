package fr.utbm.carpooling;

import org.json.JSONObject;

public abstract class JSONParsable {
	public JSONParsable(JSONObject object) {
		deserializeJSON(object);
	}
	
	public JSONParsable() {}
	
	protected abstract void deserializeJSON(JSONObject object);
}
