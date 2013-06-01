package fr.utbm.carpooling.webservices;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.HttpConnection;
import fr.utbm.carpooling.HttpConnection.HttpTaskHandler;
import fr.utbm.carpooling.TaskHandler;
import fr.utbm.carpooling.HttpConnection.REQUEST_TYPE;
import fr.utbm.carpooling.model.*;


public class PassengerWebServices { 

	public static void getNextTripsShort(int userId, final TaskHandler<ArrayList<PassengerTripShort>> handler) {
   		
		HttpConnection con = new HttpConnection("/getNextTripsShort", null, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONObject object = null;
				
				try {
					object = new JSONObject(jsonString);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				ArrayList<PassengerTripShort> listTrip = new ArrayList<PassengerTripShort>();
				
				try {
					for(int i = 0; i < object.getJSONArray("passengertripshorts").length(); ++i) {
						listTrip.add(new PassengerTripShort((JSONObject) object.getJSONArray("passengertripshorts").get(i)));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				handler.taskSuccessful(listTrip);
			}
			
			@Override
			public void taskFailed() {
				handler.taskFailed();
			}
		});
		
		con.execute("");
	}

	public static void getPreviousTripsShort(User user) {
   		// TODO: implement
	}

	public static void getAlerts(User user) {
   		// TODO: implement
	}

	public static void deleteAlert(User user, Alert alert) {
   		// TODO: implement
	}

	public static void createAlert(User user, TripSearch tripSearch) {
   		// TODO: implement
	}

	public static void searchTrips(TripSearch tripSearch) {
   		// TODO: implement
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
		 
	public static void getCheckpointPosition(PassengerTrip trip, Checkpoint checkpoint) {
   		// TODO: implement
	}
	
	public static void getPassengerTrip(PassengerTripShort trip) {
   		// TODO: implement
	}
	
}


