package fr.utbm.carpooling.model;

import java.io.Serializable;
import java.util.List;


public class TripSearch implements Serializable {

	private List<Checkpoint> checkpoints;
	private Trunk minTrunk;
	private int minSeats;
	
	public List<Checkpoint> getCheckpoints() {
		return checkpoints;
	}
	public void setCheckpoints(List<Checkpoint> checkpoints) {
		this.checkpoints = checkpoints;
	}
	public Trunk getMinTrunk() {
		return minTrunk;
	}
	public void setMinTrunk(Trunk minTrunk) {
		this.minTrunk = minTrunk;
	}
	public int getMinSeats() {
		return minSeats;
	}
	public void setMinSeats(int minSeats) {
		this.minSeats = minSeats;
	}

}