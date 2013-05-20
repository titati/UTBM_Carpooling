package fr.utbm.carpooling.model;

import java.util.List;


public class DriverTripOccurence extends BaseDriverTrip { 
	
	private List<User> passengers;

	public List<User> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<User> passengers) {
		this.passengers = passengers;
	}

}