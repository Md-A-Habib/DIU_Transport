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
import com.diu.transportapp.adapter.ComplaintAdapter;
import com.diu.transportapp.model.Complaint;
import com.diu.transportapp.network.ApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminComplaintsActivity extends AppCompatActivity implements ComplaintAdapter.Listener {

    private SwipeRefreshLayout swipeRefresh;
    private TextView tvEmpty;
    private final List<Complaint> complaints = new ArrayList<>();
    private ComplaintAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_complaints);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        swipeRefresh = findViewById(R.id.swipeRefresh);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        tvEmpty = findViewById(R.id.tvEmpty);

        adapter = new ComplaintAdapter(complaints, true, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefresh.setOnRefreshListener(this::loadComplaints);
        loadComplaints();
    }

    private void loadComplaints() {
        swipeRefresh.setRefreshing(true);
        ApiClient.get("/complaints", new ApiClient.Callback() {
            @Override
            public void onSuccess(boolean success, String message, Object data) {
                swipeRefresh.setRefreshing(false);
                complaints.clear();
                if (success && data instanceof JSONArray) {
                    JSONArray arr = (JSONArray) data;
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject o = arr.optJSONObject(i);
                        if (o != null) complaints.add(Complaint.fromJson(o));
                    }
                } else if (!success) {
                    Toast.makeText(AdminComplaintsActivity.this, message, Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
                tvEmpty.setVisibility(complaints.isEmpty() ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onError(String message) {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(AdminComplaintsActivity.this, message, Toast.LENGTH_SHORT).show();
                tvEmpty.setVisibility(complaints.isEmpty() ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void onDelete(Complaint complaint) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Complaint")
                .setMessage("Delete \"" + complaint.title + "\"?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    ApiClient.delete("/complaints/" + complaint.id, new ApiClient.Callback() {
                        @Override
                        public void onSuccess(boolean success, String message, Object data) {
                            Toast.makeText(AdminComplaintsActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (success) loadComplaints();
                        }

                        @Override
                        public void onError(String message) {
                            Toast.makeText(AdminComplaintsActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    });
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
