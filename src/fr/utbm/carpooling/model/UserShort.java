package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsable;

public class UserShort extends JSONParsable {
	
	private String userId;
	private String lastname;
	private String firstname;
	private String phone;
	private String email;
	
	public UserShort() {}
	
	public UserShort(JSONObject object) {
		super(object);
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String serializeJSON() {
		return  "\"userid\" : \"" + getUserId() +
				"\", \"lastname\" : \"" + getLastname() +
				"\", \"firstname\" : \"" + getFirstname() +
				"\", \"phone\" : \"" + getPhone() +
				"\", \"email\" : \"" + getEmail() + "\"";
	}
	
	@Override
	protected void deserializeJSON(JSONObject object) {
		try {
			setUserId(object.getString("userid"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setLastname(object.getString("lastname"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setFirstname(object.getString("firstname"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setPhone(object.getString("phone"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setEmail(object.getString("email"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}