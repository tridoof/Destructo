package com.samuel.destructo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;

import android.util.Log;


public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Parse.com analytics
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        //When a user is logged in, condition is stored in cache
        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser == null){
            navigateToLogin();
        }
        else{
            Log.i(TAG, currentUser.getUsername());
        }
    }

    private void navigateToLogin() {
        //Go to log in activity
        Intent intent = new Intent(this, LoginActivity.class);
        //A flag is needed to skip the MainAcitivty from showing up?
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Clear the task so that we cant back arrow into it
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int itemId = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (itemId == R.id.action_logout) {
            //The user has tapped on the log out option
            ParseUser.logOut();
            //Push user back to the login screen
            navigateToLogin();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
