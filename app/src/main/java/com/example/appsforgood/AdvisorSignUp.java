package com.example.appsforgood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Allows an advisor to sign up and creates an Organization with the given Advisor
 */
public class AdvisorSignUp extends AppCompatActivity
{
    private String TAG = "EvanTag ";
    Context context;

    protected void onCreate(Bundle savedInstanceState)
    {
        context = this.getBaseContext();
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

        User user = new User(firstStr, lastStr, emailStr);

        if(OrgNameStr.isEmpty())
        {
            Toast.makeText(context, "Please enter an organization name", Toast.LENGTH_SHORT).show();
        }

        user.emptyToast(context);

        if (user.checkEmpty()==false)
        {
            // Create an organization using inputted information
            Organization org = new Organization(OrgNameStr, user);

            // Referencing the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("organizations");

            // Add organization to database under tag "organizations"
            DatabaseReference newRef = ref.push();
            newRef.setValue(org);
            String orgPath = newRef.getKey();
            Log.d("MeganTag", orgPath);

            // Sending the organization path through an intent
            Intent intent = new Intent(this, DisplayCode.class);
            intent.putExtra("orgPath", orgPath);
            startActivity(intent);
        }
    }
}