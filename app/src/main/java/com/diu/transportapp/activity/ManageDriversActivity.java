package com.diu.transportapp.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.diu.transportapp.R;
import com.diu.transportapp.adapter.DriverAdapter;
import com.diu.transportapp.model.UserResponse;
import com.diu.transportapp.network.ApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManageDriversActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefresh;
    private TextView tvEmpty;
    private final List<UserResponse> drivers = new ArrayList<>();
    private DriverAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_drivers);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        swipeRefresh = findViewById(R.id.swipeRefresh);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        tvEmpty = findViewById(R.id.tvEmpty);

        adapter = new DriverAdapter(drivers, this::confirmDelete);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefresh.setOnRefreshListener(this::loadDrivers);
        loadDrivers();
    }

    private void loadDrivers() {
        swipeRefresh.setRefreshing(true);
        ApiClient.get("/users/drivers", new ApiClient.Callback() {
            @Override
            public void onSuccess(boolean success, String message, Object data) {
                swipeRefresh.setRefreshing(false);
                drivers.clear();
                if (success && data instanceof JSONArray) {
                    JSONArray arr = (JSONArray) data;
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject o = arr.optJSONObject(i);
                        if (o != null) drivers.add(UserResponse.fromJson(o));
                    }
                } else if (!success) {
                    Toast.makeText(ManageDriversActivity.this, message, Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
                tvEmpty.setVisibility(drivers.isEmpty() ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onError(String message) {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(ManageDriversActivity.this, message, Toast.LENGTH_SHORT).show();
                tvEmpty.setVisibility(drivers.isEmpty() ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void confirmDelete(UserResponse driver) {
        new AlertDialog.Builder(this)
                .setTitle("Remove Driver")
                .setMessage("Remove " + driver.fullName + " from drivers?")
                .setPositiveButton("Remove", (dialog, which) -> deleteDriver(driver))
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void deleteDriver(UserResponse driver) {
        ApiClient.delete("/users/driver/" + driver.id, new ApiClient.Callback() {
            @Override
            public void onSuccess(boolean success, String message, Object data) {
                Toast.makeText(ManageDriversActivity.this, message, Toast.LENGTH_SHORT).show();
                if (success) loadDrivers();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(ManageDriversActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
