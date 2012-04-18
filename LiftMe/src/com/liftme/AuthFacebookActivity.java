package com.liftme;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;


import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import rb.dtu.R;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AuthFacebookActivity extends Activity {
	
	// My Facebook APP ID
    private static String APP_ID = "280010475406437";
    
    // Instance of Facebook Class
    private Facebook facebook;
    private AsyncFacebookRunner mAsyncRunner;
    String FILENAME = "AndroidSSO_data";
    private SharedPreferences mPrefs;
    
    //Constant of Dialogs
    static final int LOGIN_DIALOG_ID = 0;
    static final int GOOGLELOGIN_DIALOG_ID = 1;
    static final int REGISTRATION_DIALOG_ID = 2;
    
    //Variables
    Button _buttonFacebook, _buttonLogin, _confirmButton, _setCancelButton, _RegistrationLogin, _RegistrationMain, _RegSave, _RegCancel;
    EditText _usernameLogin, _passwordLogin, _name, _surname, _email, _location, _RegUsername, _RegPassword;
    TextView label1, label2, label3, label4;

        
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        facebook = new Facebook(APP_ID);
        mAsyncRunner = new AsyncFacebookRunner(facebook);   
        
        _buttonFacebook = (Button) findViewById(R.id.buttonFacebook);
        _buttonLogin = (Button) findViewById(R.id.mainLogin);

      
        _buttonFacebook.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				loginToFacebook();
				getProfileInformation();
			}
		});
        
        //Setting the botton into the main Page
        _buttonLogin.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(LOGIN_DIALOG_ID);
			}
		});
        
    	_RegistrationMain = (Button) findViewById(R.id.buttonREGISTER);
    	
    	_RegistrationMain.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(REGISTRATION_DIALOG_ID);
			}
		});
    	

        
    }
    
    //Method to manage the several dialog
    protected Dialog onCreateDialog(int id)
    {
    	Dialog dialog = null;
    	switch (id)
    	{
    	case LOGIN_DIALOG_ID:
    		return createLogin( dialog );
    	case GOOGLELOGIN_DIALOG_ID:
    		return null;
    	case REGISTRATION_DIALOG_ID:
    		return createRegistration( dialog );
    	}
    	return null;
    }
    
    // Method to create a window dialog where the user can do the Login ( work in progress ) 
    private Dialog createLogin( Dialog dialog ){
    	
    	dialog = new Dialog(this);

    	dialog.setContentView(R.layout.dialoglogin);
    	dialog.setTitle("Login");
    	
    	_usernameLogin = (EditText) dialog.findViewById(R.id.editText1);
    	_passwordLogin = (EditText) dialog.findViewById(R.id.editText2);
    	
    	_confirmButton = (Button) dialog.findViewById(R.id.confirmLogin);
    	
    	_confirmButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// At this moment we are not able to make the identification of the user
				// so we just show a message by toast where you can see what you've typed
				
				String us = _usernameLogin.getText().toString();
				String pass = _passwordLogin.getText().toString();
				Toast.makeText(getApplicationContext(), us + 
						" entered with success", Toast.LENGTH_LONG).show();
				Intent myIntent = new Intent(AuthFacebookActivity.this, LiftMeActivity.class);
				AuthFacebookActivity.this.startActivity(myIntent);

			}
		});
    	
    	_setCancelButton = (Button) dialog.findViewById(R.id.LoginCancel);
    	
    	_setCancelButton.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				removeDialog(LOGIN_DIALOG_ID);
			}
		});
    	
    	_RegistrationLogin = (Button) dialog.findViewById(R.id.loginRegister);
    	
    	_RegistrationLogin.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(REGISTRATION_DIALOG_ID);
			}
		});
    	
    	return dialog;

    }
    
    //This method create the dialog window that save information's user into Object UserProfile
    private Dialog createRegistration( Dialog dialog ){
    	
    	dialog = new Dialog(this);

    	dialog.setContentView(R.layout.registration);  	
    	dialog.setTitle("Registration");
    	
    	//Initialize the variables of each EditText to get the information fron the new user
    	_name = (EditText) dialog.findViewById(R.id.editName);
    	_surname = (EditText) dialog.findViewById(R.id.editSurname);
    	_email = (EditText) dialog.findViewById(R.id.editEmail);
    	_location = (EditText) dialog.findViewById(R.id.editLocation);
    	_RegUsername = (EditText) dialog.findViewById(R.id.editUsername);
    	_RegPassword = (EditText) dialog.findViewById(R.id.editPassword);
    	
    	
    	_RegSave = (Button) dialog.findViewById(R.id.buttonSave);
    	
    	_RegSave.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				UserProfile newUser = new UserProfile();
				
				newUser.SetName(_name.getText().toString());
				newUser.SetSurname(_surname.getText().toString());
				newUser.SetEmail(_email.getText().toString());
				newUser.SetLocation("123", _location.getText().toString());
				newUser.SetUsername(_RegUsername.getText().toString());
				newUser.SetPassword(_RegPassword.getText().toString());
				
				Toast.makeText(getApplicationContext(), "Surname: " + newUser.getSurname() + " Name: " + newUser.getName(), Toast.LENGTH_LONG).show();
				
			}
		});
    	
    	_RegCancel = (Button) dialog.findViewById(R.id.buttonCancel);
    	
    	_RegCancel.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				removeDialog(REGISTRATION_DIALOG_ID);
			}
		});

    	return dialog;

    }

    
    public void loginToFacebook() {
        mPrefs = getPreferences(MODE_PRIVATE);
        String access_token = mPrefs.getString("access_token", null);
        long expires = mPrefs.getLong("access_expires", 0);
     
        if (access_token != null) {
            facebook.setAccessToken(access_token);
        }
     
        if (expires != 0) {
            facebook.setAccessExpires(expires);
        }
     
        if (!facebook.isSessionValid()) {
            facebook.authorize(this,
                    new String[] { "email", "publish_stream" },
                    new DialogListener() {
     
                        @Override
                        public void onCancel() {
                            // Function to handle cancel event
                        }
     
                        @Override
                        public void onComplete(Bundle values) {
                            // Function to handle complete event
                            // Edit Preferences and update facebook acess_token
                            SharedPreferences.Editor editor = mPrefs.edit();
                            editor.putString("access_token",
                                    facebook.getAccessToken());
                            editor.putLong("access_expires",
                                    facebook.getAccessExpires());
                            editor.commit();
                        }
     
                        @Override
                        public void onError(DialogError error) {
                            // Function to handle error
     
                        }
     
                        @Override
                        public void onFacebookError(FacebookError fberror) {
                            // Function to handle Facebook errors
     
                        }
     
                    });
            
        }

    }
    
    
    public void getProfileInformation() {
        mAsyncRunner.request("me", new RequestListener() {

            public void onComplete(String response, Object state) {
                Log.d("Profile", response);
                String json = response;
                try {
                    JSONObject profile = new JSONObject(json);
                    // getting name of the user
                    final String name = profile.getString("first_name");
                    // getting email of the user
                    final String email = profile.getString("email");
                    // getting surname of the user
                    final String surname = profile.getString("last_name");
                    // getting location of the user
                    final String location = profile.getString("location"); 
                    JSONObject loc = new JSONObject(location);
                    
                    final String id = loc.getString("id");
                    final String locname = loc.getString("name");
                    
                    runOnUiThread(new Runnable() {
     
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Name: " + name + "\nEmail: " + email, Toast.LENGTH_LONG).show();

                            // Create the user and fill his's fields
                            UserProfile user = new UserProfile();
                            user.SetName(name);
                            user.SetEmail(email);
                            user.SetSurname(surname);
                            user.SetLocation(id, locname);
                            
                        }
     
                    });
     
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
     
            public void onIOException(IOException e, Object state) {
            }
     
            public void onFileNotFoundException(FileNotFoundException e,
                    Object state) {
            }
     
            public void onMalformedURLException(MalformedURLException e,
                    Object state) {
            }
     
            public void onFacebookError(FacebookError e, Object state) {
            }
        });
    }
    

    
}