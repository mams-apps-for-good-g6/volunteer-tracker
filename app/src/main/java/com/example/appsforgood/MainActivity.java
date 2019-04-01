package com.example.appsforgood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(getApplicationContext());
    }

    public void toVolunteerSignUp(View v)
    {
        Intent intent = new Intent(this, VolunteerSignUp.class);
        startActivity(intent);
    }

    public void toAdvisorSignUp(View v)
    {
        Intent intent = new Intent(this, AdvisorSignUp.class);
        startActivity(intent);
    }
}
