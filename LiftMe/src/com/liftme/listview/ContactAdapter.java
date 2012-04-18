package com.liftme.listview;

import java.util.Date;

import rb.dtu.R;
import com.liftme.model.User;
import com.liftme.model.Users;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends ArrayAdapter<User> {

	Context context; 
    int layoutResourceId;    
    User data[] = null;
    Location _myLocation;
    
    public ContactAdapter(Context context, int layoutResourceId, Users data, Location location) {
        super(context, layoutResourceId, data.getArray());
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data.getArray();
        this._myLocation = location;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ContactHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new ContactHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.ownImage);
            holder.txtName = (TextView)row.findViewById(R.id.ownNameTV);
            holder.txtDistance = (TextView)row.findViewById(R.id.currentPositionTV);
            holder.txtTimeStamp = (TextView)row.findViewById(R.id.lastUpdateTV);
            holder.orientation = (ImageView)row.findViewById(R.id.orientation);
            
            row.setTag(holder);
        }
        else
        {
            holder = (ContactHolder)row.getTag();
        }
        
        User user = data[position];
        
       // Location location = contact.getCurrentLocation();
        float compassHeading =  getBearing(user.getCurrentPositionLatitude(),user.getCurrentPositionLongitude(),
        		_myLocation.getLatitude(),_myLocation.getLongitude());// Calculate bearing
        float distance = getDistance(user.getCurrentPositionLatitude(),user.getCurrentPositionLongitude(),
        		_myLocation.getLatitude(),_myLocation.getLongitude());// Calculate distance 
       
        holder.txtName.setText(user.getName());
        holder.imgIcon.setImageResource(user.getIcon());
        
        
        holder.txtTimeStamp.setText(formatLastUpdate(user.getTimeStamp()));
        holder.txtDistance.setText(formatDistance(distance));
        holder.orientation.setImageDrawable(rotateDrawable(compassHeading));
        
        return row;
    }


	private float getBearing(float currentPositionLatitude,
			float currentPositionLongitude, double latitude, double longitude) {
//		angle = atan2( sin(dlong).cos(lat2), cos(lat1).sin(lat2) - sin(lat1).cos(lat2).cos(dlong) )

//		Bearing is a direction to move from one location to another location (starting from north and then clockwise).
//		While angle in 2D starts from the east and then counter clockwise. 
//		So if an angle is what you need, later you'll need to add 90 degree to the result and then revert it (add minus).


		return 0;
	}

	private float getDistance(float currentPositionLatitude,
			float currentPositionLongitude, double latitude, double longitude) {
		// TODO Auto-generated method stub
//		a = sin^2(dlat/2) + cos(lat1).cos(lat2).sin^2(dlong/2)
//		c = 2.atan2(sqrt(a), sqrt(1-a))
//		d = R.c
//		 	where R is earth's radius (mean radius = 6,371km);
//		note that angles need to be in radians to pass to trig functions!
		
		return 0;
	}

	private CharSequence formatDistance(float distance) {
		// TODO Auto-generated method stub
		return null;
	}

	private CharSequence formatLastUpdate(Date timeStamp) {
		Date currentDate = new Date();
		long lastUpdate = (currentDate.getTime() - timeStamp.getTime())/1000; //last update in seconds
		
		if(lastUpdate<3600){// less than one hour
			return ((int)(lastUpdate/60))+" min";
		}else if (lastUpdate<(3600*24)) {// between one hour and one day
			int hour = (int) (lastUpdate % 3600);
			int min = (int) ((lastUpdate/3600) - hour);
			return hour + "h " + min +"m";
		}else{//days
			int day = (int) (lastUpdate % (3600*24));
			int hour = (int) (((lastUpdate/(3600*24)) - day) % 3600);
			int min = (int) ((lastUpdate/3600) - hour);
			return day + "g " + hour + "h " + min +"m";
		}
	}

	public BitmapDrawable rotateDrawable(float angle)
    {
      Bitmap arrowBitmap = BitmapFactory.decodeResource(context.getResources(), 
                                                        R.drawable.location_arrow);
      // Create blank bitmap of equal size
      Bitmap canvasBitmap = arrowBitmap.copy(Bitmap.Config.ARGB_8888, true);
      canvasBitmap.eraseColor(0x00000000);

      // Create canvas
      Canvas canvas = new Canvas(canvasBitmap);

      // Create rotation matrix
      Matrix rotateMatrix = new Matrix();
      rotateMatrix.setRotate(angle, canvas.getWidth()/2, canvas.getHeight()/2);

      // Draw bitmap onto canvas using matrix
      canvas.drawBitmap(arrowBitmap, rotateMatrix, null);

      return new BitmapDrawable(canvasBitmap); 
    }
    
    static class ContactHolder
    {
    	ImageView imgIcon;
    	ImageView orientation;
        TextView txtName;
        TextView txtDistance;
        TextView txtTimeStamp;
    }
}
