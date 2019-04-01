package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VolunteerSignUp extends AppCompatActivity
{
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

        String codeStr = code.getText().toString();
        String firstStr = first.getText().toString();
        String lastStr = last.getText().toString();
        String emailStr = email.getText().toString();

        // Create a volunteer using inputted information

        Volunteer vol = new Volunteer(firstStr, lastStr, emailStr);

        // Get organization with the user's code (NEED TO COMPLETE)

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("organizations");


        // Add volunteer to this organization (NEED TO COMPLETE)

        Intent intent = new Intent(this, VolunteerProfile.class);
        startActivity(intent);
    }
}
