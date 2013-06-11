package fr.utbm.carpooling.view;

import java.util.ArrayList;
import java.util.Date;

import android.view.View;
import android.widget.Button;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.Car;
import fr.utbm.carpooling.model.Checkpoint;
import fr.utbm.carpooling.model.DriverCar;
import fr.utbm.carpooling.model.PassengerTrip;
import fr.utbm.carpooling.model.User;
import fr.utbm.carpooling.view.widgets.TripDetailsPassengerBlock;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TripDetailsPassengerActivity extends Activity {

    private boolean mSuscribed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details_passenger);

        mSuscribed = getIntent().getExtras().getBoolean("suscribed");

        PassengerTrip data = new PassengerTrip();
        Car car = new DriverCar();
        car.setBrandId(0);
        car.setColorId(8);
        car.setModelId(2);
        car.setSeats(4);
        car.setTrunkId(2);
        data.setCar(car);

        Checkpoint c1 = new Checkpoint();
        c1.setSiteId(0);
        c1.setNumCheckpoint(0);
        c1.setDate(new Date(113, 4, 20, 13, 0));

        Checkpoint c2 = new Checkpoint();
        c2.setSiteId(1);
        c2.setNumCheckpoint(1);
        c2.setDate(new Date(113, 4, 20, 13, 15));

        Checkpoint c3 = new Checkpoint();
        c3.setSiteId(2);
        c3.setNumCheckpoint(2);
        c3.setDate(new Date(113, 4, 20, 13, 40));

        Checkpoint c4 = new Checkpoint();
        c4.setSiteId(0);
        c4.setNumCheckpoint(3);
        c4.setDate(new Date(113, 4, 20, 13, 50));

        ArrayList<Checkpoint> checkpoints1 = new ArrayList<Checkpoint>();
        checkpoints1.add(c1);
        checkpoints1.add(c2);
        checkpoints1.add(c3);
        checkpoints1.add(c4);

        data.setCheckpoints(checkpoints1);

        User uDriver = new User();
        uDriver.setEmail("z@gmail.com");
        uDriver.setFirstname("Driver");
        uDriver.setName("Man");
        uDriver.setPhone("");

        data.setAbstractTripId("2");
        data.setDriver(uDriver);
        data.setFeedbackGiven(false);

        TripDetailsPassengerBlock v = (TripDetailsPassengerBlock) findViewById(R.id.trip_details_driver_view);
        v.setData(data);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.trip_details_passenger, menu);

        menu.findItem(R.id.trip_details_passenger_menuitem_suscribe).setVisible(!mSuscribed);
        menu.findItem(R.id.trip_details_passenger_menuitem_unsuscribe).setVisible(mSuscribed);

        return true;
    }

}
