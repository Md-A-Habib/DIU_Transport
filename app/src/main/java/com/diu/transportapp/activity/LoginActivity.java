package com.diu.transportapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diu.transportapp.R;
import com.diu.transportapp.model.UserResponse;
import com.diu.transportapp.network.ApiClient;
import com.diu.transportapp.util.SessionManager;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvSignUp;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUp = findViewById(R.id.tvSignUp);

        btnLogin.setOnClickListener(v -> attemptLogin());
        tvSignUp.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
    }

    private void attemptLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            JSONObject body = new JSONObject();
            body.put("email", email);
            body.put("password", password);

            btnLogin.setEnabled(false);
            btnLogin.setText("Logging in...");

            ApiClient.post("/auth/login", body, new ApiClient.Callback() {
                @Override
                public void onSuccess(boolean success, String message, Object data) {
                    btnLogin.setEnabled(true);
                    btnLogin.setText("Login");

                    if (!success || !(data instanceof JSONObject)) {
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    UserResponse user = UserResponse.fromJson((JSONObject) data);
                    session.save(user);

                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                    Intent intent = "ADMIN".equalsIgnoreCase(user.role)
                            ? new Intent(LoginActivity.this, AdminDashboardActivity.class)
                            : new Intent(LoginActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onError(String message) {
                    btnLogin.setEnabled(true);
                    btnLogin.setText("Login");
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
