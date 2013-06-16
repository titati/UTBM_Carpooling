package fr.utbm.carpooling.model.wrapper;

import java.util.ArrayList;

import fr.utbm.carpooling.model.DriverCar;
import fr.utbm.carpooling.model.UserShort;

public class UserInfos {
	private UserShort mUser;
	private ArrayList<DriverCar> mCars;
	
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
}
