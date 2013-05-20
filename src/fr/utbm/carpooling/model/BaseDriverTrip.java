package fr.utbm.carpooling.model;


public abstract class BaseDriverTrip extends BaseTrip { 
	
	protected int seats;
	protected Trunk trunk;
	protected DriverCar car;
	protected String description;
	
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

}