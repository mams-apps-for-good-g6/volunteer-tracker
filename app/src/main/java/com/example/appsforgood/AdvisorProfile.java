package com.example.appsforgood;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AdvisorProfile extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advisor_profile);

        // need to get org object

        TextView name = findViewById(R.id.AdvisorName);
        name.setText("name here");

        TextView email = findViewById(R.id.AdvisorEmail);
        email.setText("email here");

        TextView orgName = findViewById(R.id.AdvisorOrganization);
        orgName.setText("org name");

        TextView code = findViewById(R.id.AdvisorOrganizationCode);
        code.setText("org code");
    }
}