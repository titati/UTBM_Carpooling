package fr.utbm.carpooling.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.TextView;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.Resources;


public class ProfileInfoFragment extends Fragment {
    
    private TextView mName;
    private TextView mFirstname;
    private TextView mEmail;
    private TextView mPhone;

	public ProfileInfoFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        
		return inflater.inflate(R.layout.fragment_profile_info, container, false);
	}
	
	@Override
	public void onResume() {
        initView();
        
		super.onResume();
	}

	private void initView() {
		mFirstname = (TextView) getView().findViewById(R.id.profile_info_textview_firstname);
		mName = (TextView) getView().findViewById(R.id.profile_info_textview_name);
		mPhone = (TextView) getView().findViewById(R.id.profile_info_textview_phone);
		mEmail = (TextView) getView().findViewById(R.id.profile_info_textview_email);

		mFirstname.setText(Resources.getUser().getFirstname());
		mName.setText(Resources.getUser().getLastname());
		mPhone.setText(Resources.getUser().getPhone());
		mEmail.setText(Resources.getUser().getEmail());
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
