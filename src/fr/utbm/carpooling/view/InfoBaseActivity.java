package fr.utbm.carpooling.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.UserShort;
import fr.utbm.carpooling.utils.Resources;
import fr.utbm.carpooling.utils.TaskHandler;
import fr.utbm.carpooling.view.widgets.LoadingDialog;

public abstract class InfoBaseActivity extends Activity {

    protected TaskHandler<Boolean> mHandler = null;
    protected LoadingDialog mLoader = null;
    protected UserShort mUser = new UserShort();

    protected TextView mName;
    protected TextView mFirstname;
    protected TextView mEmail;
    protected TextView mPhone;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_info);

        setResult(0);
        initView();
        initHandler();
    }

    private void initView() {
        mLoader = new LoadingDialog(this);

        mFirstname = (TextView) findViewById(R.id.edit_info_textview_firstname);
        mName = (TextView) findViewById(R.id.edit_info_textview_name);
        mPhone = (TextView) findViewById(R.id.edit_info_textview_phone);
        mEmail = (TextView) findViewById(R.id.edit_info_textview_email);

        mFirstname.setText(Resources.getUser().getFirstname());
        mName.setText(Resources.getUser().getLastname());
        mPhone.setText(Resources.getUser().getPhone());
        mEmail.setText(Resources.getUser().getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit_info_menuitem_cancel:
                cancelPressed();
                return true;

            case R.id.edit_info_menuitem_save:

                if (checkData()) {

                    mUser.setFirstname(mFirstname.getText().toString());
                    mUser.setLastname(mName.getText().toString());
                    mUser.setPhone(mPhone.getText().toString());
                    mUser.setEmail(mEmail.getText().toString());

                    mLoader.show();
                    pushData();

                    return true;
                }
        }

        return super.onOptionsItemSelected(item);
    }

	@Override
    protected void onPause() {
        super.onPause();

        mLoader.dismiss();
        finish();
    }

    private boolean checkData() {

        boolean ok = true;

        if (mFirstname.getText().toString().isEmpty()) {
            mFirstname.setError(getString(R.string.profile_info_error_field_required));
            ok = false;
        }

        if (mName.getText().toString().isEmpty()) {
            mName.setError(getString(R.string.profile_info_error_field_required));
            ok = false;
        }

        if (mPhone.getText().toString().isEmpty()) {
            mPhone.setError(getString(R.string.profile_info_error_field_required));
            ok = false;
        }

        if (mEmail.getText().toString().isEmpty()) {
            mEmail.setError(getString(R.string.profile_info_error_field_required));
            ok = false;
        }
        return ok;
    }
    
    @Override
    public void onBackPressed() {
    	super.onBackPressed();
    	cancelPressed();
    }
    
	private void cancelPressed() {
		setResult(0);
		finish();
	}

    protected abstract void initHandler();
    
    protected abstract void pushData();
}
