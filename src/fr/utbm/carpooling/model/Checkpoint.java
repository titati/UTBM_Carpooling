package fr.utbm.carpooling.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Checkpoint extends CheckpointShort {

	private int siteId;
	
	public int getSiteId() {
		return siteId;
	}
	
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	
	@Override
	protected void deserializeJSON(JSONObject object) {
		super.deserializeJSON(object);
		
		try {
			setSiteId(object.getInt("siteId"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}