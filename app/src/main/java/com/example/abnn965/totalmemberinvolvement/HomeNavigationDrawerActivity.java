package com.example.abnn965.totalmemberinvolvement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeNavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
            String casEmail;
            String castedEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        casEmail = intent.getStringExtra("email");

    }

    public void onClickCaptureTarget(View view){

        String cEmail = casEmail;
        Intent captureTargetIntent = new Intent(HomeNavigationDrawerActivity.this, CaptureTargetActivity.class);
        captureTargetIntent.putExtra("email", cEmail);
        startActivity(captureTargetIntent);
    }

    public void onClickCheckInTarget(View view){

        String cEmail = casEmail.toString();
        Intent checkInTargetIntent = new Intent(HomeNavigationDrawerActivity.this, CheckInTargetActivity.class);
        checkInTargetIntent.putExtra("email", cEmail);
        startActivity(checkInTargetIntent);
        Toast.makeText(this, ""+cEmail,Toast.LENGTH_LONG).show();
    }

    public void onClickViewTargets(View view){

        String cEmail = casEmail.toString();
        Intent viewTargetIntent = new Intent(HomeNavigationDrawerActivity.this, ViewTargetActivity.class);
        viewTargetIntent.putExtra("email", cEmail);
        startActivity(viewTargetIntent);
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
        getMenuInflater().inflate(R.menu.home_navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.capture_target) {
            String cEmail = casEmail;
            Intent captureTargetIntent = new Intent(HomeNavigationDrawerActivity.this, CaptureTargetActivity.class);
            captureTargetIntent.putExtra("email", cEmail);
            startActivity(captureTargetIntent);

        }
        else if (id == R.id.check_in_target) {
            String cEmail = casEmail.toString();
            Intent checkInTargetIntent = new Intent(HomeNavigationDrawerActivity.this, CheckInTargetActivity.class);
            checkInTargetIntent.putExtra("email", cEmail);
            startActivity(checkInTargetIntent);
            Toast.makeText(this, ""+cEmail,Toast.LENGTH_LONG).show();
        }
        else if (id == R.id.view_target) {
            String cEmail = casEmail.toString();
            Intent viewTargetIntent = new Intent(HomeNavigationDrawerActivity.this, ViewTargetActivity.class);
            viewTargetIntent.putExtra("email", cEmail);
            startActivity(viewTargetIntent);
        }
        else if (id == R.id.logout) {
            Intent logoutIntent = new Intent(HomeNavigationDrawerActivity.this, MainLoginActivity.class);
            startActivity(logoutIntent);
            this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
