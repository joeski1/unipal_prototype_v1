package com.example.joe.unipal.profile_match;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.widget.FrameLayout;

import com.example.joe.unipal.BaseActivity;
import com.example.joe.unipal.R;
import com.example.joe.unipal.communities.CommunitiesActivity;

public class ProfileMatchActivity extends BaseActivity {

    private static final String LOG_TAG = CommunitiesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within activity_main.xml
        getLayoutInflater().inflate(R.layout.content_profile_match, contentFrameLayout);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_profile, menu);
        return true;
    }
}
