package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class HourLog extends AppCompatActivity
{
        private RecyclerView recyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager layoutManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.hour_log);
            recyclerView = (RecyclerView) findViewById(R.id.HourLogList);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            recyclerView.setHasFixedSize(true);

            // use a linear layout manager
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            // specify an adapter (see also next example)
            //mAdapter = new MyAdapter(myDataset);
            recyclerView.setAdapter(mAdapter);
        }

    public void toVolunteerProfile(View v)
    {
        Intent intent = new Intent(this, VolunteerProfile.class);
        startActivity(intent);
    }

    public void toLogHours(View v)
    {
        Intent intent = new Intent(this, LogHours.class);
        startActivity(intent);
    }
}
