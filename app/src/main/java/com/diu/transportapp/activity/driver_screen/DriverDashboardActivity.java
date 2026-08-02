package com.diu.transportapp.activity.driver_screen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.diu.transportapp.R;
import com.diu.transportapp.activity.auth.LoginActivity;

public class DriverDashboardActivity extends AppCompatActivity {

    private CardView cardDriverSchedule, cardDriverNotice, cardDriverComplain, cardDriverUpdateProfile, cardDriverChangePassword, cardDriverSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_driver_dashboard);

        // Edge-to-edge window insets padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initializing CardViews based on XML layout IDs
        cardDriverSchedule = findViewById(R.id.cardDriverSchedule);
        cardDriverNotice = findViewById(R.id.cardDriverNotice);
        cardDriverComplain = findViewById(R.id.cardDriverComplain);
        cardDriverUpdateProfile = findViewById(R.id.cardDriverUpdateProfile);
        cardDriverChangePassword = findViewById(R.id.cardDriverChangePassword);
        cardDriverSignOut = findViewById(R.id.cardDriverSignOut);

        // 1. View Schedule
        cardDriverSchedule.setOnClickListener(v -> {
            Intent intent = new Intent(DriverDashboardActivity.this, DriverScheduleActivity.class);
            startActivity(intent);
        });

        // 2. Notice Board
        cardDriverNotice.setOnClickListener(v -> {
            Intent intent = new Intent(DriverDashboardActivity.this, DriverNoticeActivity.class);
            startActivity(intent);
        });

        // 3. Complain
        cardDriverComplain.setOnClickListener(v -> {
            Intent intent = new Intent(DriverDashboardActivity.this, DriverComplainActivity.class);
            startActivity(intent);
        });

        // 4. Update Profile
        cardDriverUpdateProfile.setOnClickListener(v -> {
            Intent intent = new Intent(DriverDashboardActivity.this, DriverUpdateProfileActivity.class);
            startActivity(intent);
        });

        // 5. Change Password
        cardDriverChangePassword.setOnClickListener(v -> {
            Intent intent = new Intent(DriverDashboardActivity.this, DriverChangePasswordActivity.class);
            startActivity(intent);
        });

        // 6. Sign Out
        cardDriverSignOut.setOnClickListener(v -> {
            Toast.makeText(DriverDashboardActivity.this, "Signed Out Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DriverDashboardActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}