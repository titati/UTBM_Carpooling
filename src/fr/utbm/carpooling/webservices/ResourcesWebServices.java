package fr.utbm.carpooling.webservices;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import fr.utbm.carpooling.HttpConnection;
import fr.utbm.carpooling.JSONValidator;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.HttpConnection.HttpTaskHandler;
import fr.utbm.carpooling.HttpConnection.REQUEST_TYPE;
import fr.utbm.carpooling.TaskHandler;
import fr.utbm.carpooling.model.*;


public class ResourcesWebServices {
	
	@SuppressLint("SimpleDateFormat")
	private static SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss");
	private static String cat = "/ressources/";
	
	public static SimpleDateFormat getStandardDateFormat() {
		return df;
	}
	
	public static void getColors(final TaskHandler<ArrayList<Color>> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "getColors", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					ArrayList<Color> listColor = new ArrayList<Color>();

					try {
						for(int i = 0; i < object.getJSONArray("colors").length(); ++i) {
							listColor.add(new Color((JSONObject) object.getJSONArray("colors").get(i)));
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
	
	public static void getTrunks(final TaskHandler<ArrayList<Trunk>> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "getTrunks", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					ArrayList<Trunk> listTrunk = new ArrayList<Trunk>();

					try {
						for(int i = 0; i < object.getJSONArray("trunks").length(); ++i) {
							listTrunk.add(new Trunk((JSONObject) object.getJSONArray("trunks").get(i)));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

					handler.taskSuccessful(listTrunk);
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
	
	public static void getBrands(final TaskHandler<ArrayList<Brand>> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "getBrands", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					ArrayList<Brand> listBrand = new ArrayList<Brand>();

					try {
						for(int i = 0; i < object.getJSONArray("brands").length(); ++i) {
							listBrand.add(new Brand((JSONObject) object.getJSONArray("brands").get(i)));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

					handler.taskSuccessful(listBrand);
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
	
	public static void getModels(int brandId, final TaskHandler<ArrayList<Model>> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "getModels", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					ArrayList<Model> listModel = new ArrayList<Model>();

					try {
						for(int i = 0; i < object.getJSONArray("models").length(); ++i) {
							listModel.add(new Model((JSONObject) object.getJSONArray("models").get(i)));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

					handler.taskSuccessful(listModel);
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
	
	public static void getSitesShort(final TaskHandler<ArrayList<SiteShort>> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "getSitesShort", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					ArrayList<SiteShort> listModel = new ArrayList<SiteShort>();

					try {
						for(int i = 0; i < object.getJSONArray("siteshorts").length(); ++i) {
							listModel.add(new SiteShort((JSONObject) object.getJSONArray("siteshorts").get(i)));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

					handler.taskSuccessful(listModel);
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

