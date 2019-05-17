package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Allows the volunteer to log service hours and adds it to their hour log.
 */
public class LogHours extends AppCompatActivity
{
    String orgPath;
    int index;
    boolean wait;
    LogEntry log;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_hours);

        // Receiving the organization path through an intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        orgPath = bundle.getString("orgPath");

        // Receiving the volunteer's index through an intent
        Intent intent2 = getIntent();
        Bundle bundle2 = intent2.getExtras();
        index = bundle2.getInt("volIndex");
        }

    /**
     * Gets the entered information and adds the entry to the user's ArrayList of log entries.
     * @param v
     */
    public void logHours(View v)
    {
        Log.d("EvanTag", "We are here #1");

        wait = false;

        // Get the inputted values

        EditText charityName = findViewById(R.id.charityName);
        EditText hours = findViewById(R.id.hours);
        EditText date = findViewById(R.id.date);
        EditText contactPerson = findViewById(R.id.contactPerson);
        EditText contactEmail = findViewById(R.id.contactEmail);

        String charityNameStr = charityName.getText().toString();
        if (charityNameStr.isEmpty()) {
            charityNameStr += " ";
            Toast.makeText(this, "Invalid charity name. Please enter a charity name.", Toast.LENGTH_LONG).show();
            wait=true;
        }

        Double hoursDouble = 0.0;
        if (!hours.getText().toString().isEmpty()) {
            hoursDouble = Double.parseDouble(hours.getText().toString());
        }
        else {
            Toast.makeText(this, "Invalid hour entry. Please enter your hours.", Toast.LENGTH_LONG).show();
            wait=true;
        }

        String dateStr = date.getText().toString();
        if (dateStr.isEmpty()) {
            dateStr+=" ";
            wait=true;
            Toast.makeText(this, "Invalid date. Please enter the date of service.", Toast.LENGTH_LONG).show();
        }

        String contactPersonStr = contactPerson.getText().toString();
        if (contactPersonStr.isEmpty()) {
            contactPersonStr+=" ";
            wait=true;
            Toast.makeText(this, "Invalid contact name. Please enter a contact person.", Toast.LENGTH_LONG).show();
        }
        String contactEmailStr = contactEmail.getText().toString();
        if (contactEmailStr.isEmpty()) {
            contactEmailStr+=" ";
            wait=true;
            Toast.makeText(this, "Invalid email. Please enter a contact email.", Toast.LENGTH_LONG).show();
        }


        Log.d("EvanTag", "We are here #2");

        // Create a new LogEntry from the inputted data

        log = new LogEntry(charityNameStr, hoursDouble, dateStr, contactPersonStr, contactEmailStr, "organizations/" + orgPath + "/volunteers/" + index + "/logEntries/", "");


        Log.d("EvanTag", "We are here #3");

        // References the database and creates a path to the volunteer
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("organizations/" + orgPath + "/volunteers/" + index);

        Log.d("EvanTag", "organizations/" + orgPath + "/volunteers/" + index);
        Log.d("EvanTag", "We are here #4");

        if (!wait) {
            // Gets the volunteer at the specified path and adds the LogEntry to their ArrayList of LogEntries
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Volunteer vol = dataSnapshot.getValue(Volunteer.class);
                    vol.addLogEntry(log);
                    log.setPath(orgPath + "/volunteers/" + vol.getIndex() + "/logEntries/" + Integer.toString(log.getIndex()));
                    log.setOrgPath(orgPath);
                    log.setVolunteerName(vol.getFullName());
                    vol.setLogEntry(log.getIndex(), log);
                    dataSnapshot.getRef().setValue(vol);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                    Log.d("EvanTag", "We are here #6.5 (BAD!!)");
                }
            });
        }

        // END OF THE BIG PASTE HERE

        Log.d("EvanTag", "We are here #7");


        // After hours are logged, user is sent to their profile

        if (!wait) {
            Toast.makeText(this, "Your hours have been logged.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, VolunteerProfile.class);
            intent.putExtra("orgPath", orgPath);
            intent.putExtra("volIndex", index);
            startActivity(intent);
        }
    }
}