package com.diu.transportapp.activity.driver_screen;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.diu.transportapp.R;

public class DriverUpdateProfileActivity extends AppCompatActivity {

    private EditText etDriverUpdateName, etDriverUpdatePhone;
    private Button btnSaveDriverProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_driver_update_profile);

        // Edge-to-edge window insets padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initializing views
        etDriverUpdateName = findViewById(R.id.etDriverUpdateName);
        etDriverUpdatePhone = findViewById(R.id.etDriverUpdatePhone);
        btnSaveDriverProfile = findViewById(R.id.btnSaveDriverProfile);

        // Save Changes Button Click Listener
        btnSaveDriverProfile.setOnClickListener(v -> {
            String name = etDriverUpdateName.getText().toString().trim();
            String phone = etDriverUpdatePhone.getText().toString().trim();

            if (name.isEmpty()) {
                etDriverUpdateName.setError("Please enter your name");
                etDriverUpdateName.requestFocus();
            } else if (phone.isEmpty()) {
                etDriverUpdatePhone.setError("Please enter your phone number");
                etDriverUpdatePhone.requestFocus();
            } else {
                Toast.makeText(DriverUpdateProfileActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                // TODO: Add database/backend update logic here if needed
                finish(); // Close activity after saving
            }
        });
    }
}