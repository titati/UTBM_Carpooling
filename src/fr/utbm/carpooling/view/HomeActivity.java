package fr.utbm.carpooling.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import fr.utbm.carpooling.R;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		findViewById(R.id.home_button_find_trip).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						
					}
				});
		
		findViewById(R.id.home_button_create_trip).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						
					}
				});
		
		findViewById(R.id.home_button_trips).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
                        Intent intent = new Intent(HomeActivity.this, TripsActivity.class);
                        startActivity(intent);
					}
				});
		
		findViewById(R.id.home_button_profile).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
						startActivity(intent);
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
