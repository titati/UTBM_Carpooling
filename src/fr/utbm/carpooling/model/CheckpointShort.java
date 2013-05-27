package fr.utbm.carpooling.model;

import java.util.Date;

public class CheckpointShort {

	protected int numCheckpoint;
	protected Date date;
	
	public int getNumCheckpoint() {
		return numCheckpoint;
	}
	public void setNumCheckpoint(int numCheckpoint) {
		this.numCheckpoint = numCheckpoint;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
