package com.diu.transportapp.activity.transport;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diu.transportapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateRouteActivity extends AppCompatActivity {

    private TextInputEditText etUpdateRouteName, etUpdateStartLocation, etUpdateEndLocation, etUpdateDepartureTime;
    private MaterialButton btnUpdateRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_route);

        // ID Binding
        etUpdateRouteName = findViewById(R.id.etUpdateRouteName);
        etUpdateStartLocation = findViewById(R.id.etUpdateStartLocation);
        etUpdateEndLocation = findViewById(R.id.etUpdateEndLocation);
        etUpdateDepartureTime = findViewById(R.id.etUpdateDepartureTime);
        btnUpdateRoute = findViewById(R.id.btnUpdateRoute);

        // Update Route Button Click Listener
        btnUpdateRoute.setOnClickListener(v -> {
            String routeName = etUpdateRouteName.getText().toString().trim();
            String startLocation = etUpdateStartLocation.getText().toString().trim();
            String endLocation = etUpdateEndLocation.getText().toString().trim();
            String departureTime = etUpdateDepartureTime.getText().toString().trim();

            if (routeName.isEmpty() || startLocation.isEmpty() || endLocation.isEmpty() || departureTime.isEmpty()) {
                Toast.makeText(UpdateRouteActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ekhane database-e route update korar logic hobe
            Toast.makeText(UpdateRouteActivity.this, "Route Updated Successfully!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}