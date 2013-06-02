package fr.utbm.carpooling.model;

import java.text.ParseException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsable;
import fr.utbm.carpooling.webservices.ResourcesWebServices;

public class CheckpointShort extends JSONParsable {
	
	protected int numCheckpoint;
	protected Date date;
	
	public CheckpointShort(JSONObject object) {
		super(object);
	}
	
	public CheckpointShort() {}
	
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
	
	@Override
	protected void deserializeJSON(JSONObject object) {
		try {
			setNumCheckpoint(object.getInt("numcheckpoint"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setDate(ResourcesWebServices.getStandardDateFormat().parse((object.getString("date"))));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
