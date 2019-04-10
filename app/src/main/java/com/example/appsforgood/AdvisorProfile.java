package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private String code = "";

    private void setCode(String codeStr)
    {
        code = codeStr;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advisor_profile);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String orgCode = bundle.getString("organizationCode");

        setCode(orgCode);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("organizations/" + orgCode);

        // Attach a listener to read the data at the reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Organization> hashmap = dataSnapshot.getValue(HashMap.class);
                Organization org = hashmap.get(code);
                Log.d(TAG, org.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
                Log.d(TAG,"Checkpoint #7");
            }
        });

        TextView displayCode = findViewById(R.id.AdvisorName);
        displayCode.setText("name");

        TextView email = findViewById(R.id.AdvisorEmail);
        email.setText("email here");

        TextView orgName = findViewById(R.id.AdvisorOrganization);
        orgName.setText("org name");

        TextView code = findViewById(R.id.AdvisorOrganizationCode);
        code.setText("org code");
    }
}