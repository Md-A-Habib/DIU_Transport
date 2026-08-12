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
import com.diu.transportapp.adapter.NoticeAdapter;
import com.diu.transportapp.model.Notice;
import com.diu.transportapp.network.ApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManageNoticesActivity extends AppCompatActivity implements NoticeAdapter.Listener {

    private SwipeRefreshLayout swipeRefresh;
    private TextView tvEmpty;
    private final List<Notice> notices = new ArrayList<>();
    private NoticeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_notices);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        swipeRefresh = findViewById(R.id.swipeRefresh);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        tvEmpty = findViewById(R.id.tvEmpty);

        adapter = new NoticeAdapter(notices, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefresh.setOnRefreshListener(this::loadNotices);
        loadNotices();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotices();
    }

    private void loadNotices() {
        swipeRefresh.setRefreshing(true);
        ApiClient.get("/notices", new ApiClient.Callback() {
            @Override
            public void onSuccess(boolean success, String message, Object data) {
                swipeRefresh.setRefreshing(false);
                notices.clear();
                if (success && data instanceof JSONArray) {
                    JSONArray arr = (JSONArray) data;
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject o = arr.optJSONObject(i);
                        if (o != null) notices.add(Notice.fromJson(o));
                    }
                } else if (!success) {
                    Toast.makeText(ManageNoticesActivity.this, message, Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
                tvEmpty.setVisibility(notices.isEmpty() ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onError(String message) {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(ManageNoticesActivity.this, message, Toast.LENGTH_SHORT).show();
                tvEmpty.setVisibility(notices.isEmpty() ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void onDelete(Notice notice) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Notice")
                .setMessage("Delete \"" + notice.title + "\"?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    ApiClient.delete("/notices/" + notice.id, new ApiClient.Callback() {
                        @Override
                        public void onSuccess(boolean success, String message, Object data) {
                            Toast.makeText(ManageNoticesActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (success) loadNotices();
                        }

                        @Override
                        public void onError(String message) {
                            Toast.makeText(ManageNoticesActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    });
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
