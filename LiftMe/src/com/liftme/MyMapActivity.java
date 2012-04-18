package com.liftme;

import java.util.List;


import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import rb.dtu.R;
import com.liftme.control.Server;
import com.liftme.model.Users;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyMapActivity extends MapActivity {

	MapController _mControl;
	GeoPoint _geoP;
	private MapView _mapV;
	private final double _DTU_lat = 55.784482;
	private final double _DTU_lon = 12.522065;
	//Riferimento al MyLocationOverlay
    private MyLocationOverlay _myLocationOverlay;

    // Otteniamo il riferimento al LocationManager
    LocationManager _locationManager;
	private String _provider;
    
    /*RANK*/
    private Users _rank;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /*POPULATE THE PEOPLE TO DISPLAY*/
        //remember - what we should use!? location? long - lat?
        _rank = Server.UpdateContacts();

        setContentView(R.layout.map);
       
        _mapV = (MapView) findViewById(R.id.map);
        _mapV.displayZoomControls(true);
        _mapV.setBuiltInZoomControls(true);
        _mapV.setClickable(true);
        
     // Aggiungiamo l'overlay sulla mappa della nostra posizione
        _myLocationOverlay = new MyLocationOverlay(this, _mapV);
        List<Overlay> overlays = _mapV.getOverlays();
        overlays.add(_myLocationOverlay);
        _myLocationOverlay.enableMyLocation();
        
        /*ADD RANK OVERLAY*/
        
//        for(int i=0;i<_rank.getSize();i++){
//        	Double geoLat = _DTU_lat*1E6;
//            Double geoLng = _DTU_lon*1E6;
//        	_geoP = new GeoPoint(Integer.parseInt(geoLat.toString()), Integer.parseInt(geoLng.toString()));
//        	OverlayItem overlay = new OverlayItem(_geoP, _rank.getArray()[i].getName(), _rank.getArray()[i].getName()+" snippet");
//            overlays.add((Overlay)overlay);
//        }
        
        /*END RANK OVERLAY*/
        
     // Otteniamo il riferimento al LocationManager
        _locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
     // Verifichiamo se il GPS è abilitato altrimenti avvisiamo l'utente
        if(!_locationManager.isProviderEnabled("gps")){
//        		setUpDialog();
                Toast.makeText(this, "GPS is now disabled.", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//            	startActivity(intent);
        }

        //choosing via criteria which is the best provider
        Criteria criteria = new Criteria();
		_provider = _locationManager.getBestProvider(criteria, false);
		// registering the location listener to the provider
        _locationManager.requestLocationUpdates(_provider, 0, 0, _locationListener);
        //get the location
        Location location = _locationManager.getLastKnownLocation(_provider);
        //Toast.makeText(this, "Provider: " + _provider + " location:" + location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_LONG).show();
      
        _geoP = new GeoPoint((int)location.getLatitude(), (int)location.getLatitude());
        _mControl = _mapV.getController();
        _mControl.animateTo(_geoP);
        _mControl.setZoom(16);
    }

	private void setUpDialog() {
//		Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.alert_dialog);
//        dialog.setTitle(R.string.alert_dialog_two_buttons_title);
//        //dialog.setCancelable(true);
//        //there are a lot of settings, for dialog, check them all out!
//
//        //set up text
//        TextView text = (TextView) dialog.findViewById(R.id.alertDialogText);
//        text.setText(R.string.alert_dialog_text);
//
//        //set up button
//        Button button = (Button) dialog.findViewById(R.id.okButton);
//        button.setOnClickListener(new OnClickListener() {
//        @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        //now that the dialog is set up, it's time to show it    
//        dialog.show();

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
		LocationListener _locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
                // Aggiorna il marker della mappa
                _mapV.invalidate();

                // Aggiorna la location
                Double geoLat = location.getLatitude()*1E6;
                Double geoLng = location.getLongitude()*1E6;
                GeoPoint point = new GeoPoint(geoLat.intValue(), geoLng.intValue());

                _mControl.animateTo(point);
        }

        @Override
        public void onProviderDisabled(String provider) {
                //Toast.makeText(MyMapActivity.this,
                //                "onProviderDisabled "+provider, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
                Toast.makeText(MyMapActivity.this,
                                "onProviderEnabled "+provider, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status,Bundle extras) {
                Toast.makeText(MyMapActivity.this,
                                "onStatusChanged "+provider+" status: "+status, Toast.LENGTH_SHORT).show();
        }
};
}
