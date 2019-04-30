package com.example.appsforgood;

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
import java.util.HashMap;
import java.util.Map;

public class AdvisorProfile extends AppCompatActivity
{
    private String TAG = "EvanTag";
    private String orgPath = "";
    private ArrayList<LogEntry> verifyLogsList = new ArrayList<>();

    private void setKey(String k)
    {
        orgPath = k;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advisor_profile);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String orgPath = bundle.getString("orgPath");

        setKey(orgPath);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("organizations/");

        // Attach a listener to read the data at the reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot datasnapshot: dataSnapshot.getChildren()) {
                    Organization org = datasnapshot.getValue(Organization.class);
                    Log.d(TAG, org.toString());

                    TextView advisorName = findViewById(R.id.AdvisorName);
                    advisorName.setText(org.getAdvisor().getFullName());

                    TextView email = findViewById(R.id.AdvisorEmail);
                    email.setText(org.getAdvisor().getEmail());

                    TextView orgName = findViewById(R.id.AdvisorOrganization);
                    orgName.setText(org.getName());

                    TextView code = findViewById(R.id.AdvisorOrganizationCode);
                    code.setText(org.getCode());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
                Log.d(TAG,"Checkpoint #7");
            }
        });

    }

    public void toHourLog(View v) {
        Intent intent = new Intent(this, HourLogRecyclerView.class);
        startActivity(intent);
    }

    public void toVerifyList(View v) {
        //Intent intent = new Intent(this, HourLog.class);
        //startActivity(intent);
        Log.d("EvanTag", "OrgPath is: " + orgPath);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("organizations/");

        // Attach a listener to read the data at the reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot datasnapshot: dataSnapshot.getChildren()) {
                    Organization org = datasnapshot.getValue(Organization.class);
                    Log.d(TAG, org.toString());

                    ArrayList<Volunteer> volunteers = org.getVolunteers();

                    for(Volunteer vol : volunteers)
                    {
                        for(LogEntry log : vol.getLogEntries())
                        {
                            if(log.getApprovalStatus() == 0)
                            {
                                verifyLogsList.add(log);
                            }
                        }
                    }

                    // Now we have the arraylist full or LogEntries that are pending

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
                Log.d(TAG,"Checkpoint #7");
            }
        });


        // This is still the same method

        // Leave this class, and go to a new XML that's a recycler view of the arraylist I'm sending it
        Intent intent = new Intent(this, VerifyHours.class);
        intent.putExtra("verifyLogsList", verifyLogsList);
        startActivity(intent);
    }
}