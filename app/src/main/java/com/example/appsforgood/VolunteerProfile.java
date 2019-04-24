package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class VolunteerProfile extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_profile);

        // Get the volunteer from Firebase



    }

    public void toLogHours(View v) {
        Intent intent = new Intent(this, LogHours.class);
        intent.putExtra("studentId","Student Name Here (that will allow us to find the log entry on the next page in firebase, so some unique identifer");
        startActivity(intent);
    }

    public void toHourLog(View v) {
        Intent intent = new Intent(this, HourLog.class);
        startActivity(intent);
    }
}