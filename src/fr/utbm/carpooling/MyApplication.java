package fr.utbm.carpooling;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.model.DriverCar;
import fr.utbm.carpooling.model.User;
import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {


	@Override
	public void onCreate() {
		super.onCreate();
		
		User user = null;

		try {
			user = new User(new JSONObject(FileManager.readFile(openFileInput(Constants.FILE_USER_INFO))));
		} catch (FileNotFoundException e) {
			Log.v("FileNotFound", "FILE_USER_INFO");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		if (user != null) {
			Resources.initUser(user.getUserId(), user.getApiToken());
			Resources.setUser(user);
			
			ArrayList<DriverCar> listCar = new ArrayList<DriverCar>();
			JSONArray array = null;
			
			try {
				array = new JSONArray(FileManager.readFile(openFileInput(Constants.FILE_USER_CARS)));
			} catch (FileNotFoundException e) {
				Log.v("FileNotFound", "FILE_USER_CARS");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			if (array != null) {
				for(int i = 0; i < array.length(); ++i) {
					try {
						listCar.add(new DriverCar((JSONObject) array.get(i)));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

				Resources.setCars(listCar);
			}
		}

	}
}
