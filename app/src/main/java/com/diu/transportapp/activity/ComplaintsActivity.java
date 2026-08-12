package com.diu.transportapp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diu.transportapp.R;
import com.diu.transportapp.adapter.ComplaintAdapter;
import com.diu.transportapp.model.Complaint;
import com.diu.transportapp.network.ApiClient;
import com.diu.transportapp.util.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ComplaintsActivity extends AppCompatActivity {

    private EditText etTitle, etDescription;
    private Button btnSubmit;
    private TextView tvEmpty;
    private final List<Complaint> complaints = new ArrayList<>();
    private ComplaintAdapter adapter;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        session = new SessionManager(this);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvEmpty = findViewById(R.id.tvEmpty);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new ComplaintAdapter(complaints, false, null); // read-only, own complaints
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

        btnSubmit.setOnClickListener(v -> submitComplaint());

        loadComplaints();
    }

    private void submitComplaint() {
        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            JSONObject body = new JSONObject();
            body.put("userId", session.getUserId());
            body.put("title", title);
            body.put("description", description);

            btnSubmit.setEnabled(false);
            btnSubmit.setText("Submitting...");

            ApiClient.post("/complaints", body, new ApiClient.Callback() {
                @Override
                public void onSuccess(boolean success, String message, Object data) {
                    btnSubmit.setEnabled(true);
                    btnSubmit.setText("Submit Complaint");
                    Toast.makeText(ComplaintsActivity.this, message, Toast.LENGTH_SHORT).show();
                    if (success) {
                        etTitle.setText("");
                        etDescription.setText("");
                        loadComplaints();
                    }
                }

                @Override
                public void onError(String message) {
                    btnSubmit.setEnabled(true);
                    btnSubmit.setText("Submit Complaint");
                    Toast.makeText(ComplaintsActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadComplaints() {
        ApiClient.get("/complaints/user/" + session.getUserId(), new ApiClient.Callback() {
            @Override
            public void onSuccess(boolean success, String message, Object data) {
                complaints.clear();
                if (success && data instanceof JSONArray) {
                    JSONArray arr = (JSONArray) data;
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject o = arr.optJSONObject(i);
                        if (o != null) complaints.add(Complaint.fromJson(o));
                    }
                }
                adapter.notifyDataSetChanged();
                tvEmpty.setVisibility(complaints.isEmpty() ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onError(String message) {
                tvEmpty.setVisibility(complaints.isEmpty() ? View.VISIBLE : View.GONE);
            }
        });
    }
}
