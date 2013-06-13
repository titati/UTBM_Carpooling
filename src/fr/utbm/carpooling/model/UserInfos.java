package fr.utbm.carpooling.model;

import java.util.ArrayList;

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
