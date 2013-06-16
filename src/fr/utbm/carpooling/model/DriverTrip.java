package fr.utbm.carpooling.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DriverTrip extends BaseTrip {

	protected DriverCar car;
	protected String description;
	private Repeat repeat;

	public DriverTrip(JSONObject object) {
		super(object);
	}

	public DriverCar getCar() {
		return car;
	}

	public void setCar(DriverCar car) {
		this.car = car;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Repeat getRepeat() {
		return repeat;
	}

	public void setRepeat(Repeat repeat) {
		this.repeat = repeat;
	}

	@Override
	public void deserializeJSON(JSONObject object) {
		super.deserializeJSON(object);

		try {
			setCar(new DriverCar(object.getJSONObject("car")));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			setDescription(object.getString("description"));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			setRepeat(new Repeat(object.getJSONObject("repeat")));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void deserializeJSONCheckpoints(JSONArray checkpoints) {
		ArrayList<Checkpoint> liCheckpoints = new ArrayList<Checkpoint>();

		try {
			for(int i = 0; i < checkpoints.length(); ++i) {
				liCheckpoints.add(new Checkpoint((JSONObject) checkpoints.get(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		setCheckpoints(liCheckpoints);
	}
}