package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Displays the volunteer's Hour Log in a recylcer view
 */
public class HourLog extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    String orgPath;
    int index;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.hour_log);

            // Receiving the organization path through an intent.
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            orgPath = bundle.getString("orgPath");

            // Receiving the volutneer's index through an intent
            Intent intent2 = getIntent();
            Bundle bundle2 = intent2.getExtras();
            index = bundle2.getInt("volIndex");

            recyclerView = findViewById(R.id.HourLogList);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.setAdapter(mAdapter);
        }

    /**
     * Send the volunteer back to their profile.
     * @param v
     */
    public void toVolunteerProfile(View v)
    {
        Intent intent = new Intent(this, VolunteerProfile.class);
        intent.putExtra("orgPath", orgPath);
        intent.putExtra("volIndex", index);
        startActivity(intent);
    }

    /**
     * Sends the volunteer to the Log Hours page.
     * @param v
     */
    public void toLogHours(View v)
    {
        Intent intent = new Intent(this, LogHours.class);
        intent.putExtra("orgPath", orgPath);
        intent.putExtra("volIndex", index);
        startActivity(intent);
    }
}
