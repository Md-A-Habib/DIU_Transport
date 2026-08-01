package com.diu.transportapp.activity.complaint;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.diu.transportapp.R;

public class ComplainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_complain);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CardView cardPreviousComplain = findViewById(R.id.cardPreviousComplain);
        CardView cardReport = findViewById(R.id.cardReport);

        // Previous Complain Click Action
        if (cardPreviousComplain != null) {
            cardPreviousComplain.setOnClickListener(v -> {
                Intent intent = new Intent(ComplainActivity.this, PreviousComplainActivity.class);
                startActivity(intent);
            });
        }

        // Report Click Action (Red Color)
        if (cardReport != null) {
            cardReport.setOnClickListener(v -> {
                Intent intent = new Intent(ComplainActivity.this, ReportActivity.class);
                startActivity(intent);
            });
        }
    }
}