package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VolunteerProfile extends AppCompatActivity {

    String orgPath;
    int index;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_profile);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        orgPath = bundle.getString("orgPath");

        Intent intent2 = getIntent();
        Bundle bundle2 = intent2.getExtras();
        index = bundle2.getInt("volIndex");

        Log.d("MeganTag", "organizations/" + orgPath + "/volunteers/" + Integer.toString(index));

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("organizations/" + orgPath + "/volunteers/" + Integer.toString(index));

        // Attach a listener to read the data at the reference
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Volunteer vol = dataSnapshot.getValue(Volunteer.class);

                TextView verifiedHours = findViewById(R.id.volunteerVerifiedHours);
                verifiedHours.setText(Double.toString(vol.getTotalHours()));

                TextView name = findViewById(R.id.VolunteerName);
                name.setText(vol.getFullName());

                TextView email = findViewById(R.id.VolunteerEmail);
                email.setText(vol.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void toLogHours(View v) {
        Intent intent = new Intent(this, LogHours.class);
        intent.putExtra("studentId","Student Name Here (that will allow us to find the log entry on the next page in firebase, so some unique identifer");
        startActivity(intent);
    }

    public void toHourLog(View v) {
        Intent intent = new Intent(this, HourLog.class);
        startActivity(intent);
    }
}