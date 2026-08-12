package com.diu.transportapp.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.diu.transportapp.R;

public class AdminNoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notice);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        findViewById(R.id.cardAddNotice).setOnClickListener(v -> startActivity(new Intent(this, AddNoticeActivity.class)));
        findViewById(R.id.cardManageNotices).setOnClickListener(v -> startActivity(new Intent(this, ManageNoticesActivity.class)));
    }
}
