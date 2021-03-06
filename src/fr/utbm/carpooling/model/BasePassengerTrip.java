package fr.utbm.carpooling.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BasePassengerTrip extends BaseTrip {

	protected int remainingSeats;
	protected UserShort driver;
	protected double rating;
	protected Car car;
	protected String description;

	public BasePassengerTrip() {
		super();
	}

	public BasePassengerTrip(JSONObject object) {
		super(object);
	}

	public int getRemainingSeats() {
		return remainingSeats;
	}

	public void setRemainingSeats(int remainingSeats) {
		this.remainingSeats = remainingSeats;
	}

	public UserShort getDriver() {
		return driver;
	}

	public void setDriver(UserShort driver) {
		this.driver = driver;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void deserializeJSON(JSONObject object) {
		super.deserializeJSON(object);

		try {
			setCar(new Car(object.getJSONObject("car")));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			setDriver(new UserShort(object.getJSONObject("driver")));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			setDescription(object.getString("description"));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			setRating(object.getDouble("rating"));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			setRemainingSeats(object.getInt("remainingseats"));
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