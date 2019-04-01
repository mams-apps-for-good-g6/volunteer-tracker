package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class VolunteerProfile extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_profile);
    }

    public void toLogHours(View v)
    {
        Intent intent = new Intent(this, LogHours.class);
        startActivity(intent);
    }
}
