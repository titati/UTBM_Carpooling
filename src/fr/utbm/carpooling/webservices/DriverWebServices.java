package fr.utbm.carpooling.webservices;

import java.util.ArrayList;
import java.util.Date;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import fr.utbm.carpooling.model.*;
import fr.utbm.carpooling.utils.HttpConnection;
import fr.utbm.carpooling.utils.JSONValidator;
import fr.utbm.carpooling.utils.Resources;
import fr.utbm.carpooling.utils.TaskHandler;
import fr.utbm.carpooling.utils.HttpConnection.HttpTaskHandler;
import fr.utbm.carpooling.utils.HttpConnection.REQUEST_TYPE;


public class DriverWebServices { 

	private static String cat = "/driver/";
	
	public static void getPreviousTripsShort(final TaskHandler<ArrayList<DriverTripOccurenceShort>> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "getPreviousTripsShort", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
					ArrayList<DriverTripOccurenceShort> listTrip = new ArrayList<DriverTripOccurenceShort>();
					
					try {
						for(int i = 0; i < object.getJSONArray("drivertripshorts").length(); ++i) {
							listTrip.add(new DriverTripOccurenceShort((JSONObject) object.getJSONArray("drivertripshorts").get(i)));
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
	
	public static void getNextTripsShort(final TaskHandler<ArrayList<DriverTripOccurenceShort>> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "getNextTripsShort", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					Log.i("JSONValidator", "valid");
					JSONObject object = jv.getObject();
					ArrayList<DriverTripOccurenceShort> listTrip = new ArrayList<DriverTripOccurenceShort>();

					try {
						for(int i = 0; i < object.getJSONArray("drivertripshorts").length(); ++i) {
							listTrip.add(new DriverTripOccurenceShort((JSONObject) object.getJSONArray("drivertripshorts").get(i)));
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
	
	public static void getCars(final TaskHandler<ArrayList<DriverCar>> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "getCars", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
					ArrayList<DriverCar> listCar = new ArrayList<DriverCar>();
					
					try {
						for(int i = 0; i < object.getJSONArray("cars").length(); ++i) {
							listCar.add(new DriverCar((JSONObject) object.getJSONArray("cars").get(i)));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					handler.taskSuccessful(listCar);
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
	
	public static void createCar(DriverCar car, final TaskHandler<Boolean> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "createCar", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
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
	
	public static void updateCar(DriverCar car, final TaskHandler<Boolean> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "updateCar", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
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
	
	public static void deleteCar(int id, final TaskHandler<Boolean> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "deleteCar", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
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

	public static void setDefaultCar(int id, TaskHandler<Boolean> mSetDefaultTask) {
		// TODO Auto-generated method stub
		
	}
	
	public static void updateCarPosition(String carId, GpsPosition position, final TaskHandler<Boolean> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "updateCarPosition", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
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
	
	public static void createTrip(DriverTrip trip, final TaskHandler<Boolean> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "createTrip", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
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
	
	public static void updateTrip(DriverTrip trip, final TaskHandler<Boolean> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "updateTrip", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
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
	
	public static void updateTripOccurence(DriverTripOccurence trip, final TaskHandler<Boolean> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "updateTripOccurence", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
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
	
	public static void deleteTrip(int abstractTripId, final TaskHandler<Boolean> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "deleteTrip", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
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
	
	public static void deleteTripOccurence(int abstractTripId, Date tripId, final TaskHandler<Boolean> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "deleteTripOccurence", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
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
	
	public static void getTrip(int abstractTripId, final TaskHandler<DriverTrip> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "getTrip", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
					DriverTrip trip = null;
					
					try {
						trip = new DriverTrip(object.getJSONObject("trip"));
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
	
	public static void getTripOccurence(int abstractTripId, Date tripId, final TaskHandler<DriverTripOccurence> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		params.add(new BasicNameValuePair("abstractTripId", String.valueOf(abstractTripId)));
		params.add(new BasicNameValuePair("tripId", ResourcesWebServices.getStandardDateFormat().format(tripId)));
		
		HttpConnection con = new HttpConnection(cat + "getTripOccurence", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
					DriverTripOccurence tripoccurence = null;
					
					try {
						tripoccurence = new DriverTripOccurence(object.getJSONObject("tripoccurence"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					handler.taskSuccessful(tripoccurence);
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


