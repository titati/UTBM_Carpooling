package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseDriverTrip extends BaseTrip {
	
	protected DriverCar car;
	protected String description;
	
	public BaseDriverTrip(JSONObject object) {
		super(object);
	}
	
	public DriverCar getCar() {
		return car;
	}
	
	public void setCar(DriverCar car) {
		this.car = car;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public void deserializeJSON(JSONObject object) {
		super.deserializeJSON(object);
		
		try {
			setCar(new DriverCar(object.getJSONObject("car")));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setDescription(object.getString("description"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}