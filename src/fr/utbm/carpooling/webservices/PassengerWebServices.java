package fr.utbm.carpooling.webservices;

import java.util.*;
import fr.utbm.carpooling.model.*;


public class PassengerWebServices { 

	public static List<PassengerTripShort> getNextTripsShort(User user) {
   		// TODO: implement
		return null;
	}

	public static List<PassengerTripShort> getPreviousTripsShort(User user) {
   		// TODO: implement
		return null;
	}

	public static List<Alert> getAlerts(User user) {
   		// TODO: implement
		return null;
	}

	public static void deleteAlert(User user, Alert alert) {
   		// TODO: implement
	}

	public static void createAlert(User user, TripSearch tripSearch) {
   		// TODO: implement
	}

	public static List<TripSearchResultShort> searchTrips(TripSearch tripSearch) {
   		// TODO: implement
		return null;
	}

	public static TripSearchResult getTripSearchResult(TripSearchResultShort trip) {
   		// TODO: implement
		return null;
	}
	
	public static void suscribe(User user, TripSearchResult trip) {
	   	// TODO: implement
	}
	
	public static void unsuscribe(User user, PassengerTrip trip) {
	   	// TODO: implement
	}
	
	public static void setTripFeedback(User user, PassengerTrip trip, TripFeedback feedback) {
   		// TODO: implement
	}
		 
	public static GpsPosition getCheckpointPosition(PassengerTrip trip, Checkpoint checkpoint) {
   		// TODO: implement
		return null;
	}
	
	public static PassengerTrip getPassengerTrip(PassengerTripShort trip) {
   		// TODO: implement
		return null;
	}
	
}


