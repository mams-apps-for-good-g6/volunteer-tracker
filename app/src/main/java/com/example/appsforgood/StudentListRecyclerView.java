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
    private static final String TAG = "MainActivity";
    String orgPath;
    private ArrayList<Volunteer> volunteers;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_list);
        Log.d(TAG, "onCreate: started");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        orgPath = bundle.getString("orgPath");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("organizations/" + orgPath);

        Log.d("MeganTag", "I am here 1");

        // Attach a listener to read the data at the refererence
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("MeganTag", "I am here 2");
                Organization org = dataSnapshot.getValue(Organization.class);
                volunteers = org.getVolunteers();
                initRecyclerView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        Log.d("MeganTag", "I am here 4");
    }

    private void initRecyclerView(){
        Log.d(TAG,"initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.student_list_recycler_view);
        StudentListAdapter adapter = new StudentListAdapter(this, volunteers, orgPath);
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