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
    String emailStr;
    String orgPath;
    int index;
    boolean volunteer;
    boolean advisor;
    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
    }

    /**
     * Gets the inputted email and sends the user to the respective profile
     * @param v
     */
    public void toProfile(View v){

        context = this.getBaseContext();

        // Gets the inputted email
        EditText email = findViewById(R.id.email);
        emailStr = email.getText().toString();

        // References the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("organizations/");

        // Loops through the organizations and checks volunteers and advisor in each org to find the one with the proper email
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                boolean empty1 = true;
                boolean empty2 = true;

                // Looping through the organizations
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    Organization org = ds.getValue(Organization.class);
                    ArrayList<Volunteer> volList = org.getVolunteers();

                    // Signing in as a volunteer if the email matches a volunteer
                    for (Volunteer v : volList)
                    {
                        if (v.getEmail().equals(emailStr))
                        {
                            orgPath=v.getOrgPath();
                            index=v.getIndex();

                            Intent intent = new Intent(context, VolunteerProfile.class);
                            intent.putExtra("orgPath", orgPath);
                            intent.putExtra("volIndex", index);
                            startActivity(intent);

                            empty1 = false;
                        }
                    }

                    // Signing in as an advisor if the email matches an advisor
                    if (org.getAdvisor().getEmail().equals(emailStr))
                    {
                        orgPath = ds.getKey();
                        Log.d("Megan", "orgPath at advisor sign in: " + orgPath);

                        Intent intent = new Intent(context, AdvisorProfile.class);
                        intent.putExtra("orgPath", orgPath);
                        startActivity(intent);

                        empty2 = false;
                    }

                    if (empty1 && empty2 == true)
                    {
                        Toast.makeText(context, "Please enter your email to log in", Toast.LENGTH_LONG).show();
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
