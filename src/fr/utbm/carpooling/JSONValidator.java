package fr.utbm.carpooling;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONValidator {
	private JSONObject object = null;
	
	public JSONValidator(String jsonString) {
		try {
			object = new JSONObject(jsonString);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isValid() {
		if (object != null) {
			try {
				return object.getBoolean("valid");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public JSONObject getObject() {
		return object;
	}
}
