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

public class DisplayCode extends AppCompatActivity
{private String TAG = "EvanTag";

    private String code = "";

    private void setCode(String codeStr)
    {
        code = codeStr;
    }

    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG,"Checkpoint #1");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_code);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String orgCode = bundle.getString("organizationCode");

        setCode(orgCode);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("organizations/" + orgCode);

        // Attach a listener to read the data at the reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot datasnapshot: dataSnapshot.getChildren()) {
                    Organization org = datasnapshot.getValue(Organization.class);
                    Log.d(TAG, org.toString());

                    TextView displayCode = findViewById(R.id.displayCode);
                    displayCode.setText(org.getCode());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
                Log.d(TAG,"Checkpoint #7");
            }
        });
    }

    public void toAdvisorProfile(View v)
    {
        Intent intent = new Intent(this, AdvisorProfile.class);
        intent.putExtra("organizationCode", code);
        startActivity(intent);
    }

}