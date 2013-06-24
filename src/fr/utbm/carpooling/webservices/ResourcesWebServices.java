package fr.utbm.carpooling.webservices;

import java.text.SimpleDateFormat;
import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import fr.utbm.carpooling.model.*;
import fr.utbm.carpooling.model.wrapper.CarsReferences;
import fr.utbm.carpooling.utils.HttpConnection;
import fr.utbm.carpooling.utils.JSONValidator;
import fr.utbm.carpooling.utils.TaskHandler;
import fr.utbm.carpooling.utils.HttpConnection.HttpTaskHandler;
import fr.utbm.carpooling.utils.HttpConnection.REQUEST_TYPE;


public class ResourcesWebServices {
	
	@SuppressLint("SimpleDateFormat")
	private final static SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss");
	private static String cat = "/resources/";
	
	public static SimpleDateFormat getStandardDateFormat() {
		return df;
	}
	
	public static void getColors(final TaskHandler<ArrayList<Color>> handler) {
		
		HttpConnection con = new HttpConnection(cat + "getColors", null, REQUEST_TYPE.GET, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					ArrayList<Color> listColor = new ArrayList<Color>();

					try {
						for(int i = 0; i < object.getJSONArray("data").length(); ++i) {
							listColor.add(new Color((JSONObject) object.getJSONArray("data").get(i)));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

					handler.taskSuccessful(listColor);
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
	
	public static void getCarsReferences(final TaskHandler<CarsReferences> handler) {
		
		HttpConnection con = new HttpConnection(cat + "getCarsReferences", null, REQUEST_TYPE.GET, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					CarsReferences refs = null;

					try {
						refs = new CarsReferences((JSONObject) object.getJSONObject("data"));
					} catch (JSONException e) {
						e.printStackTrace();
					}

					handler.taskSuccessful(refs);
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
	
	public static void getSites(final TaskHandler<ArrayList<Site>> handler) {
		
		HttpConnection con = new HttpConnection(cat + "getSites", null, REQUEST_TYPE.GET, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					ArrayList<Site> listSites = new ArrayList<Site>();

					try {
						for(int i = 0; i < object.getJSONArray("data").length(); ++i) {
							listSites.add(new Site((JSONObject) object.getJSONArray("data").get(i)));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

					handler.taskSuccessful(listSites);
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

