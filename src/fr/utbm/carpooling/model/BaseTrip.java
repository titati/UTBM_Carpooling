package fr.utbm.carpooling.model;

import java.util.List;


public abstract class BaseTrip {

	protected String id;
	protected List<Checkpoint> checkpoints;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Checkpoint> getCheckpoints() {
		return checkpoints;
	}
	public void setCheckpoints(List<Checkpoint> checkpoints) {
		this.checkpoints = checkpoints;
	}

}