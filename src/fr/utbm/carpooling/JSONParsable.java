package fr.utbm.carpooling;

import org.json.JSONObject;

import fr.utbm.carpooling.model.*;

/**
 * public class<br>
 * <strong>JSONParsable</strong><br><br>
 * Every class which extends from this one will be constructible from a {@link JSONObject}
 * <br><br>
 * Known direct subclasses<br>
 * {@link Alert}, {@link BaseTrip}, {@link Brand}, {@link Car}, {@link CheckpointShort}, {@link Color}, {@link GpsPosition},
 * {@link LoginResponse}, {@link Model}, {@link Repeat}, {@link Statistics}, {@link Site}, {@link TripFeedback}, {@link UserShort}
 * <br><br>
 * Known indirect subclasses<br>
 * {@link DriverCar}, {@link User}, {@link Checkpoint}, {@link BasePassengerTrip}, {@link DriverTrip}, {@link TripSearchResult},
 * {@link PassengerTrip}, {@link PassengerTripShort}, {@link TripSearchResultShort}, {@link DriverTripOccurence}, {@link DriverTripOccurenceShort}
 */
public abstract class JSONParsable {
	/**
	 * <strong>public JSONParsable({@link JSONObject} object)</strong><br><br>
	 * Define how to construct the object from a JSONObject.
	 * @param object A JSONObject representing some data
	 */
	public JSONParsable(JSONObject object) {
		deserializeJSON(object);
	}
	
	/**	Need a default blank constructor to create object from nothing */
	public JSONParsable() {}
	
	/**
	 * <strong>protected abstract void deserializeJSON({@link JSONObject} object)</strong><br><br>
	 * Define how to deserialize the object from a JSONObject.
	 * @param object The JSONObject to deserialize
	 */
	protected abstract void deserializeJSON(JSONObject object);
}
