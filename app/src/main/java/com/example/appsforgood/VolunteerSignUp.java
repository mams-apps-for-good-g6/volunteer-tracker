package com.example.appsforgood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class VolunteerSignUp extends AppCompatActivity
{
    String codeStr;
    Volunteer vol;
    String orgPath;
    int index;
    Context context;

    protected void onCreate(Bundle savedInstanceState)
    {
        context = this.getBaseContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_signup);
    }

    public void toVolunteerProfile(View v)
    {
        // Convert user input to String objects.

        EditText code = findViewById(R.id.organizationCode);
        EditText first = findViewById(R.id.firstName);
        EditText last = findViewById(R.id.lastName);
        EditText email = findViewById(R.id.email);

        codeStr = code.getText().toString();
        String firstStr = first.getText().toString();
        String lastStr = last.getText().toString();
        String emailStr = email.getText().toString();

        // Create a volunteer using inputted information

        vol = new Volunteer(firstStr, lastStr, emailStr);

        // Find organization with the user's code in firebase

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("organizations/");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(vol.checkEmpty() == false)
                    {
                        orgPath = ds.getKey();
                        Log.d("MeganTag", "reference for ds: " + orgPath);
                        vol.setOrgPath(orgPath);

                        Organization org = ds.getValue(Organization.class);
                        vol.setOrgName(org.getName());

                        if (org.getCode().equals(codeStr)) {
                            org.addVolunteer(vol);
                            index = vol.getIndex();
                            Log.d("MeganTag", "index: " + index);
                            ds.getRef().setValue(org);

                            Intent intent = new Intent(context, VolunteerProfile.class);
                            intent.putExtra("orgPath", orgPath);
                            intent.putExtra("volIndex", index);
                            startActivity(intent);
                        }

                        else
                        {
                            Toast.makeText(context, "Please enter a valid organization code", Toast.LENGTH_SHORT).show();
                            vol.emptyToast(context);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }
}
