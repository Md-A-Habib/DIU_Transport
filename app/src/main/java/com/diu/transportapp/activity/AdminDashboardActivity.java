package com.diu.transportapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.diu.transportapp.R;
import com.diu.transportapp.util.SessionManager;

public class AdminDashboardActivity extends AppCompatActivity {

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        session = new SessionManager(this);

        TextView tvGreeting = findViewById(R.id.tvGreeting);
        tvGreeting.setText("Hi, " + session.getFullName());

        findViewById(R.id.cardDrivers).setOnClickListener(v -> startActivity(new Intent(this, AdminDriverActivity.class)));
        findViewById(R.id.cardRoutes).setOnClickListener(v -> startActivity(new Intent(this, AdminRouteActivity.class)));
        findViewById(R.id.cardNotices).setOnClickListener(v -> startActivity(new Intent(this, AdminNoticeActivity.class)));
        findViewById(R.id.cardComplaints).setOnClickListener(v -> startActivity(new Intent(this, AdminComplaintsActivity.class)));
        findViewById(R.id.cardChangePassword).setOnClickListener(v -> startActivity(new Intent(this, ChangePasswordActivity.class)));
        findViewById(R.id.cardLogout).setOnClickListener(v -> logout());
    }

    private void logout() {
        session.clear();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
