package com.example.rucha.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Rucha on 2/10/2016.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener,ActivityCompat.OnRequestPermissionsResultCallback {

    public int userid;
    public String uid;
    private static final int REQUEST_GPS = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.Blogin) {

            //Read the username and password from the textbox and convert to string
            EditText a = (EditText) findViewById(R.id.TFuser);
            String email = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.TFpassword);
            String pwd = b.getText().toString();

            if ((!email.equals("")) && (!pwd.equals(""))) {

                Json Js = new Json();
                int res = Js.sendJson(email, pwd);

                //Check with httpresult
                if (res == 200) {

                    Intent i = new Intent(this, MapsActivity.class);
                    i.putExtra("Username", email);
                    startActivity(i);

                } else {
                    Toast.makeText(this, "Passwords do not Match, Try again", Toast.LENGTH_SHORT).show(); //Pop-up
                }
            }
            if ((email == null || email.equals(""))) {
                Toast.makeText(getApplicationContext(),
                        "Enter email", Toast.LENGTH_SHORT).show();
            } else if ((pwd == null && pwd.equals(""))) {
                Toast.makeText(getApplicationContext(),
                        "Enter Password", Toast.LENGTH_SHORT).show();
            }


        }
        if (v.getId() == R.id.Bsignuph) {

            Intent i = new Intent(this, trial.class);
            startActivity(i);
               /* Intent i = new Intent(this,SignUp.class);
                startActivity(i);*/
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Camera permission has not been granted.

            requestGpsPermission();
        }

            super.onCreate(savedInstanceState);
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            setContentView(R.layout.activity_main);

            // ATTENTION: This was auto-generated to implement the App Indexing API.
            // See https://g.co/AppIndexing/AndroidStudio for more information.
            client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.rucha.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.rucha.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private void requestGpsPermission() {

        Log.e("for gps", "GPS permission has NOT been granted. Requesting permission.");

        // BEGIN_INCLUDE(camera_permission_request)
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            Log.e("For Gps",
                    "Displaying GPS permission rationale to provide additional context.");
        } else {

            // Camera permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_GPS);
        }
        // END_INCLUDE(camera_permission_request)

    }
}
