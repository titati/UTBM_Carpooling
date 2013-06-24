package fr.utbm.carpooling.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.adapter.BrandAdapter;
import fr.utbm.carpooling.adapter.ModelAdapter;
import fr.utbm.carpooling.adapter.TrunkAdapter;
import fr.utbm.carpooling.model.Color;
import fr.utbm.carpooling.utils.Resources;
import fr.utbm.carpooling.view.widgets.ColorGridView;

import java.util.ArrayList;


public class EditCarActivity extends Activity {

    private ColorGridView mColors;
    private Spinner mTrunks;
    private Spinner mBrands;
    private Spinner mModels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);

        mColors = (ColorGridView) findViewById(R.id.edit_car_gridview_colors);
        mTrunks = (Spinner) findViewById(R.id.edit_car_spinner_trunk);
        mBrands = (Spinner) findViewById(R.id.edit_car_spinner_brand);
        mModels = (Spinner) findViewById(R.id.edit_car_spinner_model);

        final TrunkAdapter trunkAdapter = new TrunkAdapter(this, 0, Resources.getTrunks());
        mTrunks.setAdapter(trunkAdapter);

        final BrandAdapter brandAdapter = new BrandAdapter(this, 0, Resources.getBrands());
        mBrands.setAdapter(brandAdapter);

        mBrands.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ModelAdapter modelAdapter = new ModelAdapter(EditCarActivity.this, 0, Resources.getModels(brandAdapter.getItem(position).getId()));
                mModels.setAdapter(modelAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mColors.setColors((ArrayList<Color>) Resources.getColors());
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
                //TODO: update the car
                this.setResult(1);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}