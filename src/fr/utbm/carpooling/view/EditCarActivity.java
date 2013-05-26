package fr.utbm.carpooling.view;

import android.app.Activity;
import android.os.Bundle;
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

}