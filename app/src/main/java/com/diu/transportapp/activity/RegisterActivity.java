package com.diu.transportapp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diu.transportapp.R;

import org.json.JSONObject;

import com.diu.transportapp.network.ApiClient;

public class RegisterActivity extends AppCompatActivity {

    private RadioGroup rgRole;
    private EditText etFullName, etDiuId, etEmail, etPhone, etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rgRole = findViewById(R.id.rgRole);
        etFullName = findViewById(R.id.etFullName);
        etDiuId = findViewById(R.id.etDiuId);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLoginLink = findViewById(R.id.tvLoginLink);

        btnRegister.setOnClickListener(v -> attemptRegister());
        tvLoginLink.setOnClickListener(v -> finish());
    }

    private void attemptRegister() {
        String fullName = etFullName.getText().toString().trim();
        String diuId = etDiuId.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(diuId) || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        int checkedId = rgRole.getCheckedRadioButtonId();
        String role;
        if (checkedId == R.id.rbStudent) role = "STUDENT";
        else if (checkedId == R.id.rbFaculty) role = "FACULTY";
        else if (checkedId == R.id.rbStaff) role = "STAFF";
        else {
            Toast.makeText(this, "Please select your role", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            JSONObject body = new JSONObject();
            body.put("fullName", fullName);
            body.put("diuId", diuId);
            body.put("email", email);
            body.put("phone", phone);
            body.put("password", password);
            body.put("confirmPassword", confirmPassword);
            body.put("role", role);

            btnRegister.setEnabled(false);
            btnRegister.setText("Creating account...");

            ApiClient.post("/auth/register", body, new ApiClient.Callback() {
                @Override
                public void onSuccess(boolean success, String message, Object data) {
                    btnRegister.setEnabled(true);
                    btnRegister.setText("Register");
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                    if (success) {
                        finish();
                    }
                }

                @Override
                public void onError(String message) {
                    btnRegister.setEnabled(true);
                    btnRegister.setText("Register");
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
