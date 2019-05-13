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

/**
 * The recycler view that displays a volunteer's Hour Log
 */
public class HourLogRecyclerView extends AppCompatActivity {
    //Data
    private ArrayList<LogEntry> logEntries;
    private static final String TAG = "MainActivity";
    private String orgPath;
    private int index;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hour_log);
        Log.d(TAG, "onCreate: started");

        // Receiving the volunteer's index through an intent
        Intent intent1 = getIntent();
        Bundle bundle1 = intent1.getExtras();
        index = bundle1.getInt("index");

        Log.d("Megan", "Index gotten from intent in hour log: " + index);

        // Receiving the organization path through an intent
        Intent intent2 = getIntent();
        Bundle bundle2 = intent2.getExtras();
        orgPath = bundle2.getString("orgPath");

        // Receiving the volunteer's Log Entries arrayList through an intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        logEntries = bundle.getParcelableArrayList("logEntries");
        initRecyclerView();
    }

    /**
     * Initializes the recycler view
     */
    private void initRecyclerView(){
        Log.d(TAG,"initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.HourLogList);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, logEntries);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Sends the volunteer back to their profile
     * @param v
     */
    public void toVolunteerProfile(View v) {
        Intent intent = new Intent(this, VolunteerProfile.class);
        intent.putExtra("orgPath", orgPath);
        intent.putExtra("volIndex", index);
        Log.d("MeganTag", "In HourLogRecyclerView, index: " + index);
        startActivity(intent);
    }

    /**
     * Sends the volunteer to the Log Hours page
     * @param v
     */
    public void toLogHours(View v) {
        Intent intent = new Intent(this, LogHours.class);
        intent.putExtra("orgPath", orgPath);
        intent.putExtra("volIndex", index);
        startActivity(intent);
    }
}
