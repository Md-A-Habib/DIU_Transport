package com.diu.transportapp.activity.transport;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diu.transportapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddRouteActivity extends AppCompatActivity {

    private TextInputEditText etRouteName, etStartLocation, etEndLocation, etDepartureTime;
    private MaterialButton btnSaveRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);

        // ID Binding
        etRouteName = findViewById(R.id.etRouteName);
        etStartLocation = findViewById(R.id.etStartLocation);
        etEndLocation = findViewById(R.id.etEndLocation);
        etDepartureTime = findViewById(R.id.etDepartureTime);
        btnSaveRoute = findViewById(R.id.btnSaveRoute);

        // Save Route Button Click Listener
        btnSaveRoute.setOnClickListener(v -> {
            String routeName = etRouteName.getText().toString().trim();
            String startLocation = etStartLocation.getText().toString().trim();
            String endLocation = etEndLocation.getText().toString().trim();
            String departureTime = etDepartureTime.getText().toString().trim();

            // Simple Validation
            if (routeName.isEmpty() || startLocation.isEmpty() || endLocation.isEmpty() || departureTime.isEmpty()) {
                Toast.makeText(AddRouteActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ekhane apnar database (Firebase/Firestore/MySQL) er code add korte parben
            Toast.makeText(AddRouteActivity.this, "Route Saved Successfully!", Toast.LENGTH_SHORT).show();

            // Success hole activity close kore dewar jonno:
            finish();
        });
    }
}