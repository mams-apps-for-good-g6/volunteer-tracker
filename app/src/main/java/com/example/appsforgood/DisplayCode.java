package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Displays the Advisor's organization code
 */
public class DisplayCode extends AppCompatActivity
{private String TAG = "EvanTag";

    private String orgPath = "";

    private void setKey(String k) {orgPath = k;}

    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG,"Checkpoint #1");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_code);

        // Receiving the organization path through an intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String orgPath = bundle.getString("orgPath");
        setKey(orgPath);

        // References the database and creates a path to the organization's code
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("organizations/" + orgPath + "/code");

        // Getting the code of the organization at the specified path and displaying it.
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String code = dataSnapshot.getValue(String.class);
                Log.d(TAG, code);

                TextView displayCode = findViewById(R.id.displayCode);
                displayCode.setText(code);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    /**
     * Sends the Advisor to their profile
     * @param v
     */
    public void toAdvisorProfile(View v)
    {
        Intent intent = new Intent(this, AdvisorProfile.class);
        intent.putExtra("orgPath", orgPath);
        startActivity(intent);
    }

}