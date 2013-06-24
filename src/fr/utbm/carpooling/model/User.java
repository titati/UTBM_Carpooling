package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.utils.JSONSerializable;

public class User extends UserShort implements JSONSerializable {
	
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
		return "\"userid\" : \"" + getUserId() +
				"\", \"lastname\" : \"" + getLastname() +
				"\", \"firstname\" : \"" + getFirstname() +
				"\", \"phone\" : \"" + getPhone() +
				"\", \"email\" : \"" + getEmail() +
				"\", \"apitoken\" : \"" + getApiToken() + "\"";
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