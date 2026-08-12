package com.diu.transportapp.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.diu.transportapp.R;

public class AdminRouteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_route);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        findViewById(R.id.cardAddRoute).setOnClickListener(v -> startActivity(new Intent(this, AddRouteActivity.class)));
        findViewById(R.id.cardManageRoutes).setOnClickListener(v -> startActivity(new Intent(this, ManageRoutesActivity.class)));
    }
}
