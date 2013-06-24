package fr.utbm.carpooling.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.model.LoginResponse;
import fr.utbm.carpooling.model.wrapper.UserInfos;
import fr.utbm.carpooling.utils.Resources;
import fr.utbm.carpooling.utils.TaskHandler;
import fr.utbm.carpooling.view.widgets.LoadingDialog;
import fr.utbm.carpooling.webservices.UserWebServices;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity {
	
	/** Keep track of the login task. */
	private TaskHandler<LoginResponse> mAuthTask = null;
	/** Keep track of the gathering info task. */
	private TaskHandler<UserInfos> mGatheringTask = null;

	// Values for email and password at the time of the login attempt.
	private String mLogin = null;
	private String mPassword = null;
	private int mNbTry = 0;

	// UI references.
	private EditText mLoginView;
	private EditText mPasswordView;
    private LoadingDialog mLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        doLogin();
	}

	private void doLogin() {
		setContentView(R.layout.activity_login);
		setTitle(R.string.login_title);

        mLoader = new LoadingDialog(this);
        mLoader.setCancelable(false);

		mLoginView = (EditText) findViewById(R.id.login_edittext_login);
		mPasswordView = (EditText) findViewById(R.id.login_edittext_password);
		mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
				if (id == R.id.login_edittext_password_imeAction || id == EditorInfo.IME_NULL) {
					attemptLogin();
					return true;
				}
				return false;
			}
		});

		findViewById(R.id.login_button_sign_in).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});

		mAuthTask = new TaskHandler<LoginResponse>() {

			@Override
			public void taskSuccessful(LoginResponse object) {
				mLoader.show();
				
				if (object.isLoggedIn()) {
					Resources.initUser(object.getUserId(), object.getApiToken());
					
					if (object.isUserExist()) {
                        mLoader.setText(getString(R.string.info_gathering_info));
                        mLoader.show();
						
						mGatheringTask = new TaskHandler<UserInfos>() {
							
							@Override
							public void taskSuccessful(UserInfos object) {
                                mLoader.dismiss();
								Resources.setUserInfos(object, getApplicationContext());
								Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
								startActivity(intent);
								finish();
							}
							
							@Override
							public void taskFailed() {
								++mNbTry;
								if (mNbTry > 10) {
                                    mLoader.setText(getString(R.string.login_progress_signing_in));
									Toast.makeText(getApplicationContext(), R.string.error_fetching_data, Toast.LENGTH_LONG).show();
									mNbTry = 0;
								} else {
									UserWebServices.getUserInfos(mGatheringTask);
								}
							}
						};
						
						UserWebServices.getUserInfos(mGatheringTask);
					} else {
                        mLoader.dismiss();
						Intent intent = new Intent(LoginActivity.this, CreateUserActivity.class);
			            startActivityForResult(intent, 0);
					}
				} else {
                    mLoader.setText(null);
				}
			}

			@Override
			public void taskFailed() {
                mLoader.dismiss();
				Toast.makeText(getApplicationContext(), "Error while signin in", Toast.LENGTH_LONG).show();
			}
		};
	}


	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {

		// Reset errors.
		mLoginView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mLogin = mLoginView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid login.
		if (mLogin.isEmpty()) {
			mLoginView.setError(getString(R.string.login_error_field_required));
			focusView = mLoginView;
			cancel = true;
		}

		// Check for a valid password.
		if (mPassword.isEmpty()) {
			mPasswordView.setError(getString(R.string.login_error_field_required));
			focusView = mPasswordView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
            mLoader.show();
			mLoader.setText(getString(R.string.login_progress_signing_in));

			UserWebServices.login(mLogin, mPassword, mAuthTask);
		}
	}
	
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0 && resultCode == 1) {
        	Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
			startActivity(intent);
			finish();
        } else if (requestCode == 0 && resultCode == 0) {
        	Resources.resetUser();
        }
        
        super.onActivityResult(requestCode, resultCode, data);
    }
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		mLoader.dismiss();
		finish();
	}
}
