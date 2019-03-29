package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayCode extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_code);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String code = bundle.getString("organizationCode");

        TextView displayCode = findViewById(R.id.displayCode);
        displayCode.setText(code);
    }
}