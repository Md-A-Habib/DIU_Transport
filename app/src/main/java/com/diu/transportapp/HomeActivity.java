package com.diu.transportapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // References for Virtual Card Status
        TextView tvCardStatus = findViewById(R.id.tvCardStatus);
        TextView tvCardExpires = findViewById(R.id.tvCardExpires);

        // --- STATUS CONTROL ---
        boolean isTransportActive = false; // false korle Inactive dekhabe, true korle Active dekhabe

        if (isTransportActive) {
            tvCardStatus.setText("Status: Active");
            tvCardStatus.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            if (tvCardExpires != null) tvCardExpires.setText("Expires: 31 Dec 2026");
        } else {
            tvCardStatus.setText("Status: Inactive");
            tvCardStatus.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            if (tvCardExpires != null) tvCardExpires.setText("Expires: Expired");
        }

        // Apply / Renew Transport Card Click Action
        CardView cardApplyRenew = findViewById(R.id.cardApplyRenew);

        if (cardApplyRenew != null) {
            cardApplyRenew.setOnClickListener(v -> {
                if (isTransportActive) {
                    Toast.makeText(HomeActivity.this, "No need to apply or renew", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(HomeActivity.this, ApplyTransportActivity.class);
                    startActivity(intent);
                }
            });
        }

        // --- Bus Schedule Card Click Action ---
        CardView cardBusSchedule = findViewById(R.id.cardBusSchedule);

        if (cardBusSchedule != null) {
            cardBusSchedule.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, BusScheduleActivity.class);
                startActivity(intent);
            });
        }

        // --- Seat Booking Card Click Action ---
        CardView cardSeatBooking = findViewById(R.id.cardSeatBooking);

        if (cardSeatBooking != null) {
            cardSeatBooking.setOnClickListener(v -> {
                Toast.makeText(HomeActivity.this, "Seat booking feature will be available soon!", Toast.LENGTH_SHORT).show();
            });
        }

        // --- Sign Out Card Click Action ---
        CardView cardSignOut = findViewById(R.id.cardSignOut);

        if (cardSignOut != null) {
            cardSignOut.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            });
        }

        // --- Complain Card Click Action ---
        CardView cardComplain = findViewById(R.id.cardComplain);

        if (cardComplain != null) {
            cardComplain.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, ComplainActivity.class);
                startActivity(intent);
            });
        }

        // --- Notice Board Card Click Action ---
        CardView cardNoticeBoard = findViewById(R.id.cardNoticeBoard);

        if (cardNoticeBoard != null) {
            cardNoticeBoard.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, NoticeActivity.class);
                startActivity(intent);
            });
        }

        // --- Emergency Helpline Card Click Action ---
        CardView cardEmergencyHelpline = findViewById(R.id.cardEmergencyHelpline);

        if (cardEmergencyHelpline != null) {
            cardEmergencyHelpline.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, EmergencyActivity.class);
                startActivity(intent);
            });
        }

        // --- Change Password Card Click Action ---
        CardView cardChangePassword = findViewById(R.id.cardChangePassword);

        if (cardChangePassword != null) {
            cardChangePassword.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            });
        }
    }
}