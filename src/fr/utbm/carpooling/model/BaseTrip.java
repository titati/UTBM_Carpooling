package fr.utbm.carpooling.model;

import java.io.Serializable;
import java.util.ArrayList;


public abstract class BaseTrip implements Serializable {

	protected String id;
	protected ArrayList<? extends CheckpointShort> checkpoints;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public ArrayList<? extends CheckpointShort> getCheckpoints() {
		return checkpoints;
	}
	
	public void setCheckpoints(ArrayList<? extends CheckpointShort> checkpoints) {
		this.checkpoints = checkpoints;
	}

}