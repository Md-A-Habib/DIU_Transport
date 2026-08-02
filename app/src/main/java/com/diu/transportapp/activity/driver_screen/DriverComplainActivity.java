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

public class DriverComplainActivity extends AppCompatActivity {

    private CardView cardDriverPreviousComplain, cardDriverReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_driver_complain);

        // Edge-to-edge window insets padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initializing views
        cardDriverPreviousComplain = findViewById(R.id.cardDriverPreviousComplain);
        cardDriverReport = findViewById(R.id.cardDriverReport);

        // 1. Previous Complains Click
        cardDriverPreviousComplain.setOnClickListener(v -> {
            Intent intent = new Intent(DriverComplainActivity.this, DriverPreviousComplainActivity.class);
            startActivity(intent);
        });

        // 2. Emergency Issue Report Click
        cardDriverReport.setOnClickListener(v -> {
            Intent intent = new Intent(DriverComplainActivity.this, DriverReportActivity.class);
            startActivity(intent);
        });
    }
}