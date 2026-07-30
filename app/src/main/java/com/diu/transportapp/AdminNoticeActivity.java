package com.diu.transportapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.cardview.widget.CardView;

public class AdminNoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_notice);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Click actions for Notice options
        CardView cardViewNoticesAction = findViewById(R.id.cardViewNoticesAction);
        CardView cardDeleteNoticeAction = findViewById(R.id.cardDeleteNoticeAction);

        if (cardViewNoticesAction != null) {
            cardViewNoticesAction.setOnClickListener(v ->
                    Toast.makeText(AdminNoticeActivity.this, "View Notices Action", Toast.LENGTH_SHORT).show());
        }

        if (cardDeleteNoticeAction != null) {
            cardDeleteNoticeAction.setOnClickListener(v ->
                    Toast.makeText(AdminNoticeActivity.this, "Delete Notice Action", Toast.LENGTH_SHORT).show());
        }
    }
}