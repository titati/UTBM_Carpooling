package fr.utbm.carpooling.webservices;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.HttpConnection;
import fr.utbm.carpooling.JSONValidator;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.HttpConnection.HttpTaskHandler;
import fr.utbm.carpooling.HttpConnection.REQUEST_TYPE;
import fr.utbm.carpooling.TaskHandler;
import fr.utbm.carpooling.model.*;
import fr.utbm.carpooling.model.wrapper.UserInfos;


public class UserWebServices { 
	
	private static String cat = "/user/"; 
	
	public static void login(String login, String pwd, final TaskHandler<LoginResponse> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userid", login));
		params.add(new BasicNameValuePair("pwd", pwd));
		
		HttpConnection con = new HttpConnection(cat + "login", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					LoginResponse result = null;

					try {
						result = new LoginResponse(object.getJSONObject("loginresponse"));
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

	public static void getUserInfos(final TaskHandler<UserInfos> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "getUserInfos", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					UserInfos userInfos = new UserInfos();

					try {
						userInfos.setUser(new UserShort(object.getJSONObject("user")));
					} catch (JSONException e) {
						e.printStackTrace();
					}

					ArrayList<DriverCar> listCar = new ArrayList<DriverCar>();

					try {
						for(int i = 0; i < object.getJSONArray("cars").length(); ++i) {
							listCar.add(new DriverCar((JSONObject) object.getJSONArray("cars").get(i)));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					userInfos.setCars(listCar);
					handler.taskSuccessful(userInfos);
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
	
	public static void createUser(UserShort user, final TaskHandler<Boolean> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "createUser", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					boolean result = false;

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
	
	public static void updateUser(UserShort user, final TaskHandler<Boolean> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		params.add(new BasicNameValuePair("firstname", user.getFirstname()));
		params.add(new BasicNameValuePair("name", user.getLastname()));
		params.add(new BasicNameValuePair("email", user.getEmail()));
		params.add(new BasicNameValuePair("phone", user.getPhone()));
		
		HttpConnection con = new HttpConnection(cat + "createUser", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					boolean result = false;

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
	
	public static void getStatistics(final TaskHandler<Statistics> handler) {
		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("userId", Resources.getUser().getUserId()));
		params.add(new BasicNameValuePair("apiToken", Resources.getUser().getApiToken()));
		
		HttpConnection con = new HttpConnection(cat + "createUser", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					Statistics stats = null;

					try {
						stats = new Statistics(object.getJSONObject("stats"));
					} catch (JSONException e) {
						e.printStackTrace();
					}

					handler.taskSuccessful(stats);
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

