package fr.utbm.carpooling.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import fr.utbm.carpooling.R;


public class ProfileInfoFragment extends Fragment {

	public ProfileInfoFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        setHasOptionsMenu(true);
		return inflater.inflate(R.layout.fragment_profile_info, container,
				false);
	}


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_info, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile_menuitem_edit_info:
                startActivityForResult(new Intent(getActivity(), EditInfoActivity.class), 0);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0 && resultCode == 1) {
            //TODO: get updated user infos
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
