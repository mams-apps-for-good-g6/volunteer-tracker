package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HourLog extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hour_log);
    }

    public void toVolunteerProfile(View v)
    {
        Intent intent = new Intent(this, VolunteerProfile.class);
        startActivity(intent);
    }
}
