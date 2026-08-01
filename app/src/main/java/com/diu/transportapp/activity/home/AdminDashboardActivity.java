package com.diu.transportapp.activity.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.cardview.widget.CardView;

import com.diu.transportapp.activity.complaint.AdminComplainActivity;
import com.diu.transportapp.activity.notice.AdminNoticeActivity;
import com.diu.transportapp.activity.action.DriverActivity;
import com.diu.transportapp.R;
import com.diu.transportapp.activity.transport.RouteActivity;
import com.diu.transportapp.activity.auth.LoginActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_dashboard);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Driver Option Click Action
        CardView cardDriver = findViewById(R.id.cardDriver);
        if (cardDriver != null) {
            cardDriver.setOnClickListener(v -> {
                Intent intent = new Intent(AdminDashboardActivity.this, DriverActivity.class);
                startActivity(intent);
            });
        }

        // 2. Route Option Click Action
        CardView cardRoute = findViewById(R.id.cardRoute);
        if (cardRoute != null) {
            cardRoute.setOnClickListener(v -> {
                Intent intent = new Intent(AdminDashboardActivity.this, RouteActivity.class);
                startActivity(intent);
            });
        }

        // 3. Notice Option Click Action
        CardView cardNotice = findViewById(R.id.cardNotice);
        if (cardNotice != null) {
            cardNotice.setOnClickListener(v -> {
                Intent intent = new Intent(AdminDashboardActivity.this, AdminNoticeActivity.class);
                startActivity(intent);
            });
        }

        // 4. View Complain Option Click Action
        CardView cardViewComplain = findViewById(R.id.cardViewComplain);
        if (cardViewComplain != null) {
            cardViewComplain.setOnClickListener(v -> {
                Intent intent = new Intent(AdminDashboardActivity.this, AdminComplainActivity.class);
                startActivity(intent);
            });
        }

        // 5. Logout Action
        CardView cardAdminLogout = findViewById(R.id.cardAdminLogout);
        if (cardAdminLogout != null) {
            cardAdminLogout.setOnClickListener(v -> {
                Intent intent = new Intent(AdminDashboardActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            });
        }
    }
}