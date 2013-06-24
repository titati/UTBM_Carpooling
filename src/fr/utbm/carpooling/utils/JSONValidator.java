package fr.utbm.carpooling.utils;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONValidator {
	private JSONObject object = null;
	
	public JSONValidator(String jsonString) {
		try {
			object = new JSONObject(jsonString);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		Log.i("json String", jsonString);
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
