package fr.utbm.carpooling.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.model.Color;
import fr.utbm.carpooling.view.widgets.ColorGridView;

import java.util.ArrayList;


public class EditCarActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);


        ((ColorGridView) findViewById(R.id.edit_car_gridview_colors)).setColors((ArrayList<Color>) Resources.getColors());
    }

}