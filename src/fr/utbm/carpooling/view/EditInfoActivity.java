package fr.utbm.carpooling.view;

import android.widget.Toast;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.utils.Resources;
import fr.utbm.carpooling.utils.TaskHandler;
import fr.utbm.carpooling.webservices.UserWebServices;


public class EditInfoActivity extends InfoBaseActivity {
    
    @Override
	protected void initHandler() {
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
                Toast.makeText(getApplicationContext(), R.string.error_updating_data, Toast.LENGTH_LONG).show();
            }

        };
	}

	@Override
	protected void pushData() {
        UserWebServices.createUser(mUser, mHandler);
	}
}
