package fr.utbm.carpooling.model.wrapper;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.model.DriverCar;
import fr.utbm.carpooling.model.UserShort;
import fr.utbm.carpooling.utils.JSONParsableObject;

public class UserInfos extends JSONParsableObject {
	private UserShort mUser;
	private ArrayList<DriverCar> mCars;
	
	public UserInfos(JSONObject object) {
		super(object);
	}

	public UserShort getUser() {
		return mUser;
	}
	
	public void setUser(UserShort user) {
		this.mUser = user;
	}
	
	public ArrayList<DriverCar> getCars() {
		return mCars;
	}
	
	public void setCars(ArrayList<DriverCar> cars) {
		this.mCars = cars;
	}

	@Override
	protected void deserializeJSON(JSONObject object) {
		try {
			setUser(new UserShort(object.getJSONObject("user")));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		mCars = new ArrayList<DriverCar>();

		try {
			for(int i = 0; i < object.getJSONArray("cars").length(); ++i) {
				mCars.add(new DriverCar((JSONObject) object.getJSONArray("cars").get(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
