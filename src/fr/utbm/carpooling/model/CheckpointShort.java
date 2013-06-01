package fr.utbm.carpooling.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.JSONParsable;

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
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss", Locale.ENGLISH);
		try {
			setDate(df.parse((object.getString("date"))));
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
