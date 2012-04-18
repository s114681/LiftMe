package com.liftme;

import com.liftme.R;
import com.liftme.control.Server;
import com.liftme.model.User;
import com.liftme.model.Users;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class Profile extends Activity {
	private TextView _name;
	private TextView _positiveScore;
	private ImageView _image;
	private TextView _negativeScore;
	private LinearLayout _profileImages;
	
	private ImageButton _callImageButton;
	private TextView _callTextView;

	private User _selectedContact;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        
        Users contacts = Server.UpdateContacts();
        
        //get all the components
        _name = (TextView)findViewById(R.id.name);
        _positiveScore = (TextView)findViewById(R.id.positiveScore);
        _negativeScore = (TextView)findViewById(R.id.negativeScore);
        _image = (ImageView)findViewById(R.id.profileImage);
        _profileImages = (LinearLayout)findViewById(R.id.profileImages);
        
        //set values
        Bundle extras = getIntent().getExtras();
        String name = extras.getCharSequence("name").toString();
        _selectedContact = contacts.getUserByName(name);
       
        _name.setText(_selectedContact.getName());
        _positiveScore.setText(_selectedContact.getPoints()[0]+"");
        _negativeScore.setText(_selectedContact.getPoints()[1]+"");
        _image.setImageResource(getResources().getIdentifier(((String) extras.getCharSequence("name")).toLowerCase() , "drawable", getPackageName()));
   
        //set friends
        String[] friends = extras.getStringArray("friends");
        for (String friend : friends) {
        	if(!friend.equalsIgnoreCase((String) extras.getCharSequence("name"))){
	        	ImageView friendImage = new ImageView(this);
	        	int resId = this.getResources().getIdentifier(friend.toLowerCase(),"drawable","com.liftme");
	        	friendImage.setImageResource(resId);
	        	friendImage.setPadding(4, 2, 4, 2);
	        	LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
	        	friendImage.setLayoutParams(layoutParams);
				_profileImages.addView(friendImage);
        	}
		}

      //SETTING THE CALLING CONTACT
        _callTextView = (TextView) findViewById(R.id.callTextView);
        _callTextView.setText("Get in contact");
        
        _callImageButton = (ImageButton) findViewById(R.id.callImageButton);
    	_callImageButton.setImageResource(android.R.drawable.sym_action_call);
    	_callImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                call();
            }
        });        
       
    }
    
    private void call() {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+_selectedContact.getPhoneNumber()));
            startActivity(callIntent);
        } catch (ActivityNotFoundException e) {
            Log.e("helloandroid dialing example", "Call failed", e);
        }
    }
    
    
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        return true;
//    }
//    
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch ( item.getItemId() ) {
//            case R.id.settings:
//            startActivity(new Intent(this, Settings.class));
//            return true;
//            case R.id.exit:
//            android.os.Process.killProcess(android.os.Process.myPid());
//            return true;
//        }
//        return false;
//    }
}
