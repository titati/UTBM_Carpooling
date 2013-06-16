package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONSerializable;

public class DriverCar extends Car implements JSONSerializable {

	private boolean mDefaultCar;

	public DriverCar() {
		super();
	}

	public DriverCar(JSONObject object) {
		super(object);
	}

	public boolean isDefaultCar() {
		return mDefaultCar;
	}

	public void setDefaultCar(boolean defaultCar) {
		this.mDefaultCar = defaultCar;
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
		return "\"id\" : \"" + getId() +
				"\", \"brandid\" : \"" + getBrandId() +
				"\", \"modelid\" : \"" + getModelId() +
				"\", \"colorid\" : \"" + getColorId() +
				"\", \"trunkid\" : \"" + getTrunkId() +
				"\", \"seats\" : \"" + getSeats() +
				"\", \"defaultcar\" : \"" + isDefaultCar() + "\""; 
	}
}