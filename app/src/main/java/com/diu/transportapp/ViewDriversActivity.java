package com.diu.transportapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewDriversActivity extends AppCompatActivity {

    private TextView tvDriverId, tvDriverName, tvDriverEmail, tvDriverRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drivers);

        // ID Binding
        tvDriverId = findViewById(R.id.tvDriverId);
        tvDriverName = findViewById(R.id.tvDriverName);
        tvDriverEmail = findViewById(R.id.tvDriverEmail);
        tvDriverRole = findViewById(R.id.tvDriverRole);

        // Demo Data Set
        tvDriverId.setText("#DRV-1001");
        tvDriverName.setText("Md. Rahim Uddin");
        tvDriverEmail.setText("rahim.driver@diu.edu.bd");
        tvDriverRole.setText("Bus Driver");
    }
}