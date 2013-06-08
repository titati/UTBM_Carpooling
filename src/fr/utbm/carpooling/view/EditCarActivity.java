package fr.utbm.carpooling.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.model.Color;
import fr.utbm.carpooling.view.widgets.ColorGridView;

import java.util.ArrayList;


public class EditCarActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);


        ((ColorGridView) findViewById(R.id.edit_car_gridview_colors)).setColors((ArrayList<Color>) Resources.getColors());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_car, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit_car_menuitem_cancel:
                this.setResult(0);
                finish();
                return true;

            case R.id.edit_car_menuitem_save:
                //TODO: create the car
                this.setResult(1);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}