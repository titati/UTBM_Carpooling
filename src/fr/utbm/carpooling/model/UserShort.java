package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsable;

public class UserShort extends JSONParsable {
	
	private String name;
	private String firstname;
	private String phone;
	private String email;
	
	public UserShort() {}
	
	public UserShort(JSONObject object) {
		super(object);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	@Override
	protected void deserializeJSON(JSONObject object) {
		try {
			setName(object.getString("name"));
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