package fr.utbm.carpooling.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TripSearchResultShort extends BaseTrip {

	private int seats;
	private int remainingSeats;
	private int trunkId;
	private boolean arrivalIsEnd;

	public TripSearchResultShort() {
		super();
	}

	public TripSearchResultShort(JSONObject object) {
		super(object);
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getRemainingSeats() {
		return remainingSeats;
	}

	public void setRemainingSeats(int remainingSeats) {
		this.remainingSeats = remainingSeats;
	}

	public int getTrunkId() {
		return trunkId;
	}

	public void setTrunkId(int trunkId) {
		this.trunkId = trunkId;
	}

	public boolean getArrivalIsEnd() {
		return arrivalIsEnd;
	}

	public void setArrivalIsEnd(boolean arrivalIsEnd) {
		this.arrivalIsEnd = arrivalIsEnd;
	}

	@Override
	public void deserializeJSON(JSONObject object) {
		super.deserializeJSON(object);

		try {
			setSeats(object.getInt("seats"));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			setRemainingSeats(object.getInt("remainingseats"));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			setTrunkId(object.getInt("trunkid"));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			setArrivalIsEnd(object.getBoolean("arrivalisend"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void deserializeJSONCheckpoints(JSONArray checkpoints) {
		ArrayList<CheckpointShort> liCheckpoints = new ArrayList<CheckpointShort>();

		try {
			for(int i = 0; i < checkpoints.length(); ++i) {
				liCheckpoints.add(new CheckpointShort((JSONObject) checkpoints.get(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		setCheckpoints(liCheckpoints);
	}
}