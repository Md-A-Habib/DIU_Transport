package com.diu.transportapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private RadioGroup rgRole;
    private EditText etFullName, etEmail, etIdNumber, etDepartment, etPhone, etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        rgRole = findViewById(R.id.rgRole);
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etIdNumber = findViewById(R.id.etIdNumber);
        etDepartment = findViewById(R.id.etDepartment);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLoginLink = findViewById(R.id.tvLoginLink);

        // Dynamic Hint change based on Role selection
        rgRole.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbStudent) {
                etIdNumber.setHint("Student ID (e.g., 221-15-...)");
            } else if (checkedId == R.id.rbFaculty) {
                etIdNumber.setHint("Faculty Employee ID");
            } else if (checkedId == R.id.rbStaff) {
                etIdNumber.setHint("Staff ID / Office ID");
            }
        });

        // Register Button Click
        btnRegister.setOnClickListener(v -> {
            String name = etFullName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String idNum = etIdNumber.getText().toString().trim();
            String dept = etDepartment.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();
            String confirmPass = etConfirmPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || idNum.isEmpty() || dept.isEmpty() || phone.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(SignupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!pass.equals(confirmPass)) {
                Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                // Navigate to Verification Activity
                Toast.makeText(SignupActivity.this, "Verification code sent to email!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignupActivity.this, VerificationActivity.class);
                startActivity(intent);
            }
        });

        // Back to Login Link Click
        tvLoginLink.setOnClickListener(v -> {
            finish();
        });
    }
}