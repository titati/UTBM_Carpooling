package fr.utbm.carpooling.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.utils.JSONParsableObject;
import fr.utbm.carpooling.webservices.ResourcesWebServices;

public class Alert extends JSONParsableObject {
	
	private int alertId;
	private int fromSiteId;
	private int toSiteId;
	private Date arrivalDate;
	
	public Alert() {}

	public Alert(JSONObject object) {
		super(object);
	}
	
	public int getAlertId() {
		return alertId;
	}

	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

	public int getFromSiteId() {
		return fromSiteId;
	}

	public void setFromSiteId(int fromSiteId) {
		this.fromSiteId = fromSiteId;
	}

	public int getToSiteId() {
		return toSiteId;
	}

	public void setToSiteId(int toSiteId) {
		this.toSiteId = toSiteId;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public ArrayList<Checkpoint> getCheckpoints() {
		ArrayList<Checkpoint> checkpoints = new ArrayList<Checkpoint>();

		Checkpoint from = new Checkpoint();
		from.setSiteId(fromSiteId);
		from.setNumCheckpoint(1);
		
		Checkpoint to = new Checkpoint();
		to.setSiteId(toSiteId);
		to.setNumCheckpoint(2);
		
		checkpoints.add(from);
		checkpoints.add(to);
		
		return checkpoints;
	}

	@Override
	protected void deserializeJSON(JSONObject object) {
		try {
			setAlertId(object.getInt("alertid"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setFromSiteId(object.getInt("fromsiteid"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setToSiteId(object.getInt("tositeid"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			setArrivalDate(ResourcesWebServices.getStandardDateFormat().parse(object.getString("arrivaldate")));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}