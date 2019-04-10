package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LogHours extends AppCompatActivity
{
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.log_hours);
//    }

    public void logHours(View v)
    {
        // DO STUFF HERE TO ADD HOURS TO HOUR LOG

        // After hours are logged, user is sent to their hour log
        Intent intent = new Intent(this, HourLog.class);
        startActivity(intent);
    }
}