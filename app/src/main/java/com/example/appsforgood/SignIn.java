package com.example.appsforgood;

import android.content.Context;
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

import java.util.ArrayList;

/**
 * Allows the user to sign in
 * Works for Volunteers and Advisors
 */
public class SignIn extends AppCompatActivity
{
    private String emailStr;
    private String orgPath;
    private int index;
    private Context context;
    private boolean found;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
    }

    /**
     * Gets the inputted email and sends the user to the respective profile
     * @param v View
     */
    public void toProfile(View v){

        context = this.getBaseContext();

        // Gets the inputted email
        EditText email = findViewById(R.id.email);
        emailStr = email.getText().toString();

        // References the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("organizations/");

        found = false;

        // Loops through the organizations and checks volunteers and advisor in each org to find the one with the proper email
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (emailStr.isEmpty() == false)
                {
                    // Looping through the organizations
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {

                        Organization org = ds.getValue(Organization.class);
                        ArrayList<Volunteer> volList = org.getVolunteers();

                        // Signing in as a volunteer if the email matches a volunteer
                        for (Volunteer v : volList)
                        {
                            if (v.getEmail().equals(emailStr)) {
                                orgPath = v.getOrgPath();
                                index = v.getIndex();

                                found=true;
                                Intent intent = new Intent(context, VolunteerProfile.class);
                                intent.putExtra("orgPath", orgPath);
                                intent.putExtra("volIndex", index);
                                startActivity(intent);
                            }
                        }

                        // Signing in as an advisor if the email matches an advisor
                        if (org.getAdvisor().getEmail().equals(emailStr))
                        {
                            orgPath = ds.getKey();
                            Log.d("Megan", "orgPath at advisor sign in: " + orgPath);

                            found=true;
                            Intent intent = new Intent(context, AdvisorProfile.class);
                            intent.putExtra("orgPath", orgPath);
                            startActivity(intent);
                        }
                    }
                }

                if (!found) {
                    Toast.makeText(context, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}