package fr.utbm.carpooling.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.TaskHandler;
import fr.utbm.carpooling.model.UserShort;
import fr.utbm.carpooling.view.widgets.LoadingDialog;
import fr.utbm.carpooling.webservices.UserWebServices;


public class EditInfoActivity extends Activity {
	
	private TaskHandler<Boolean> mHandler = null;
    private LoadingDialog mLoader = null;
    private UserShort mUser = new UserShort();
    
    private TextView mName;
    private TextView mFirstname;
    private TextView mEmail;
    private TextView mPhone;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        setResult(0);
        initView();
        initHandler();
    }

    private void initHandler() {
    	mHandler = new TaskHandler<Boolean>() {

			@Override
			public void taskSuccessful(Boolean object) {
				if (object) {
					Resources.setUser(mUser);
					Resources.saveUser(getApplicationContext());
					
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
                finish();
                return true;

            case R.id.edit_info_menuitem_save:
            	mUser.setFirstname(mFirstname.getText().toString());
            	mUser.setLastname(mName.getText().toString());
            	mUser.setPhone(mPhone.getText().toString());
            	mUser.setEmail(mEmail.getText().toString());
            	
            	mLoader.show();
                UserWebServices.updateUser(mUser, mHandler);
                
                return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	
    	mLoader.dismiss();
    	finish();
    }
}
