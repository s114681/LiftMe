package com.liftme;

import com.liftme.R;
import com.liftme.control.Server;
import com.liftme.listview.ContactAdapter;
import com.liftme.model.User;
import com.liftme.model.Users;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class RankActivity extends Activity implements LocationListener{
	
	private Users contact_data;
	private ListView _listView;
	private LocationManager _locationManager;
	private String _provider;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /*populate*/
        contact_data = Server.UpdateContacts();
    
        /* get location */
        
 		_locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
 		// Define the criteria how to select the locatioin provider -> use
 		// default
 		Criteria criteria = new Criteria();
 		_provider = _locationManager.getBestProvider(criteria, false);
 		Location location = _locationManager.getLastKnownLocation(_provider);
        
        _listView = new ListView(this);
      //set listener for listview
        _listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
//				Toast.makeText(getApplicationContext(), 
//			            "You have selected " + contact_data[position]._name, 
//			            Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getApplicationContext(), Profile.class);
				//creo una variabile che sarà passata all'activity chiamata
				User selectedContact = contact_data.getUserByPosition(position);
				intent.putExtra("name", selectedContact.getName());
				String[] friends = new String[selectedContact.getFriends().getSize()];
				for (int i= 0; i<selectedContact.getFriends().getSize();i++) {
					friends[i] = (selectedContact.getFriends().getArray()[i]).getName();
				}
				intent.putExtra("friends", friends);
				startActivity(intent);
			}
		});

        ContactAdapter adapter = new ContactAdapter(this, 
                R.layout.listview_item_row, contact_data,location);
        
        /*POSSIBLE HEADER*/
//        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
//        listView1.addHeaderView(header);

        _listView.setAdapter(adapter);
        
        setContentView(_listView);
    }//onCreate

	/* Request updates at startup */
	@Override
	protected void onResume() {
		super.onResume();
		_locationManager.requestLocationUpdates(_provider, 400, 1, this);
	}

	/* Remove the locationlistener updates when Activity is paused */
	@Override
	protected void onPause() {
		super.onPause();
		_locationManager.removeUpdates(this);
	}
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
}
