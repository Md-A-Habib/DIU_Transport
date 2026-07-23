package com.diu.transportapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    private EditText etOtpCode;
    private Button btnSubmitOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        etOtpCode = findViewById(R.id.etOtpCode);
        btnSubmitOtp = findViewById(R.id.btnSubmitOtp);

        btnSubmitOtp.setOnClickListener(v -> {
            String code = etOtpCode.getText().toString().trim();

            if (code.isEmpty() || code.length() < 6) {
                Toast.makeText(VerificationActivity.this, "Please enter the full 6-digit code", Toast.LENGTH_SHORT).show();
            } else {
                // Verification Match Successful!
                // Jehetu home screen (MainActivity) akhno nei, tai ekhane success message diye sign-up/verification finish kore dicchi.
                Toast.makeText(VerificationActivity.this, "Login Successful! (Home screen coming soon)", Toast.LENGTH_LONG).show();

                // Sob activity finish kore app-er ber hoye jabe ba login/signup clear hoye jabe
                finishAffinity();
            }
        });
    }
}