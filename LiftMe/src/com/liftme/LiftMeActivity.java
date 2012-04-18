package com.liftme;

import com.liftme.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class LiftMeActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Reusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, RankActivity.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("rank").setIndicator("",res.getDrawable(R.drawable.ic_tab_group))
                      .setContent(intent);
        tabHost.addTab(spec);

     // Do the same for the other tabs
        intent = new Intent().setClass(this, MyMapActivity.class);
        spec = tabHost.newTabSpec("map").setIndicator("",res.getDrawable(R.drawable.ic_tab_map))
                          .setContent(intent);
        tabHost.addTab(spec);
        
     // Do the same for the other tabs
        intent = new Intent().setClass(this, MeActivity.class);
        spec = tabHost.newTabSpec("me").setIndicator("",res.getDrawable(R.drawable.ic_tab_user))
                          .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
}