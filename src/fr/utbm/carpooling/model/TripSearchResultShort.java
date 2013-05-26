package fr.utbm.carpooling.model;

import java.io.Serializable;

public class TripSearchResultShort extends BaseTrip implements Serializable {
	
	private int seats;
	private int remainingSeats;
	private int trunkId;
	private boolean arrivalIsEnd;
	
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
	
	public int getTrunkId() {
		return trunkId;
	}
	
	public void setTrunkId(int trunkId) {
		this.trunkId = trunkId;
	}

	public boolean getArrivalIsEnd() {
		return arrivalIsEnd;
	}
	
	public void setArrivalIsEnd(boolean arrivalIsEnd) {
		this.arrivalIsEnd = arrivalIsEnd;
	}

}