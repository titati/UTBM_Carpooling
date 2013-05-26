package fr.utbm.carpooling.view;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.Brand;
import fr.utbm.carpooling.model.Color;
import fr.utbm.carpooling.model.DriverCar;
import fr.utbm.carpooling.model.Model;
import fr.utbm.carpooling.model.Trunk;
import fr.utbm.carpooling.view.widgets.DriverCarAdapter;


public class ProfileCarsFragment extends Fragment {

    private ListView mListView = null;

    public ProfileCarsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_cars, container,
                false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mListView = (ListView) getView().findViewById(
                R.id.profile_cars_listview_cars);

        Brand renault = new Brand(0, "");
        renault.setName("Renault");
        Model megane = new Model(0, 1, "");
        megane.setName("Megane");
        Model clio = new Model(0, 2, "");
        clio.setName("Clio");
        Color red = new Color(3, "", "");
        red.setHex("#FF0000");
        Color blue = new Color(4, "", "");
        blue.setHex("0000FF");
        Trunk trunk = new Trunk(5, "");
        trunk.setName("Regular");

        DriverCar m = new DriverCar();
        m.setBrand(renault);
        m.setModel(megane);
        m.setColor(red);
        m.setDefaultCar(true);
        m.setSeats(4);
        m.setTrunk(trunk);

        DriverCar c = new DriverCar();
        c.setBrand(renault);
        c.setModel(clio);
        c.setColor(blue);
        c.setDefaultCar(false);
        c.setSeats(4);
        c.setTrunk(trunk);

        ArrayList<DriverCar> cars = new ArrayList<DriverCar>();
        cars.add(0, m);
        cars.add(1, c);

        DriverCarAdapter adapter = new DriverCarAdapter(getActivity(),
                R.id.profile_cars_listview_cars, cars);

        mListView.setAdapter(adapter);

    }

}
