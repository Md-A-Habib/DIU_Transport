package com.diu.transportapp.activity;

import android.app.AlertDialog;
import android.content.Intent;
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

public class ManageRoutesActivity extends AppCompatActivity implements RouteAdapter.Listener {

    private SwipeRefreshLayout swipeRefresh;
    private TextView tvEmpty;
    private final List<BusRoute> routes = new ArrayList<>();
    private RouteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_routes);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        swipeRefresh = findViewById(R.id.swipeRefresh);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        tvEmpty = findViewById(R.id.tvEmpty);

        adapter = new RouteAdapter(routes, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefresh.setOnRefreshListener(this::loadRoutes);
        loadRoutes();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                    Toast.makeText(ManageRoutesActivity.this, message, Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
                tvEmpty.setVisibility(routes.isEmpty() ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onError(String message) {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(ManageRoutesActivity.this, message, Toast.LENGTH_SHORT).show();
                tvEmpty.setVisibility(routes.isEmpty() ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void onEdit(BusRoute route) {
        Intent intent = new Intent(this, UpdateRouteActivity.class);
        intent.putExtra(UpdateRouteActivity.EXTRA_ID, route.id);
        intent.putExtra(UpdateRouteActivity.EXTRA_ROUTE_NAME, route.routeName);
        intent.putExtra(UpdateRouteActivity.EXTRA_START_LOCATION, route.startLocation);
        intent.putExtra(UpdateRouteActivity.EXTRA_END_LOCATION, route.endLocation);
        intent.putExtra(UpdateRouteActivity.EXTRA_DEPARTURE_TIME, route.departureTime);
        startActivity(intent);
    }

    @Override
    public void onDelete(BusRoute route) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Route")
                .setMessage("Delete \"" + route.routeName + "\"?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    ApiClient.delete("/routes/" + route.id, new ApiClient.Callback() {
                        @Override
                        public void onSuccess(boolean success, String message, Object data) {
                            Toast.makeText(ManageRoutesActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (success) loadRoutes();
                        }

                        @Override
                        public void onError(String message) {
                            Toast.makeText(ManageRoutesActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    });
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
