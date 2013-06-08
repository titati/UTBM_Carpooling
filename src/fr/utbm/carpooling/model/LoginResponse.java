package fr.utbm.carpooling.model;

import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsable;

public class LoginResponse extends JSONParsable {
	
	private String userId;
	private String apiToken;
	private boolean userExist;
	private boolean loggedIn;
	
	public LoginResponse(JSONObject object) {
		super(object);
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getApiToken() {
		return apiToken;
	}
	
	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}
	
	public boolean isUserExist() {
		return userExist;
	}
	
	public void setUserExist(boolean userExist) {
		this.userExist = userExist;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	@Override
	protected void deserializeJSON(JSONObject object) {
		
	}
}
