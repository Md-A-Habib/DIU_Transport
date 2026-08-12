package com.diu.transportapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diu.transportapp.R;
import com.diu.transportapp.network.ApiClient;

import org.json.JSONObject;

public class UpdateRouteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "extra_id";
    public static final String EXTRA_ROUTE_NAME = "extra_route_name";
    public static final String EXTRA_START_LOCATION = "extra_start_location";
    public static final String EXTRA_END_LOCATION = "extra_end_location";
    public static final String EXTRA_DEPARTURE_TIME = "extra_departure_time";

    private EditText etRouteName, etStartLocation, etEndLocation, etDepartureTime;
    private Button btnSave;
    private long routeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_route);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        etRouteName = findViewById(R.id.etRouteName);
        etStartLocation = findViewById(R.id.etStartLocation);
        etEndLocation = findViewById(R.id.etEndLocation);
        etDepartureTime = findViewById(R.id.etDepartureTime);
        btnSave = findViewById(R.id.btnSave);

        Intent intent = getIntent();
        routeId = intent.getLongExtra(EXTRA_ID, -1);
        etRouteName.setText(intent.getStringExtra(EXTRA_ROUTE_NAME));
        etStartLocation.setText(intent.getStringExtra(EXTRA_START_LOCATION));
        etEndLocation.setText(intent.getStringExtra(EXTRA_END_LOCATION));
        etDepartureTime.setText(intent.getStringExtra(EXTRA_DEPARTURE_TIME));

        btnSave.setOnClickListener(v -> save());
    }

    private void save() {
        String routeName = etRouteName.getText().toString().trim();
        String startLocation = etStartLocation.getText().toString().trim();
        String endLocation = etEndLocation.getText().toString().trim();
        String departureTime = etDepartureTime.getText().toString().trim();

        if (TextUtils.isEmpty(routeName) || TextUtils.isEmpty(startLocation)
                || TextUtils.isEmpty(endLocation) || TextUtils.isEmpty(departureTime)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            JSONObject body = new JSONObject();
            body.put("routeName", routeName);
            body.put("startLocation", startLocation);
            body.put("endLocation", endLocation);
            body.put("departureTime", departureTime);

            btnSave.setEnabled(false);
            btnSave.setText("Saving...");

            ApiClient.put("/routes/" + routeId, body, new ApiClient.Callback() {
                @Override
                public void onSuccess(boolean success, String message, Object data) {
                    btnSave.setEnabled(true);
                    btnSave.setText("Save Changes");
                    Toast.makeText(UpdateRouteActivity.this, message, Toast.LENGTH_SHORT).show();
                    if (success) finish();
                }

                @Override
                public void onError(String message) {
                    btnSave.setEnabled(true);
                    btnSave.setText("Save Changes");
                    Toast.makeText(UpdateRouteActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
