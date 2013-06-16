package fr.utbm.carpooling.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fr.utbm.carpooling.R;
import fr.utbm.carpooling.Resources;
import fr.utbm.carpooling.TaskHandler;
import fr.utbm.carpooling.model.LoginResponse;
import fr.utbm.carpooling.model.wrapper.UserInfos;
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
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        if (Resources.getUser() == null) {
        	doLogin();
        } else {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
	}


	private void doLogin() {
		setContentView(R.layout.activity_login);
		setTitle(R.string.login_title);

		mLoginView = (EditText) findViewById(R.id.login_edittext_login);
		mPasswordView = (EditText) findViewById(R.id.login_edittext_password);
		findViewById(R.id.login_checkbox_remember).setVisibility(View.GONE);
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

		mLoginFormView = findViewById(R.id.login_scrollview_data);
		mLoginStatusView = findViewById(R.id.login_linearlayout_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_textview_status);

		findViewById(R.id.login_textview_invalid_data).setVisibility(View.GONE);
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
				showProgress(false);
				
				if (object.isLoggedIn()) {
					Resources.initUser(object.getUserId(), object.getApiToken());
					
					if (object.isUserExist()) {
						((TextView) findViewById(R.id.login_textview_status)).setText("Gathering information");
						showProgress(true);
						
						mGatheringTask = new TaskHandler<UserInfos>() {
							
							@Override
							public void taskSuccessful(UserInfos object) {
								showProgress(false);
								Resources.setUserInfos(object, getApplicationContext());
								Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
								startActivity(intent);
								finish();
							}
							
							@Override
							public void taskFailed() {
								++mNbTry;
								if (mNbTry > 10) {
									((TextView) findViewById(R.id.login_textview_status)).setText(R.string.login_progress_signing_in);
									Toast.makeText(getApplicationContext(), "Error while gathering information", Toast.LENGTH_LONG).show();
									mNbTry = 0;
								} else {
									UserWebServices.getUserInfos(mGatheringTask);
								}
							}
						};
						
						UserWebServices.getUserInfos(mGatheringTask);
					} else {
						Intent intent = new Intent(LoginActivity.this, CreateUserActivity.class);
			            startActivity(intent);
					}
				} else {
					mLoginStatusMessageView.setVisibility(View.VISIBLE);
					findViewById(R.id.login_textview_invalid_data).setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void taskFailed() {
				showProgress(false);
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
		findViewById(R.id.login_textview_invalid_data).setVisibility(View.GONE);

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
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);

			showProgress(true);
			UserWebServices.login(mLogin, mPassword, mAuthTask);
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
			.alpha(show ? 1 : 0)
			.setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoginStatusView.setVisibility(show ? View.VISIBLE
							: View.GONE);
				}
			});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
			.alpha(show ? 0 : 1)
			.setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoginFormView.setVisibility(show ? View.GONE
							: View.VISIBLE);
				}
			});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		finish();
	}
}
