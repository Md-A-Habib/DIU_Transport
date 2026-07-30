package com.diu.transportapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddDriverActivity extends AppCompatActivity {

    private TextInputEditText etDriverName, etDriverPhone, etDriverEmail, etDriverPassword;
    private MaterialButton btnSaveDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);

        // ID Binding
        etDriverName = findViewById(R.id.etDriverName);
        etDriverPhone = findViewById(R.id.etDriverPhone);
        etDriverEmail = findViewById(R.id.etDriverEmail);
        etDriverPassword = findViewById(R.id.etDriverPassword);
        btnSaveDriver = findViewById(R.id.btnSaveDriver);

        // Save Driver Button Click Listener
        btnSaveDriver.setOnClickListener(v -> {
            String name = etDriverName.getText().toString().trim();
            String phone = etDriverPhone.getText().toString().trim();
            String email = etDriverEmail.getText().toString().trim();
            String password = etDriverPassword.getText().toString().trim();

            // Simple Validation
            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(AddDriverActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ekhane apnar database (Firebase Authentication / Firestore / MySQL) er code add korte parben
            Toast.makeText(AddDriverActivity.this, "Driver Saved Successfully!", Toast.LENGTH_SHORT).show();

            // Success hole activity close kore dewar jonno:
            finish();
        });
    }
}