package com.diu.transportapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.cardview.widget.CardView;

public class RouteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_route);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Click actions for Route options
        CardView cardAddRouteAction = findViewById(R.id.cardAddRouteAction);
        CardView cardUpdateRouteAction = findViewById(R.id.cardUpdateRouteAction);
        CardView cardDeleteRouteAction = findViewById(R.id.cardDeleteRouteAction);

        // 1. Add Bus Route Action (Connected to AddRouteActivity)
        if (cardAddRouteAction != null) {
            cardAddRouteAction.setOnClickListener(v -> {
                Intent intent = new Intent(RouteActivity.this, AddRouteActivity.class);
                startActivity(intent);
            });
        }

        // 2. Update Bus Route Action (Connected to UpdateRouteActivity)
        if (cardUpdateRouteAction != null) {
            cardUpdateRouteAction.setOnClickListener(v -> {
                Intent intent = new Intent(RouteActivity.this, UpdateRouteActivity.class);
                startActivity(intent);
            });
        }

        // 3. Delete Bus Route Action (Connected to DeleteRouteActivity)
        if (cardDeleteRouteAction != null) {
            cardDeleteRouteAction.setOnClickListener(v -> {
                Intent intent = new Intent(RouteActivity.this, DeleteRouteActivity.class);
                startActivity(intent);
            });
        }
    }
}