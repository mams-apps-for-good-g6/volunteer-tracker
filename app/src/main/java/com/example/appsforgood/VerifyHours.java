package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class VerifyHours extends AppCompatActivity
{


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_hours);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ArrayList<LogEntry> verifyLogsList = (ArrayList<LogEntry>) bundle.getSerializable("verifyLogsList");

        // Recycle view thiss
    }

}
