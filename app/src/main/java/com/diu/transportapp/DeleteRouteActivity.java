package com.diu.transportapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class DeleteRouteActivity extends AppCompatActivity {

    private TextInputEditText etDeleteRouteId;
    private MaterialButton btnRemoveRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_route);

        // ID Binding
        etDeleteRouteId = findViewById(R.id.etDeleteRouteId);
        btnRemoveRoute = findViewById(R.id.btnRemoveRoute);

        // Delete Route Button Click Listener
        btnRemoveRoute.setOnClickListener(v -> {
            String routeId = etDeleteRouteId.getText().toString().trim();

            if (routeId.isEmpty()) {
                Toast.makeText(DeleteRouteActivity.this, "Please enter Route ID or Name", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ekhane database theke route delete korar logic hobe
            Toast.makeText(DeleteRouteActivity.this, "Route Deleted Successfully!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}