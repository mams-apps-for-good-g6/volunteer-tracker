package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HourLogRecyclerView extends AppCompatActivity {
    //Data
    private ArrayList<LogEntry> logEntries;
    private static final String TAG = "MainActivity";
    private String orgPath;
    private int index;
    private boolean bool;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hour_log);
        Log.d(TAG, "onCreate: started");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        orgPath = bundle.getString("orgPath");

        Intent intent2 = getIntent();
        Bundle bundle2 = intent2.getExtras();
        index = bundle2.getInt("volIndex");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("organizations/" + orgPath + "/volunteers/" + Integer.toString(index));

        Log.d("MeganTag", "I am here 1");

        // Attach a listener to read the data at the reference
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("MeganTag", "I am here 2");
                Volunteer vol = dataSnapshot.getValue(Volunteer.class);
                logEntries = vol.getLogEntries();
                bool=true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        Log.d("MeganTag", "I am here 3");

        if(bool) {
            initLogEntries();
        }
    }

    private void initLogEntries()
    {
        Log.d(TAG, "initLogEntries: preparing log entries");

        for(int i = 0; i < 25; i++) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy.MM/dd");
            Date date = new Date();
            LogEntry entry = new LogEntry(Character.toString((char) i), (double) i, dateFormat.format(date), Character.toString((char) i), Character.toString((char) i), "path");
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

    public void toVolunteerProfile(View v) {
        Intent intent = new Intent(this, VolunteerProfile.class);
        intent.putExtra("orgPath", orgPath);
        intent.putExtra("volIndex", index);
        startActivity(intent);
    }

    public void toLogHours(View v) {
        Intent intent = new Intent(this, LogHours.class);
        intent.putExtra("orgPath", orgPath);
        intent.putExtra("volIndex", index);
        startActivity(intent);
    }
}
