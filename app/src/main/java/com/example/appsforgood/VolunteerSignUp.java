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

public class VolunteerSignUp extends AppCompatActivity
{
    String codeStr;

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

        Volunteer vol = new Volunteer(firstStr, lastStr, emailStr);

        // Find organization with the user's code in firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("organizations/-LcgBkef_KITw7C3pRpw");
        DatabaseReference volRef = ref.child("volunteers");
        volRef.push().setValue(vol);

        // Add volunteer to this organization (NEED TO COMPLETE)

        Intent intent = new Intent(this, VolunteerProfile.class);
        startActivity(intent);
    }
}
