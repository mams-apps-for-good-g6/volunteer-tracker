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

public class StudentListRecyclerView extends AppCompatActivity {
    //Data
    private ArrayList<LogEntry> students;
    private static final String TAG = "MainActivity";
    boolean bool;
    String orgPath;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_list);
        Log.d(TAG, "onCreate: started");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        orgPath = bundle.getString("orgPath");


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("organizations/" + orgPath + "/volunteers/");

        Log.d("MeganTag", "I am here 1");

        // Attach a listener to read the data at the refererence
        //ref.addListenerForSingleValueEvent(new ValueEventListener() {
           // @Override
           // public void onDataChange(DataSnapshot dataSnapshot) {
            //    Log.d("MeganTag", "I am here 2");
            //    Volunteer vol = dataSnapshot.getValue(Volunteer.class);
            //    logEntries = vol.getLogEntries();
            //    bool=true;
            //}

          //  @Override
          //  public void onCancelled(DatabaseError databaseError) {
          //      System.out.println("The read failed: " + databaseError.getCode());
          //  }
        //});

        Log.d("MeganTag", "I am here 4");

        if(bool) {
            initLogEntries();
        }
    }

    private void initLogEntries()
    {
        Log.d(TAG, "initLogEntries: preparing log entries");

//        for(int i = 0; i < 25; i++) {
//            DateFormat dateFormat = new SimpleDateFormat("yyyy.MM/dd");
//            Date date = new Date();
//            Volunteer volunteer = new Volunteer(Character.toString((char) i), (double) i, dateFormat.format(date), Character.toString((char) i), Character.toString((char) i), "path");
//            logEntries.add(entry);
//        }

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG,"initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.student_list_recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, students);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void toAdvisorProfile(View v)
    {
        Intent intent = new Intent(this, AdvisorProfile.class);
        intent.putExtra("orgPath", orgPath);
        startActivity(intent);
    }
}