package fr.utbm.carpooling.model;


public class TripSearchResultShort extends BaseTrip { 
	
	private int seats;
	private int remainingSeats;
	private Trunk trunk;
	
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public int getRemainingSeats() {
		return remainingSeats;
	}
	public void setRemainingSeats(int remainingSeats) {
		this.remainingSeats = remainingSeats;
	}
	public Trunk getTrunk() {
		return trunk;
	}
	public void setTrunk(Trunk trunk) {
		this.trunk = trunk;
	}

}