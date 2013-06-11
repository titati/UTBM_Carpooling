package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User extends UserShort {
	
	private String apiToken;
	
	public User() {}
	
	public User(JSONObject object) {
		super(object);
	}

	public String getApiToken() {
		return apiToken;
	}
	
	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}
	
	public String serializeJSON() {
		return super.serializeJSON() + ", \"apitoken\" : \"" + getApiToken() + "\"";
	}
	
	@Override
	protected void deserializeJSON(JSONObject object) {
		super.deserializeJSON(object);
		
		try {
			setApiToken(object.getString("apitoken"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}