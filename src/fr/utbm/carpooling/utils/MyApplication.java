package fr.utbm.carpooling.utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.Brand;
import fr.utbm.carpooling.model.DriverCar;
import fr.utbm.carpooling.model.User;
import fr.utbm.carpooling.model.wrapper.CarsReferences;
import fr.utbm.carpooling.model.wrapper.Trunk;
import android.app.Application;
import android.util.Log;
import android.util.SparseArray;

public class MyApplication extends Application {


	@Override
	public void onCreate() {
		super.onCreate();
		
		initResources();
		initSession();
	}

	private void initResources() {
		//Initialize trunk options
		SparseArray<Trunk> trunks = new SparseArray<Trunk>();

		trunks.put(0, new Trunk(0, getString(R.string.trunk_size_0)));
		trunks.put(1, new Trunk(1, getString(R.string.trunk_size_1)));
		trunks.put(2, new Trunk(2, getString(R.string.trunk_size_2)));
		trunks.put(3, new Trunk(3, getString(R.string.trunk_size_3)));
		trunks.put(4, new Trunk(4, getString(R.string.trunk_size_4)));
		
		Resources.setTrunks(trunks);
		
		//Initialize brand list
		ArrayList<Brand> listBrands = new ArrayList<Brand>();
		JSONArray brandsArray = null;
		
		try {
			brandsArray = new JSONArray(FileManager.readFile(openFileInput(Constants.FILE_BRANDS)));
		} catch (FileNotFoundException e) {
			Log.v("FileNotFound", "FILE_BRANDS");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		if (brandsArray != null) {
			for(int i = 0; i < brandsArray.length(); ++i) {
				try {
					listBrands.add(new Brand((JSONObject) brandsArray.get(i)));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			Resources.setBrands(listBrands);
		}
	}

	private void initSession() {
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
