package com.diu.transportapp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diu.transportapp.R;
import com.diu.transportapp.network.ApiClient;

import org.json.JSONObject;

public class AddRouteActivity extends AppCompatActivity {

    private EditText etRouteName, etStartLocation, etEndLocation, etDepartureTime;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        etRouteName = findViewById(R.id.etRouteName);
        etStartLocation = findViewById(R.id.etStartLocation);
        etEndLocation = findViewById(R.id.etEndLocation);
        etDepartureTime = findViewById(R.id.etDepartureTime);
        btnSave = findViewById(R.id.btnSave);

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

            ApiClient.post("/routes", body, new ApiClient.Callback() {
                @Override
                public void onSuccess(boolean success, String message, Object data) {
                    btnSave.setEnabled(true);
                    btnSave.setText("Save Route");
                    Toast.makeText(AddRouteActivity.this, message, Toast.LENGTH_SHORT).show();
                    if (success) finish();
                }

                @Override
                public void onError(String message) {
                    btnSave.setEnabled(true);
                    btnSave.setText("Save Route");
                    Toast.makeText(AddRouteActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
