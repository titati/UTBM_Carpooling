package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

public class DriverCar extends Car {

	private boolean defaultCar;

	public DriverCar() {
		super();
	}

	public DriverCar(JSONObject object) {
		super(object);
	}

	public boolean isDefaultCar() {
		return defaultCar;
	}

	public void setDefaultCar(boolean defaultCar) {
		this.defaultCar = defaultCar;
	}
	
	@Override
	protected void deserializeJSON(JSONObject object) {
		super.deserializeJSON(object);
		
		try {
			setDefaultCar(object.getBoolean("defaultcar"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public String serializeJSON() {
		return super.serializeJSON() + ", \"defaultcar\" : \"" + isDefaultCar() + "\""; 
	}
}