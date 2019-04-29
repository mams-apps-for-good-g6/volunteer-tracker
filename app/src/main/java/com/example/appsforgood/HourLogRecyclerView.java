package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HourLogRecyclerView extends AppCompatActivity {
    //Data
    private ArrayList<LogEntry> logEntries = new ArrayList<>();
    private static final String TAG = "MainActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hour_log);
        Log.d(TAG, "onCreate: started");

        initLogEntries();
    }

    private void initLogEntries()
    {
        Log.d(TAG, "initLogEntries: preparing log entries");

        for(int i = 0; i < 25; i++) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy.MM/dd HH:mm:ss");
            Date date = new Date();
            LogEntry entry = new LogEntry(Character.toString((char) i), (double) i, dateFormat.format(date), Character.toString((char) i), Character.toString((char) i));
            logEntries.add(entry);
        }

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG,"initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.HourLogList);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, logEntries);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
