package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class LogHours extends AppCompatActivity
{
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.log_hours);
//    }

    public void logHours(View v)
    {
        // Get the inputted values
        EditText charityName = findViewById(R.id.charityName);
        EditText hours = findViewById(R.id.hours);
        EditText date = findViewById(R.id.date);
        EditText contactPerson = findViewById(R.id.contactPerson);
        EditText contactEmail = findViewById(R.id.contactEmail);

        String charityNameStr = charityName.getText().toString();
        Double hoursDouble = Double.parseDouble(hours.getText().toString());
        String dateStr = date.getText().toString();
        String contactPersonStr = contactPerson.getText().toString();
        String contactEmailStr = contactEmail.getText().toString();

        // Create a new LogEntry from the inputted data
        LogEntry log = new LogEntry(charityNameStr, hoursDouble, dateStr, contactPersonStr, contactEmailStr);

        //Send it off to firebase



        // After hours are logged, user is sent to their hour log
        Intent intent = new Intent(this, HourLog.class);
        startActivity(intent);
    }
}