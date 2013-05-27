package fr.utbm.carpooling.model;

import java.io.Serializable;

public class Car implements Serializable {

	protected String id;
	protected int brandId;
	protected int modelId;
	protected int colorId;
	protected int seats;
	protected int trunkId;
	
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
}