package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

        Log.d("EvanTag", "We are here #2");

        // Create a new LogEntry from the inputted data

        log = new LogEntry(charityNameStr, hoursDouble, dateStr, contactPersonStr, contactEmailStr, "organizations/" + orgPath + "/volunteers/" + index + "/logEntries/", "");


        Log.d("EvanTag", "We are here #3");

        // References the database and creates a path to the volunteer
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("organizations/" + orgPath + "/volunteers/" + index);

        Log.d("EvanTag", "organizations/" + orgPath + "/volunteers/" + index);
        Log.d("EvanTag", "We are here #4");

        // Gets the volunteer at the specified path and adds the LogEntry to their ArrayList of LogEntries
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                        Volunteer vol = dataSnapshot.getValue(Volunteer.class);
                        vol.addLogEntry(log);
                        log.setPath(orgPath + "/volunteers/" + vol.getIndex() + "/logEntries/" + Integer.toString(log.getIndex()));
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

        // END OF THE BIG PASTE HERE

        Log.d("EvanTag", "We are here #7");


        // After hours are logged, user is sent to their profile
        Intent intent = new Intent(this, VolunteerProfile.class);
        intent.putExtra("orgPath", orgPath);
        intent.putExtra("volIndex", index);
        startActivity(intent);
    }
}