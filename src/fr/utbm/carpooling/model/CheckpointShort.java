package fr.utbm.carpooling.model;

import java.text.ParseException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.utils.JSONParsableObject;
import fr.utbm.carpooling.webservices.ResourcesWebServices;

/**
 * public class<br>
 * <strong>CheckpointShort</strong><br>
 * extends {@link JSONParsableObject}<br><br>
 * Known direct subclasses<br>
 * {@link Checkpoint}
 */
public class CheckpointShort extends JSONParsableObject {
	
	/** Indicate which checkpoint is the current one. The numbering begins at 1. */
	protected int numCheckpoint;
	/** Indicate when the car will be at the checkpoint */
	protected Date date;
	
	/**
	 * <strong>public CheckpointShort({@link JSONObject} object)</strong><br><br>
	 * Create a CheckpointShort from a JSONObject<br>
	 * @param object A JSONObject representing a CheckpointShort
	 */
	public CheckpointShort(JSONObject object) {
		super(object);
	}
	
	/** Default constructor */
	public CheckpointShort() {}
	
	/**
	 * <strong>public int getNumCheckpoint()</strong><br><br>
	 * Return the number of the checkpoint<br><br>
	 * @return <li>The number of the checkpoint</li>
	 */
	public int getNumCheckpoint() {
		return numCheckpoint;
	}
	
	/**
	 * <strong>public void setNumCheckpoint(int numCheckpoint)</strong><br><br>
	 * Set the checkpoint number.
	 * @param numCheckpoint Checkpoint number. Starts at 1.
	 */
	public void setNumCheckpoint(int numCheckpoint) {
		this.numCheckpoint = numCheckpoint;
	}
	
	/**
	 * <strong>public {@link Date} getDate()</strong><br><br>
	 * Return the date when the car will be at this checkpoint.
	 * @return <li>The date when the car will be at this checkpoint</li>
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * <strong>public void setDate({@link Date} date)</strong><br><br>
	 * Set the date when the car will be at this checkpoint.
	 * @param date The date when the car will be at this checkpoint
	 */
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
