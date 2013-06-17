package fr.utbm.carpooling.webservices;

import java.util.ArrayList;
import java.util.Date;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.HttpConnection;
import fr.utbm.carpooling.HttpConnection.HttpTaskHandler;
import fr.utbm.carpooling.JSONValidator;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.TaskHandler;
import fr.utbm.carpooling.HttpConnection.REQUEST_TYPE;
import fr.utbm.carpooling.model.*;
import fr.utbm.carpooling.model.wrapper.TripSearch;


public class PassengerWebServices {
	
	private static String cat = "/passenger/";

	public static void getNextTripsShort(final TaskHandler<ArrayList<PassengerTripShort>> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "getNextTripsShort", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					ArrayList<PassengerTripShort> listTrip = new ArrayList<PassengerTripShort>();

					try {
						for(int i = 0; i < object.getJSONArray("passengertripshorts").length(); ++i) {
							listTrip.add(new PassengerTripShort((JSONObject) object.getJSONArray("passengertripshorts").get(i)));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

					handler.taskSuccessful(listTrip);
				} else {
					handler.taskFailed();
				}
			}
			
			@Override
			public void taskFailed() {
				handler.taskFailed();
			}
		});
		
		con.execute("");
	}

	public static void getPreviousTripsShort(final TaskHandler<ArrayList<PassengerTripShort>> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "getPreviousTripsShort", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					ArrayList<PassengerTripShort> listTrip = new ArrayList<PassengerTripShort>();

					try {
						for(int i = 0; i < object.getJSONArray("passengertripshorts").length(); ++i) {
							listTrip.add(new PassengerTripShort((JSONObject) object.getJSONArray("passengertripshorts").get(i)));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

					handler.taskSuccessful(listTrip);
				} else {
					handler.taskFailed();
				}
			}
			
			@Override
			public void taskFailed() {
				handler.taskFailed();
			}
		});
		
		con.execute("");
	}

	public static void getAlerts(final TaskHandler<ArrayList<Alert>> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "getAlerts", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					ArrayList<Alert> listAlert = new ArrayList<Alert>();
					
					try {
						for(int i = 0; i < object.getJSONArray("alerts").length(); ++i) {
							listAlert.add(new Alert((JSONObject) object.getJSONArray("alerts").get(i)));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					handler.taskSuccessful(listAlert);
				} else {
					handler.taskFailed();
				}
			}
			
			@Override
			public void taskFailed() {
				handler.taskFailed();
			}
		});
		
		con.execute("");
	}

	public static void deleteAlert(int alertId, final TaskHandler<Boolean> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		params.add(new BasicNameValuePair("alertId", String.valueOf(alertId)));
		
		HttpConnection con = new HttpConnection(cat + "deleteAlert", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
					Boolean result = false;
					
					try {
						result = object.getBoolean("result");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					handler.taskSuccessful(result);
				} else {
					handler.taskFailed();
				}
			}
			
			@Override
			public void taskFailed() {
				handler.taskFailed();
			}
		});
		
		con.execute("");
	}

	public static void createAlert(TripSearch tripSearch, final TaskHandler<Boolean> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		params.add(new BasicNameValuePair("startSiteId", String.valueOf(tripSearch.getFromSiteId())));
		params.add(new BasicNameValuePair("endSiteId", String.valueOf(tripSearch.getToSiteId())));
		params.add(new BasicNameValuePair("arrivalDate", ResourcesWebServices.getStandardDateFormat().format(tripSearch.getArrivalDate())));
		
		HttpConnection con = new HttpConnection(cat + "createAlert", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					Boolean result = false;
					
					try {
						result = object.getBoolean("result");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					handler.taskSuccessful(result);
				} else {
					handler.taskFailed();
				}
			}
			
			@Override
			public void taskFailed() {
				handler.taskFailed();
			}
		});
		
		con.execute("");
	}

	public static void searchTrips(TripSearch tripSearch, final TaskHandler<ArrayList<TripSearchResultShort>> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		params.addAll(tripSearch.toParam());
		 
		HttpConnection con = new HttpConnection(cat + "searchTrips", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					ArrayList<TripSearchResultShort> listTrip = new ArrayList<TripSearchResultShort>();
					
					try {
						for(int i = 0; i < object.getJSONArray("trips").length(); ++i) {
							listTrip.add(new TripSearchResultShort((JSONObject) object.getJSONArray("trips").get(i)));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					handler.taskSuccessful(listTrip);
				} else {
					handler.taskFailed();
				}
			}
			
			@Override
			public void taskFailed() {
				handler.taskFailed();
			}
		});
		
		con.execute("");
	}

	public static void getTripSearchResult(int abstractTripId, Date tripId, final TaskHandler<TripSearchResult> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		params.add(new BasicNameValuePair("abstractTripId", String.valueOf(abstractTripId)));
		params.add(new BasicNameValuePair("tripId", ResourcesWebServices.getStandardDateFormat().format(tripId)));
		
		HttpConnection con = new HttpConnection(cat + "getTripSearchResult", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					TripSearchResult searchResult = null;
					
					try {
						searchResult = new TripSearchResult(object.getJSONObject("result"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					handler.taskSuccessful(searchResult);
				} else {
					handler.taskFailed();
				}
			}
			
			@Override
			public void taskFailed() {
				handler.taskFailed();
			}
		});
		
		con.execute("");
	}
	
	public static void suscribe(int abstractTripId, Date tripId, final TaskHandler<Boolean> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		params.add(new BasicNameValuePair("abstractTripId", String.valueOf(abstractTripId)));
		params.add(new BasicNameValuePair("tripId", ResourcesWebServices.getStandardDateFormat().format(tripId)));
		
		HttpConnection con = new HttpConnection(cat + "suscribe", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					Boolean result = false;
					
					try {
						result = object.getBoolean("result");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					handler.taskSuccessful(result);
				} else {
					handler.taskFailed();
				}
			}
			
			@Override
			public void taskFailed() {
				handler.taskFailed();
			}
		});
		
		con.execute("");
	}
	
	public static void unsuscribe(int abstractTripId, Date tripId, final TaskHandler<Boolean> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		params.add(new BasicNameValuePair("abstractTripId", String.valueOf(abstractTripId)));
		params.add(new BasicNameValuePair("tripId", ResourcesWebServices.getStandardDateFormat().format(tripId)));
		
		HttpConnection con = new HttpConnection(cat + "unsuscribe", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					Boolean result = false;
					
					try {
						result = object.getBoolean("result");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					handler.taskSuccessful(result);
				} else {
					handler.taskFailed();
				}
			}
			
			@Override
			public void taskFailed() {
				handler.taskFailed();
			}
		});
		
		con.execute("");
	}
	
	public static void setTripFeedback(int abstractTripId, Date tripId, TripFeedback feedback, final TaskHandler<Boolean> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		params.add(new BasicNameValuePair("abstractTripId", String.valueOf(abstractTripId)));
		params.add(new BasicNameValuePair("tripId", ResourcesWebServices.getStandardDateFormat().format(tripId)));
		params.add(new BasicNameValuePair("feedbackRating", String.valueOf(feedback.getRating())));
		params.add(new BasicNameValuePair("feedbackComment", feedback.getComment()));
		
		HttpConnection con = new HttpConnection(cat + "setTripFeedback", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					Boolean result = false;
					
					try {
						result = object.getBoolean("result");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					handler.taskSuccessful(result);
				} else {
					handler.taskFailed();
				}
			}
			
			@Override
			public void taskFailed() {
				handler.taskFailed();
			}
		});
		
		con.execute("");
	}
	
	public static void getPassengerTrip(int abstractTripId, Date tripId, final TaskHandler<PassengerTrip> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		params.add(new BasicNameValuePair("abstractTripId", String.valueOf(abstractTripId)));
		params.add(new BasicNameValuePair("tripId", ResourcesWebServices.getStandardDateFormat().format(tripId)));
		
		HttpConnection con = new HttpConnection(cat + "getPassengerTrip", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					PassengerTrip trip = null;
					
					try {
						trip = new PassengerTrip(object.getJSONObject("trip"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					handler.taskSuccessful(trip);
				} else {
					handler.taskFailed();
				}
			}
			
			@Override
			public void taskFailed() {
				handler.taskFailed();
			}
		});
		
		con.execute("");
	}
	
}


