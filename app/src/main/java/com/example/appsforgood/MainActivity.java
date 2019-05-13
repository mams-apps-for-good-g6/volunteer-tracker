package com.example.appsforgood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * The first screen that the user sees when they open the app.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate: started");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(getApplicationContext());
    }

    /**
     * Sends the user to sign up as a volunteer.
     * @param v
     */
    public void toVolunteerSignUp(View v)
    {
        Intent intent = new Intent(this, VolunteerSignUp.class);
        startActivity(intent);
    }

    /**
     * Sends the user to sign up as an advisor.
     * @param v
     */
    public void toAdvisorSignUp(View v)
    {
        Intent intent = new Intent(this, AdvisorSignUp.class);
        startActivity(intent);
    }

    /**
     * Sends the user to sign in (can sign in as an advisor or volunteer).
     * @param v
     */
    public void toSignIn(View v)
    {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }
}
