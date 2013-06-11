package fr.utbm.carpooling;

import java.io.FileNotFoundException;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.model.User;
import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

    
    @Override
    public void onCreate() {
    	super.onCreate();
    	
    	Resources.setUser(null);
		Log.i("myapp", "used");
    	
    	try {
			Resources.setUser(new User(new JSONObject(FileManager.readFile(openFileInput("userInfos")))));
			Log.i("user", "loaded");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	
    	if (Resources.getUser() != null) {
    		
    	}
    }
}
