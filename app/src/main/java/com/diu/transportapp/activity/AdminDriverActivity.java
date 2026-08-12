package com.diu.transportapp.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.diu.transportapp.R;

public class AdminDriverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_driver);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        findViewById(R.id.cardAddDriver).setOnClickListener(v -> startActivity(new Intent(this, AddDriverActivity.class)));
        findViewById(R.id.cardManageDrivers).setOnClickListener(v -> startActivity(new Intent(this, ManageDriversActivity.class)));
    }
}
