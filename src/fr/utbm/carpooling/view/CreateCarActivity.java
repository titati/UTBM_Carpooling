package fr.utbm.carpooling.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.adapter.BrandAdapter;
import fr.utbm.carpooling.adapter.ModelAdapter;
import fr.utbm.carpooling.adapter.TrunkAdapter;
import fr.utbm.carpooling.model.Brand;
import fr.utbm.carpooling.model.DriverCar;
import fr.utbm.carpooling.model.Model;
import fr.utbm.carpooling.model.wrapper.Trunk;
import fr.utbm.carpooling.utils.Resources;
import fr.utbm.carpooling.utils.TaskHandler;
import fr.utbm.carpooling.view.widgets.ColorGridView;
import fr.utbm.carpooling.view.widgets.LoadingDialog;
import fr.utbm.carpooling.webservices.DriverWebServices;


public class CreateCarActivity extends Activity {

    private ColorGridView mColors;
    private EditText mSeats;
    private Spinner mTrunks;
    private Spinner mBrands;
    private Spinner mModels;
    private CheckBox mDefault;

    private TaskHandler<Boolean> mHandler = null;
    private LoadingDialog mLoader = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initHandler();
    }

    private void initView() {
        mLoader = new LoadingDialog(this);

        setContentView(R.layout.activity_create_car);

        mColors = (ColorGridView) findViewById(R.id.edit_car_gridview_colors);
        mTrunks = (Spinner) findViewById(R.id.edit_car_spinner_trunk);
        mBrands = (Spinner) findViewById(R.id.edit_car_spinner_brand);
        mModels = (Spinner) findViewById(R.id.edit_car_spinner_model);
        mSeats = (EditText) findViewById(R.id.edit_car_edittext_seats);
        mDefault = (CheckBox) findViewById(R.id.edit_car_checkbox_default);

        final TrunkAdapter trunkAdapter = new TrunkAdapter(this, 0, Resources.getTrunks());
        mTrunks.setAdapter(trunkAdapter);

        final BrandAdapter brandAdapter = new BrandAdapter(this, 0, Resources.getBrands());
        mBrands.setAdapter(brandAdapter);
        mBrands.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ModelAdapter modelAdapter = new ModelAdapter(CreateCarActivity.this, 0, Resources.getModels(brandAdapter.getItem(position).getId()));
                mModels.setAdapter(modelAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mColors.setColors(Resources.getColors());
    }

    private void initHandler() {
        mHandler = new TaskHandler<Boolean>() {

            @Override
            public void taskSuccessful(Boolean object) {
                if (object) {
                    setResult(1);
                    finish();
                } else {
                    taskFailed();
                }
            }

            @Override
            public void taskFailed() {
                mLoader.hide();
                Toast.makeText(getApplicationContext(), "Error while updating data", Toast.LENGTH_LONG).show();
            }

        };
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
                finish();
                return true;

            case R.id.edit_car_menuitem_save:

                if (checkData()) {

                    DriverCar car = new DriverCar();
                    car.setDefaultCar(mDefault.isChecked());
                    car.setBrandId(((Brand) mBrands.getSelectedItem()).getId());
                    car.setBrandId(((Model) mModels.getSelectedItem()).getId());
                    car.setColorId(mColors.getSelectedColor().getId());
                    car.setSeats(Integer.parseInt(mSeats.getText().toString()));
                    car.setTrunkId(((Trunk) mTrunks.getSelectedItem()).getId());

                    mLoader.show();
                    DriverWebServices.createCar(car, mHandler);

                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean checkData() {

        boolean ok = true;

        if (mSeats.getText().toString().isEmpty()) {

            mSeats.setError(getString(R.string.edit_car_error_seats));
            ok = false;
        }

        if (mColors.getSelectedColor() == null) {

            mColors.setError(getString(R.string.edit_car_error_color));
            ok = false;
        }

        return ok;
    }

    @Override
    protected void onPause() {
        super.onPause();

        mLoader.dismiss();
        finish();
    }

}