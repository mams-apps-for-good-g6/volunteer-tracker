package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class VerifyHours extends AppCompatActivity
{

    private ArrayList<LogEntry> verifyLogsList;
    private String orgPath;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_hours);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        verifyLogsList = bundle.getParcelableArrayList("verifyLogsList");
        initRecyclerView();

        Intent intent2 = getIntent();
        Bundle bundle2 = intent2.getExtras();
        orgPath = bundle2.getString("orgPath");
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d("TAG","initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.verify_hours_recycler_view);
        VerifyHoursAdapter adapter = new VerifyHoursAdapter(this, verifyLogsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     *
     * @param v
     */
    public void toAdvisorProfile(View v)
    {
        Intent intent = new Intent(this, AdvisorProfile.class);
        intent.putExtra("orgPath", orgPath);
        startActivity(intent);
    }

}
