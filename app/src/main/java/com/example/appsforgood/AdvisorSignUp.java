package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AdvisorSignUp extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advisor_signup);
    }

    public void displayCode(View v)
    {
        // take input and generate code by creating an organization object and calling the generateCode method
        EditText OrgName = findViewById(R.id.organizationName);
        EditText first = findViewById(R.id.firstName);
        EditText last = findViewById(R.id.lastName);
        EditText email = findViewById(R.id.email);

        String OrgNameStr = OrgName.getText().toString();
        String firstStr = first.getText().toString();
        String lastStr = last.getText().toString();
        String emailStr = email.getText().toString();

        Organization org = new Organization(OrgNameStr, new User(firstStr, lastStr, emailStr));
        String code = Integer.toString(org.getCode());

        Intent intent = new Intent(this, DisplayCode.class);
        intent.putExtra("organizationCode", code);
        startActivity(intent);
    }
}
