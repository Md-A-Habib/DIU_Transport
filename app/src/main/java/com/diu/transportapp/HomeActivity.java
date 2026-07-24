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
    }
}