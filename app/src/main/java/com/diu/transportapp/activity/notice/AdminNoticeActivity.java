package com.diu.transportapp.activity.notice;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.cardview.widget.CardView;

import com.diu.transportapp.R;

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
        CardView cardAddNoticeAction = findViewById(R.id.cardAddNoticeAction);
        CardView cardViewNoticesAction = findViewById(R.id.cardViewNoticesAction);
        CardView cardDeleteNoticeAction = findViewById(R.id.cardDeleteNoticeAction);

        // 1. Add Notice Action (Connected to AddNoticeActivity)
        if (cardAddNoticeAction != null) {
            cardAddNoticeAction.setOnClickListener(v -> {
                Intent intent = new Intent(AdminNoticeActivity.this, AddNoticeActivity.class);
                startActivity(intent);
            });
        }

        // 2. View Notices Action (Connected to ViewNoticeActivity)
        if (cardViewNoticesAction != null) {
            cardViewNoticesAction.setOnClickListener(v -> {
                Intent intent = new Intent(AdminNoticeActivity.this, ViewNoticeActivity.class);
                startActivity(intent);
            });
        }

        // 3. Delete Notice Action (Connected to DeleteNoticeActivity)
        if (cardDeleteNoticeAction != null) {
            cardDeleteNoticeAction.setOnClickListener(v -> {
                Intent intent = new Intent(AdminNoticeActivity.this, DeleteNoticeActivity.class);
                startActivity(intent);
            });
        }
    }
}