package fr.utbm.carpooling.model;

import java.util.List;


public class Statistics { 

	private int driverTrips;
	private int passengerTrips;
	private int peopleCarried;
	private int rating;
	private List<String> comments;
	
	public int getDriverTrips() {
		return driverTrips;
	}
	public void setDriverTrips(int driverTrips) {
		this.driverTrips = driverTrips;
	}
	public int getPassengerTrips() {
		return passengerTrips;
	}
	public void setPassengerTrips(int passengerTrips) {
		this.passengerTrips = passengerTrips;
	}
	public int getPeopleCarried() {
		return peopleCarried;
	}
	public void setPeopleCarried(int peopleCarried) {
		this.peopleCarried = peopleCarried;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}

}