package fr.utbm.carpooling.model;

import java.util.ArrayList;

public class TripSearch {

	private ArrayList<Checkpoint> checkpoints;
	private int minTrunk;
	private int minSeats;
	
	public ArrayList<Checkpoint> getCheckpoints() {
		return checkpoints;
	}
	
	public void setCheckpoints(ArrayList<Checkpoint> checkpoints) {
		this.checkpoints = checkpoints;
	}
	
	public int getMinTrunk() {
		return minTrunk;
	}
	
	public void setMinTrunk(int minTrunk) {
		this.minTrunk = minTrunk;
	}
	
	public int getMinSeats() {
		return minSeats;
	}
	
	public void setMinSeats(int minSeats) {
		this.minSeats = minSeats;
	}

}