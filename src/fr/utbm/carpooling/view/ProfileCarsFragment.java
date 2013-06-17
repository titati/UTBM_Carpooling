package fr.utbm.carpooling.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.TaskHandler;
import fr.utbm.carpooling.adapter.DriverCarAdapter;
import fr.utbm.carpooling.view.widgets.LoadingDialog;
import fr.utbm.carpooling.webservices.DriverWebServices;


public class ProfileCarsFragment extends Fragment {

    private ListView mListView = null;
    private TaskHandler<Boolean> mDeleteTask = null;
    private TaskHandler<Boolean> mSetDefaultTask = null;
    private LoadingDialog mLoader = null;


    public ProfileCarsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        mLoader = new LoadingDialog(getActivity());
        
        return inflater.inflate(R.layout.fragment_profile_cars, container, false);
    }

	@Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mListView = (ListView) getView().findViewById(
                R.id.profile_cars_listview_cars);

        final DriverCarAdapter adapter = new DriverCarAdapter(getActivity(), R.layout.view_driver_car_item, Resources.getCars());

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
                                	makeAsDefault(adapter.getItem(which).getId());
                                }
                                break;
                            case 1:
                                deleteCar(adapter.getItem(which).getId());
                                break;
                        }
                    }
                });

                builder.create().show();
                return false;
            }
        });

    }
	
	private void deleteCar(final int id) {
		mDeleteTask = new TaskHandler<Boolean>() {
			
			@Override
			public void taskSuccessful(Boolean object) {
				Resources.deleteCar(id);
			}

			@Override
			public void taskFailed() {
				mLoader.hide();
			}
		};

        mLoader.show();
		DriverWebServices.deleteCar(id, mDeleteTask);
	}
	
	private void makeAsDefault(final int id) {
		mSetDefaultTask = new TaskHandler<Boolean>() {

			@Override
			public void taskSuccessful(Boolean object) {
				Resources.deleteCar(id);
			}

			@Override
			public void taskFailed() {
				mLoader.hide();
			}
		};
		
		mLoader.show();
        DriverWebServices.setDefaultCar(id, mSetDefaultTask);
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

        if (resultCode == 1) {
            //TODO: get updated car list with the new car
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
