package com.example.appsforgood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * The volunteer profile which displays the volunteer's information
 */
public class VolunteerProfile extends AppCompatActivity {

    String orgPath;
    int index;
    ArrayList<LogEntry> logEntries;
    Context context;

    /**
     * Assigns information for the XML to display
     * @param savedInstanceState information received from the volunteer sign up class
     */
    protected void onCreate(Bundle savedInstanceState) {
        context = this.getBaseContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_profile);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        orgPath = bundle.getString("orgPath");

        Intent intent2 = getIntent();
        Bundle bundle2 = intent2.getExtras();
        index = bundle2.getInt("volIndex");

        Log.d("MeganTag", "organizations/" + orgPath + "/volunteers/" + Integer.toString(index));

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("organizations/" + orgPath + "/volunteers/" + Integer.toString(index));

        // Attach a listener to read the data at the reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Volunteer vol = dataSnapshot.getValue(Volunteer.class);

                // Verified Hours
                double verHours = 0;
                double declinedHours=0;
                double pendingHours=0;

                for(LogEntry inLog: vol.getLogEntries())
                {
                    if(inLog.getApprovalStatus() == 1)
                    {
                      verHours = verHours + inLog.getHours();
                    }
                    if(inLog.getApprovalStatus() == -1)
                    {
                        declinedHours = declinedHours + inLog.getHours();
                    }
                    if(inLog.getApprovalStatus() == 0)
                    {
                        pendingHours = pendingHours + inLog.getHours();
                    }
                }

                // Verified Hours
                TextView verifiedHours = findViewById(R.id.volunteerVerifiedHours);
                verifiedHours.setText(Double.toString(verHours));

                // Pending Hours
                TextView pending = findViewById(R.id.volunteerPendingHours);
                pending.setText(Double.toString(pendingHours));

                // Declined Hours
                TextView declined = findViewById(R.id.volunteerDeclinedHours);
                declined.setText(Double.toString(declinedHours));

                // Name
                TextView name = findViewById(R.id.VolunteerName);
                name.setText(vol.getFullName());

                // Email
                TextView email = findViewById(R.id.VolunteerEmail);
                email.setText(vol.getEmail());

                // Organization
                TextView volOrg = findViewById(R.id.VolunteerOrganization);
                volOrg.setText(vol.getOrgName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    /**
     * Sends volunteer to the Log Hours page to allows them to create an hour entry for the advisor to approve
     * Sends data (organization path and the index of the volunteer within the organization's ArrayList) to the LogHours page
     * @param v an instance fo the View class
     */
    public void toLogHours(View v) {
        Intent intent = new Intent(this, LogHours.class);
        intent.putExtra("orgPath", orgPath);
        Log.d("Megan", "sending index to log hours: " + Integer.toString(index));
        intent.putExtra("volIndex", index);
        startActivity(intent);
    }

    /**
     * Sends the volunteer to their existing hour log recycler view which
     * the hour log recycler view contains all existing hour log entries
     * @param v an instance of the View class
     */
    public void toHourLogRecyclerView(View v) {

        Log.d("Megan", "index after clicking recycler view: " + Integer.toString(index));

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("organizations/" + orgPath + "/volunteers/" + Integer.toString(index));

        Log.d("MeganTag", "I am here 1");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("MeganTag", "I am here 2");
                Volunteer vol = dataSnapshot.getValue(Volunteer.class);
                logEntries = vol.getLogEntries();

                Intent intent = new Intent(context, HourLogRecyclerView.class);
                intent.putParcelableArrayListExtra("logEntries", logEntries);
                intent.putExtra("orgPath", orgPath);
                intent.putExtra("index", index);
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}