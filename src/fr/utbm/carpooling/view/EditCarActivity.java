package fr.utbm.carpooling.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.Color;
import fr.utbm.carpooling.view.widgets.ColorGridView;

import java.util.ArrayList;


public class EditCarActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);


        Color red = new Color();
        red.setHex("FF0000");
        Color blue = new Color();
        blue.setHex("0000FF");
        Color green = new Color();
        green.setHex("00FF00");
        Color black = new Color();
        black.setHex("000000");
        Color gray = new Color();
        gray.setHex("808080");
        Color white = new Color();
        white.setHex("FFFFFF");

        ArrayList<Color> colors = new ArrayList<Color>();
        colors.add(red);
        colors.add(blue);
        colors.add(green);
        colors.add(black);
        colors.add(gray);
        colors.add(white);

        ((ColorGridView) findViewById(R.id.edit_car_gridview_colors)).setColors(colors);
    }
}