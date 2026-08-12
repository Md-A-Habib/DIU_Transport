package com.diu.transportapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diu.transportapp.R;
import com.diu.transportapp.adapter.RouteAdapter;
import com.diu.transportapp.model.BusRoute;
import com.diu.transportapp.network.ApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RoutesActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;
    private TextView tvEmpty;
    private final List<BusRoute> routes = new ArrayList<>();
    private RouteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        swipeRefresh = findViewById(R.id.swipeRefresh);
        recyclerView = findViewById(R.id.recyclerView);
        tvEmpty = findViewById(R.id.tvEmpty);

        adapter = new RouteAdapter(routes, null); // read-only
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefresh.setOnRefreshListener(this::loadRoutes);
        loadRoutes();
    }

    private void loadRoutes() {
        swipeRefresh.setRefreshing(true);
        ApiClient.get("/routes", new ApiClient.Callback() {
            @Override
            public void onSuccess(boolean success, String message, Object data) {
                swipeRefresh.setRefreshing(false);
                routes.clear();
                if (success && data instanceof JSONArray) {
                    JSONArray arr = (JSONArray) data;
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject o = arr.optJSONObject(i);
                        if (o != null) routes.add(BusRoute.fromJson(o));
                    }
                } else if (!success) {
                    Toast.makeText(RoutesActivity.this, message, Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
                tvEmpty.setVisibility(routes.isEmpty() ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onError(String message) {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(RoutesActivity.this, message, Toast.LENGTH_SHORT).show();
                tvEmpty.setVisibility(routes.isEmpty() ? View.VISIBLE : View.GONE);
            }
        });
    }
}
