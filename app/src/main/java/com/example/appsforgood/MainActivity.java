package com.example.appsforgood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(getApplicationContext());

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }

    public void toVolunteerSignUp(View v)
    {
        Intent intent = new Intent(this, VolunteerSignUp.class);
        startActivity(intent);
    }

    public void toAdvisorSignUp(View v)
    {
        Intent intent = new Intent(this, AdvisorSignUp.class);
        startActivity(intent);
    }

    public void displayCode(View v)
    {
        // take input and generate code by creating an organization object and calling the generateCode method
        EditText OrgName = findViewById(R.id.organizationName);
        EditText first = findViewById(R.id.firstName);
        EditText last = findViewById(R.id.lastName);
        EditText email = findViewById(R.id.email);

        String OrgNameStr = OrgName.getText().toString();
        String firstStr = first.getText().toString();
        String lastStr = last.getText().toString();
        String emailStr = email.getText().toString();

        Organization org = new Organization(OrgNameStr, new User(firstStr, lastStr, emailStr));
        int code = org.getCode();
        Intent intent = new Intent(this, DisplayCode.class);
        intent.putExtra("organizationCode", code);
        startActivity(intent);
    }
}
