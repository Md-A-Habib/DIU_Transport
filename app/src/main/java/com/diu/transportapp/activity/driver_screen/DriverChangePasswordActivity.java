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

public class DriverChangePasswordActivity extends AppCompatActivity {

    private EditText etDriverOldPassword, etDriverNewPassword, etDriverConfirmPassword;
    private Button btnDriverResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_driver_change_password);

        // Edge-to-edge window insets padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initializing views
        etDriverOldPassword = findViewById(R.id.etDriverOldPassword);
        etDriverNewPassword = findViewById(R.id.etDriverNewPassword);
        etDriverConfirmPassword = findViewById(R.id.etDriverConfirmPassword);
        btnDriverResetPassword = findViewById(R.id.btnDriverResetPassword);

        // Reset Password Button Click Listener
        btnDriverResetPassword.setOnClickListener(v -> {
            String oldPassword = etDriverOldPassword.getText().toString().trim();
            String newPassword = etDriverNewPassword.getText().toString().trim();
            String confirmPassword = etDriverConfirmPassword.getText().toString().trim();

            if (oldPassword.isEmpty()) {
                etDriverOldPassword.setError("Please enter old password");
                etDriverOldPassword.requestFocus();
            } else if (newPassword.isEmpty()) {
                etDriverNewPassword.setError("Please enter new password");
                etDriverNewPassword.requestFocus();
            } else if (confirmPassword.isEmpty()) {
                etDriverConfirmPassword.setError("Please confirm your new password");
                etDriverConfirmPassword.requestFocus();
            } else if (!newPassword.equals(confirmPassword)) {
                etDriverConfirmPassword.setError("New passwords do not match");
                etDriverConfirmPassword.requestFocus();
            } else {
                Toast.makeText(DriverChangePasswordActivity.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                // TODO: Add backend/database password update logic here if needed
                finish(); // Close activity after successful reset
            }
        });
    }
}