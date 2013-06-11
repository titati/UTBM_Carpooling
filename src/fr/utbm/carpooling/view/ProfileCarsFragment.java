package fr.utbm.carpooling.view;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.adapter.DriverCarAdapter;
import fr.utbm.carpooling.model.DriverCar;


public class ProfileCarsFragment extends Fragment {

    private ListView mListView = null;

    private class CarOptionsDialog extends AlertDialog {

        public CarOptionsDialog(Context context) {
            super(context);

        }
    }


    public ProfileCarsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
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
        c.setBrandId(1);
        c.setModelId(3);
        c.setColorId(6);
        c.setDefaultCar(false);
        c.setSeats(3);
        c.setTrunkId(2);

        ArrayList<DriverCar> cars = new ArrayList<DriverCar>();
        cars.add(0, m);
        cars.add(1, c);

        final DriverCarAdapter adapter = new DriverCarAdapter(getActivity(), R.layout.view_driver_car_item, cars);

        mListView.setAdapter(adapter);
        mListView.setItemsCanFocus(false);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                CharSequence items[] = new CharSequence[2];
                items[0] = getResources().getString(R.string.profile_cars_option_make_default);
                items[1] =getResources().getString(R.string.profile_cars_option_delete);

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                if (!adapter.getItem(which).isDefaultCar()) {
                                    // todo: updateCar
                                }
                                break;
                            case 1:
                                // todo: deleteCar
                                break;
                        }
                        // update list
                    }
                });

                builder.create().show();
                return false;
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_cars, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile_menuitem_new_car:
                startActivityForResult(new Intent(getActivity(), EditCarActivity.class), 0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0 && resultCode == 1) {
            //TODO: get updated car list
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
