package com.example.ashokaengineer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class profileactivity extends AppCompatActivity {

    private Toolbar protoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileactivity);
        protoolbar=findViewById(R.id.protoolbar);

        setSupportActionBar(protoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
