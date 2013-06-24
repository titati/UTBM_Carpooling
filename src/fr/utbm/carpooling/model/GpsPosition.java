package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.utils.JSONParsableObject;
import fr.utbm.carpooling.utils.JSONSerializable;

public class GpsPosition extends JSONParsableObject implements JSONSerializable {

	private double latitude;
	private double longitude;
	
	public GpsPosition(JSONObject object) {
		super(object);
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	protected void deserializeJSON(JSONObject object) {
		try {
			setLatitude(object.getDouble("latitude"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setLongitude(object.getDouble("longitude"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String serializeJSON() {
		return "\"latitude\" : \"" + getLatitude() +
				"\", \"longitude\" : \"" + getLongitude() + "\"";
	}

}