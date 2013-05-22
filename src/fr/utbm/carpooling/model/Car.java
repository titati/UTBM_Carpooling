package fr.utbm.carpooling.model;


import java.io.Serializable;

public class Car implements Serializable {

	protected String id;
	protected Brand brand;
	protected Model model;
	protected Color color;
	protected int seats;
	protected Trunk trunk;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public Trunk getTrunk() {
		return trunk;
	}
	public void setTrunk(Trunk trunk) {
		this.trunk = trunk;
	}

}