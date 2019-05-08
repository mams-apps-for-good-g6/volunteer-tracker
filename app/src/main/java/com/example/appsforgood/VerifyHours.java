package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class VerifyHours extends AppCompatActivity
{

    private ArrayList<LogEntry> verifyLogsList;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_hours);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        verifyLogsList = (ArrayList<LogEntry>) bundle.getSerializable("verifyLogsList");
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d("TAG","initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.verify_hours_recycler_view);
        VerifyHoursAdapter adapter = new VerifyHoursAdapter(this, verifyLogsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
