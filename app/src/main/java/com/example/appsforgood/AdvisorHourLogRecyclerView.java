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
 * Creates the recycler view for the Advisor to view the HourLog of a clicked on Volunteer.
 */
public class AdvisorHourLogRecyclerView extends AppCompatActivity {
    //Data
    private ArrayList<LogEntry> logEntries;
    private static final String TAG = "MainActivity";
    private String orgPath;
    private int index;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.advisor_hourlog);
        Log.d(TAG, "onCreate: started");

        Intent intent1 = getIntent();
        Bundle bundle1 = intent1.getExtras();
        index = bundle1.getInt("index");

        Log.d("Megan", "Index gotten from intent in hour log: " + index);

        Intent intent2 = getIntent();
        Bundle bundle2 = intent2.getExtras();
        orgPath = bundle2.getString("orgPath");

        Intent intent3 = getIntent();
        Bundle bundle3 = intent3.getExtras();
        String volName = bundle3.getString("volName");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        logEntries = bundle.getParcelableArrayList("logEntries");

        TextView name = findViewById(R.id.volunteer_name);
        name.setText(volName);

        initRecyclerView();
    }


    private void initRecyclerView(){
        Log.d(TAG,"initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.advisor_hourlog);
        AdvisorHourLogAdapter adapter = new AdvisorHourLogAdapter(this, logEntries);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void toStudentList(View v)
    {
        Intent intent = new Intent(this, StudentListRecyclerView.class);
        intent.putExtra("orgPath", orgPath);
        startActivity(intent);
    }
}
