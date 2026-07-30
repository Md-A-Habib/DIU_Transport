package com.diu.transportapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class DeleteDriverActivity extends AppCompatActivity {

    private TextInputEditText etDeleteDriverId;
    private MaterialButton btnRemoveDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_driver);

        // ID Binding
        etDeleteDriverId = findViewById(R.id.etDeleteDriverId);
        btnRemoveDriver = findViewById(R.id.btnRemoveDriver);

        // Remove Driver Button Click Listener
        btnRemoveDriver.setOnClickListener(v -> {
            String driverId = etDeleteDriverId.getText().toString().trim();

            if (driverId.isEmpty()) {
                Toast.makeText(DeleteDriverActivity.this, "Please enter Driver ID", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ekhane database theke driver delete korar logic hobe
            Toast.makeText(DeleteDriverActivity.this, "Driver Removed Successfully!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}