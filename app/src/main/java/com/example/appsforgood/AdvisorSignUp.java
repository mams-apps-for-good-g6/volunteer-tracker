package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AdvisorSignUp extends AppCompatActivity
{
    private String TAG = "EvanTag";

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advisor_signup);
    }

    public void displayCode(View v)
    {
        // Convert user input to String objects

        EditText OrgName = findViewById(R.id.organizationName);
        EditText first = findViewById(R.id.firstName);
        EditText last = findViewById(R.id.lastName);
        EditText email = findViewById(R.id.email);

        String OrgNameStr = OrgName.getText().toString();
        String firstStr = first.getText().toString();
        String lastStr = last.getText().toString();
        String emailStr = email.getText().toString();

        // Create an organization using inputted information

        Organization org = new Organization(OrgNameStr, new User(firstStr, lastStr, emailStr));

        // Add organization to database under tag "organizations"

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("organizations");

        DatabaseReference usersRef = ref.child(org.getCode());
        usersRef.push().setValue(org);

        // Add org as a child of "organizations"
        //Map<String, Organization> orgs = new HashMap<>();
        //orgs.put(org.getCode(), org);
        //usersRef.setValue(orgs);

        Intent intent = new Intent(this, DisplayCode.class);
        intent.putExtra("organizationCode", org.getCode());
        startActivity(intent);
    }
}