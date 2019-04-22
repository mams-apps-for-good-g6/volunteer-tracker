package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class VolunteerSignUp extends AppCompatActivity
{
    String codeStr;
    Volunteer vol;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_signup);
    }

    public void toVolunteerProfile(View v)
    {
        // Convert user input to String objects

        EditText code = findViewById(R.id.organizationCode);
        EditText first = findViewById(R.id.firstName);
        EditText last = findViewById(R.id.lastName);
        EditText email = findViewById(R.id.email);

        codeStr = code.getText().toString();
        String firstStr = first.getText().toString();
        String lastStr = last.getText().toString();
        String emailStr = email.getText().toString();

        // Create a volunteer using inputted information

        vol = new Volunteer(firstStr, lastStr, emailStr);

        // Find organization with the user's code in firebase

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("organizations/");

        // Attach a listener to read the data at the reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Organization org = ds.getValue(Organization.class);
                    if (org.getCode().equals(codeStr)) {
                        org.addVolunteer(vol);
                        ds.getRef().setValue(org);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        // Add volunteer to proper organization

        //FirebaseDatabase database2 = FirebaseDatabase.getInstance();
       // DatabaseReference ref2 = database2.getReference("organizations/-Lcqnd6a2mUMUFdEWdFP");
       // DatabaseReference volRef = ref2.child("volunteers");
       // volRef.push().setValue(vol);

        // Add volunteer to this organization (NEED TO COMPLETE)

        Intent intent = new Intent(this, VolunteerProfile.class);
        startActivity(intent);
    }
}
