package fr.utbm.carpooling.model;

import java.io.Serializable;

public class Checkpoint extends CheckpointShort implements Serializable {

	private int siteId;
	
	public int getSiteId() {
		return siteId;
	}
	
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
}