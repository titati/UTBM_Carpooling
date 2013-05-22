package fr.utbm.carpooling.model;

import java.io.Serializable;
import java.util.List;


public class DriverTripOccurence extends BaseDriverTrip implements Serializable {
	
	private List<User> passengers;

	public List<User> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<User> passengers) {
		this.passengers = passengers;
	}

}