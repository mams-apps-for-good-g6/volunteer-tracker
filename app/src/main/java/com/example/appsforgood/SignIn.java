package com.example.appsforgood;

import android.content.Context;
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

import java.util.ArrayList;

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

    public void toProfile(View v){
        context = this.getBaseContext();

        EditText email = findViewById(R.id.email);
        emailStr = email.getText().toString();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("organizations/");

        //ref.push().setValue(new Organization());

        // Attach a listener to read the data at the reference
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Organization org = ds.getValue(Organization.class);
                    ArrayList<Volunteer> volList = org.getVolunteers();
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
                        }
                    }

                    if (org.getAdvisor().getEmail().equals(emailStr))
                    {
                        orgPath = ds.getKey();
                        Log.d("Megan", "orgPath at advisor sign in: " + orgPath);

                        Intent intent = new Intent(context, AdvisorProfile.class);
                        intent.putExtra("orgPath", orgPath);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

//        if(volunteer) {
//            Intent intent = new Intent(this, VolunteerProfile.class);
//            intent.putExtra("orgPath", orgPath);
//            Log.d("MeganTag", "sending " + orgPath + " " + index);
//            intent.putExtra("volIndex", index);
//            startActivity(intent);
//        }

//        if(advisor) {
//            Intent intent = new Intent(this, AdvisorProfile.class);
//            intent.putExtra("orgPath", orgPath);
//            startActivity(intent);
//        }
    }
}
