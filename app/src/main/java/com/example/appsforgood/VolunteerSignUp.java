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

/**
 * Allows a volunteer to create a profile
 * Adds the volunteer to an organization based on a pre-existing organization code
 */
public class VolunteerSignUp extends AppCompatActivity
{
    String codeStr;
    Volunteer vol;
    String orgPath;
    int index;
    Context context;

    /**
     * Creates an instance of the volunteer sign up
     * corresponding xml: volunteer_signup
     * @param savedInstanceState data received from MainActivity
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        context = this.getBaseContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_signup);
    }

    /**
     * Sends data from this class to the volunteer profile
     * data sent: first name of volunteer, last name of volunteer, volunteer's email, and the organization code to which they belong
     * All data is inputted by user
     * New volunteer is added to an organization's ArrayList in firebase based on inputted and pre-existing code
     * No "empty volunteers" (without any information) can be added to firebase
     * user will not be permitted to create an account without completing all fields (a toast will be created to notify the user if any fields are empty)
     * @param v an instance of the View class
     */
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
