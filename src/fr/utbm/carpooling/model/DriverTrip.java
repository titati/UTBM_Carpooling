package fr.utbm.carpooling.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DriverTrip extends BaseTrip {

	protected DriverCar mCar;
	protected String mDescription;
	private Repeat mRepeat;

    public DriverTrip() {
        super();
    }

	public DriverTrip(JSONObject object) {
		super(object);
	}

	public DriverCar getCar() {
		return mCar;
	}

	public void setCar(DriverCar car) {
		this.mCar = car;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String description) {
		this.mDescription = description;
	}


	public Repeat getRepeat() {
		return mRepeat;
	}

	public void setRepeat(Repeat repeat) {
		this.mRepeat = repeat;
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