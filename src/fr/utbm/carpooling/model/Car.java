package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsable;

public class Car extends JSONParsable {

	protected String id;
	protected int brandId;
	protected int modelId;
	protected int colorId;
	protected int seats;
	protected int trunkId;

	public Car() {}
	
	public Car(JSONObject object) {
		super(object);
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public int getBrandId() {
		return brandId;
	}
	
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	
	public int getModelId() {
		return modelId;
	}
	
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	
	public int getColorId() {
		return colorId;
	}
	
	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
	
	public int getSeats() {
		return seats;
	}
	
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	public int getTrunkId() {
		return trunkId;
	}
	
	public void setTrunkId(int trunkId) {
		this.trunkId = trunkId;
	}

	@Override
	protected void deserializeJSON(JSONObject object) {
		try {
			setId(object.getString("id"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setBrandId(object.getInt("brandId"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setModelId(object.getInt("modelId"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setColorId(object.getInt("colorId"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setSeats(object.getInt("seats"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setTrunkId(object.getInt("trunkId"));
		} catch(JSONException e) {
			e.printStackTrace();
		}
	}
}