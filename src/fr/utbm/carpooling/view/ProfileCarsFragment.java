package fr.utbm.carpooling.view;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.adapter.DriverCarAdapter;
import fr.utbm.carpooling.model.Brand;
import fr.utbm.carpooling.model.Color;
import fr.utbm.carpooling.model.DriverCar;
import fr.utbm.carpooling.model.Model;
import fr.utbm.carpooling.model.Trunk;


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

        DriverCar m = new DriverCar();
        m.setBrandId(0);
        m.setModelId(2);
        m.setColorId(8);
        m.setDefaultCar(true);
        m.setSeats(4);
        m.setTrunkId(2);

        DriverCar c = new DriverCar();
        m.setBrandId(1);
        m.setModelId(3);
        m.setColorId(6);
        m.setDefaultCar(false);
        m.setSeats(3);
        m.setTrunkId(2);

        ArrayList<DriverCar> cars = new ArrayList<DriverCar>();
        cars.add(0, m);
        cars.add(1, c);

        DriverCarAdapter adapter = new DriverCarAdapter(getActivity(),
                R.id.profile_cars_listview_cars, cars);

        mListView.setAdapter(adapter);
    }

}
