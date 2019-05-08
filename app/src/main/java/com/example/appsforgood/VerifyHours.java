package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class VerifyHours extends AppCompatActivity
{


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_hours);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ArrayList<LogEntry> verifyLogsList = (ArrayList<LogEntry>) bundle.getSerializable("verifyLogsList");

        String print = new String("\n");

        for(LogEntry log: verifyLogsList)
        {
            print = print + log.getPath() + "\n";
        }

        Log.d("EvanTag", "This is our Array List: " + print);

        // Recycle view thisss
    }

}
