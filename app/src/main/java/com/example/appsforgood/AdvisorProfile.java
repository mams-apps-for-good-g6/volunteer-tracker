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
import java.util.HashMap;
import java.util.Map;

public class AdvisorProfile extends AppCompatActivity
{
    private String TAG = "EvanTag";
    private String orgPath = "";
    private ArrayList<LogEntry> verifyLogsList;
    private Context context;

    private void setKey(String k)
    {
        orgPath = k;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advisor_profile);

        context = this.getBaseContext();

        verifyLogsList = new ArrayList();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String orgPath = bundle.getString("orgPath");

        setKey(orgPath);

        //ADVISOR PROFILE BETTER

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("organizations/" + orgPath);

        // Attach a listener to read the data at the reference
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Organization org = dataSnapshot.getValue(Organization.class);

                Log.d(TAG, "1The code is: " + org.getCode());
                Log.d(TAG, "1The name is: " + org.getName());

                TextView advisorName = findViewById(R.id.AdvisorName);
                advisorName.setText(org.getAdvisor().getFullName());

                TextView email = findViewById(R.id.AdvisorEmail);
                email.setText(org.getAdvisor().getEmail());

                TextView orgName = findViewById(R.id.AdvisorOrganization);
                orgName.setText(org.getName());

                TextView code = findViewById(R.id.AdvisorOrganizationCode);
                code.setText(org.getCode());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
                Log.d(TAG,"Checkpoint #7");
            }
        });

    }

    public void toStudentList(View v)
    {
        Intent intent = new Intent(this, StudentListRecyclerView.class);
        intent.putExtra("orgPath", orgPath);
        startActivity(intent);
    }

    public void toVerifyList(View v) {
        //Intent intent = new Intent(this, HourLog.class);
        //startActivity(intent);
        Log.d("EvanTag", "OrgPath is: " + orgPath);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("organizations/" + orgPath);

        // Attach a listener to read the data at the reference
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Organization org = dataSnapshot.getValue(Organization.class);

                Log.d(TAG, "2The code is: " + org.getCode());
                Log.d(TAG, "2The name is: " + org.getName());

                ArrayList<Volunteer> volunteers = org.getVolunteers();

                for(Volunteer vol : volunteers)
                {
                    Log.d("Megan", "Getting vol: " + vol.getFullName());
                    for(LogEntry log : vol.getLogEntries())
                    {
                        Log.d("Megan", "Getting entry: " + log.getCharityName());
                        if(log.getApprovalStatus() == 0)
                        {
                            Log.d("Megan", "Getting status: " + log.getStringApprovalStatus());
                            verifyLogsList.add(log);
                        }
                    }
                }

                Intent intent2 = new Intent(context, VerifyHours.class);
                Log.d("Megan", "logs list size: " + Integer.toString(verifyLogsList.size()));
                intent2.putExtra("verifyLogsList", verifyLogsList);
                startActivity(intent2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
                Log.d(TAG,"Checkpoint #7");
            }
        });

    }
}