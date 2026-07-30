package com.diu.transportapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DriverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        // 1. Add Driver Action (Connected to AddDriverActivity)
        CardView cardAddDriver = findViewById(R.id.cardAddDriverAction);
        if (cardAddDriver != null) {
            cardAddDriver.setOnClickListener(v -> {
                Intent intent = new Intent(DriverActivity.this, AddDriverActivity.class);
                startActivity(intent);
            });
        }

        // 2. View Drivers Action
        CardView cardViewDrivers = findViewById(R.id.cardViewDriversAction);
        if (cardViewDrivers != null) {
            cardViewDrivers.setOnClickListener(v -> {
                Intent intent = new Intent(DriverActivity.this, ViewDriversActivity.class);
                startActivity(intent);
            });
        }

        // 3. Delete Driver Action
        CardView cardDeleteDriver = findViewById(R.id.cardDeleteDriverAction);
        if (cardDeleteDriver != null) {
            cardDeleteDriver.setOnClickListener(v -> {
                Intent intent = new Intent(DriverActivity.this, DeleteDriverActivity.class);
                startActivity(intent);
            });
        }
    }
}