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

import java.util.HashMap;
import java.util.Map;

public class AdvisorProfile extends AppCompatActivity
{
    private String TAG = "EvanTag";
    private String key = "";

    private void setKey(String k)
    {
        key = k;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advisor_profile);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String key = bundle.getString("organizationKey");

        setKey(key);

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
        Intent intent = new Intent(this, HourLog.class);
        startActivity(intent);
    }
}