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

        // THIS IS A BIG PASTE HERE

        final int check = 5; // This it the index of the signed in volunteer, which we would need to get

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("organizations/-Ld9uKTam5hwU1uK0CFb/volunteers");

        // Attach a listener to read the data at the reference
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Volunteer vol = ds.getValue(Volunteer.class);
                    Log.d("MeganTag", "We are here #1");
                    if (vol.getIndex() == check) {
                        //vol.addTheLogEntryHere
                        ds.getRef().setValue(vol);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        // END OF THE BIG PASTE HERE


        // After hours are logged, user is sent to their hour log
        Intent intent = new Intent(this, HourLog.class);
        startActivity(intent);
    }
}