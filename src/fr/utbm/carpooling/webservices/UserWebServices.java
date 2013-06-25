package fr.utbm.carpooling.webservices;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.model.*;
import fr.utbm.carpooling.model.wrapper.UserInfos;
import fr.utbm.carpooling.utils.HttpConnection;
import fr.utbm.carpooling.utils.JSONValidator;
import fr.utbm.carpooling.utils.Resources;
import fr.utbm.carpooling.utils.TaskHandler;
import fr.utbm.carpooling.utils.HttpConnection.HttpTaskHandler;
import fr.utbm.carpooling.utils.HttpConnection.REQUEST_TYPE;


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
						result = new LoginResponse(object.getJSONObject("data"));
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
				
					UserInfos userInfos = null;
					
					try {
						userInfos = new UserInfos(object.getJSONObject("data"));
					} catch (JSONException e) {
						e.printStackTrace();
					}

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
						result = object.getBoolean("data");
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
		
		HttpConnection con = new HttpConnection(cat + "updateUser", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					boolean result = false;

					try {
						result = object.getBoolean("data");
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
		
		HttpConnection con = new HttpConnection(cat + "getStatistics", params, REQUEST_TYPE.POST, new HttpTaskHandler() {
			
			@Override
			public void taskSuccessful(String jsonString) {
				JSONValidator jv = new JSONValidator(jsonString);
				
				if (jv.isValid()) {
					JSONObject object = jv.getObject();
				
					Statistics stats = null;

					try {
						stats = new Statistics(object.getJSONObject("data"));
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

