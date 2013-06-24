package fr.utbm.carpooling.view;

import java.util.ArrayList;

import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.Color;
import fr.utbm.carpooling.model.Site;
import fr.utbm.carpooling.model.wrapper.CarsReferences;
import fr.utbm.carpooling.utils.Resources;
import fr.utbm.carpooling.utils.TaskHandler;
import fr.utbm.carpooling.webservices.ResourcesWebServices;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class LauncherActivity extends Activity {

	private boolean mCarsRefsDL = false;
	private boolean mColorsDL = false;
	private boolean mSitesDL = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_launcher);

        if (Resources.getSites().isEmpty()) {
        	ResourcesWebServices.getSites(new TaskHandler<ArrayList<Site>>() {

				@Override
				public void taskSuccessful(ArrayList<Site> object) {
					Resources.setSites(object);
					Resources.saveSites(getApplicationContext());
					
					mSitesDL = true;
					checkResources();
				}

				@Override
				public void taskFailed() {
					Toast.makeText(getApplicationContext(), R.string.error_fetching_data, Toast.LENGTH_LONG).show();
				}
			});
        }
        
        if (Resources.getBrands().isEmpty()) {
        	ResourcesWebServices.getCarsReferences(new TaskHandler<CarsReferences>() {
				
				@Override
				public void taskSuccessful(CarsReferences object) {
					Resources.setBrands(object.getBrands());
					Resources.setModels(object.getModels());
					Resources.saveBrands(getApplicationContext());
					Resources.saveModels(getApplicationContext());
					
					mCarsRefsDL = true;
					checkResources();
				}
				
				@Override
				public void taskFailed() {
					Toast.makeText(getApplicationContext(), R.string.error_fetching_data, Toast.LENGTH_LONG).show();
				}
			});
        }
        
        if (Resources.getBrands().isEmpty()) {
        	ResourcesWebServices.getColors(new TaskHandler<ArrayList<Color>>() {
				
				@Override
				public void taskSuccessful(ArrayList<Color> object) {
			        Resources.setColors(object);
					Resources.saveColors(getApplicationContext());
					
					mColorsDL = true;
					checkResources();
				}
				
				@Override
				public void taskFailed() {
					Toast.makeText(getApplicationContext(), R.string.error_fetching_data, Toast.LENGTH_LONG).show();
				}
			});
        }
        
        
        checkResources();
	}
	
	protected void checkResources() {
		if (mCarsRefsDL && mColorsDL && mSitesDL) {
			checkLoggedIn();
		}
	}

	private void checkLoggedIn() {
		if (Resources.getUser() == null) {
			Intent intent = new Intent(LauncherActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(LauncherActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
	}
}
