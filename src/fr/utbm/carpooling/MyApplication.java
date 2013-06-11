package fr.utbm.carpooling;

import java.io.FileNotFoundException;

import org.json.JSONException;
import org.json.JSONObject;

import fr.utbm.carpooling.model.User;
import android.app.Application;

public class MyApplication extends Application {

    
    @Override
    public void onCreate() {
    	super.onCreate();
    	
    	Resources.setUser(null);
    	
    	try {
			Resources.setUser(new User(new JSONObject(FileManager.readFile(openFileInput(Constants.FILE_USER_INFO)))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	
    	if (Resources.getUser() != null) {
    		
    	}
    }
}
