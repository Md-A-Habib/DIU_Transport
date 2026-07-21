package com.diu.transportapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etResetEmail, etVerificationCode, etNewPassword, etConfirmNewPassword;
    private Button btnSendCode, btnResetPassword;
    private LinearLayout layoutNewPasswordSection;
    private TextView tvBackToLogin, tvSubtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Initialize views
        etResetEmail = findViewById(R.id.etResetEmail);
        etVerificationCode = findViewById(R.id.etVerificationCode);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmNewPassword = findViewById(R.id.etConfirmNewPassword);
        btnSendCode = findViewById(R.id.btnSendCode);
        btnResetPassword = findViewById(R.id.btnResetPassword);
        layoutNewPasswordSection = findViewById(R.id.layoutNewPasswordSection);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);
        tvSubtitle = findViewById(R.id.tvSubtitle);

        // Send Code Button Click
        btnSendCode.setOnClickListener(v -> {
            String email = etResetEmail.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email first", Toast.LENGTH_SHORT).show();
            } else {
                // Simulate code sending
                Toast.makeText(this, "Verification code sent to " + email, Toast.LENGTH_SHORT).show();

                // Show the next section (Code & New Password fields)
                layoutNewPasswordSection.setVisibility(View.VISIBLE);
                etResetEmail.setEnabled(false); // Lock email field
                btnSendCode.setVisibility(View.GONE); // Hide send code button
                tvSubtitle.setText("Enter the code sent to your email and set a new password");
            }
        });

        // Reset Password Button Click
        btnResetPassword.setOnClickListener(v -> {
            String code = etVerificationCode.getText().toString().trim();
            String newPass = etNewPassword.getText().toString().trim();
            String confirmPass = etConfirmNewPassword.getText().toString().trim();

            if (code.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!newPass.equals(confirmPass)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Password Reset Successful!", Toast.LENGTH_LONG).show();
                finish(); // Return to Login page
            }
        });

        // Back to Login Click
        tvBackToLogin.setOnClickListener(v -> {
            finish();
        });
    }
}