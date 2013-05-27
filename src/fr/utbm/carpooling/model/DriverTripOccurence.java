package fr.utbm.carpooling.model;

import java.io.Serializable;
import java.util.ArrayList;


public class DriverTripOccurence extends BaseTrip implements Serializable {
	
	private ArrayList<User> passengers;
	private Car car;

	public ArrayList<User> getPassengers() {
		return passengers;
	}

	public void setPassengers(ArrayList<User> passengers) {
		this.passengers = passengers;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}