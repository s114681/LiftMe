package com.liftme;

import com.liftme.R;
import com.liftme.control.Server;
import com.liftme.model.User;
import com.liftme.model.Users;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MeActivity extends Activity {
	
	private User _myContact;
	
	/*LAYOUT*/
	private TextView _name;
	private TextView _positiveScore;
	private ImageView _image;
	private TextView _negativeScore;
	private LinearLayout _profileImages;
	
	/*ONCREATE*/
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*INIT*/
		_myContact = Server.getOwnContact();
		if(_myContact == null){
			setContentView(R.layout.error);
			return;
		}
		setContentView(R.layout.profile);
		/*END INIT*/
		
		/*SET LAYOUT*/
		//get all the components
        _name = (TextView)findViewById(R.id.name);
        _positiveScore = (TextView)findViewById(R.id.positiveScore);
        _negativeScore = (TextView)findViewById(R.id.negativeScore);
        _image = (ImageView)findViewById(R.id.profileImage);
        _profileImages = (LinearLayout)findViewById(R.id.profileImages);
        
        //set values
        _name.setText(_myContact.getName());
        _positiveScore.setText(_myContact.getPoints()[0]+"");
        _negativeScore.setText(_myContact.getPoints()[1]+"");
        _image.setImageResource(getResources().getIdentifier(_myContact.getName().toLowerCase() , "drawable", getPackageName()));
   
        //set friends
        Users friends = _myContact.getFriends();
        for (int i=0; i<friends.getSize();i++) {
        	User friend = friends.getArray()[i];
        	if(!friend.getName().equalsIgnoreCase(_myContact.getName())){
	        	ImageView friendImage = new ImageView(this);
	        	int resId = this.getResources().getIdentifier(friend.getName().toLowerCase(),"drawable","rb.dtu");
	        	friendImage.setImageResource(resId);
	        	friendImage.setPadding(4, 2, 4, 2);
	        	LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
	        	friendImage.setLayoutParams(layoutParams);
				_profileImages.addView(friendImage);
        	}
		}
        
        /*END SET LAYOUT*/
        
    }
}
