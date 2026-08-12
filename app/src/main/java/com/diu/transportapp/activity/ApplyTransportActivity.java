package com.diu.transportapp.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diu.transportapp.R;
import com.diu.transportapp.network.ApiClient;
import com.diu.transportapp.util.SessionManager;

import org.json.JSONObject;

public class ApplyTransportActivity extends AppCompatActivity {

    private final String[] semesterLabels = {"Spring", "Summer", "Fall"};
    private final String[] semesterValues = {"SPRING", "SUMMER", "FALL"};

    private final String[] paymentLabels = {"bKash", "Nagad", "Visa Card", "Mastercard"};
    private final String[] paymentValues = {"BKASH", "NAGAD", "VISA", "MASTERCARD"};

    private Spinner spinnerSemester, spinnerPayment;
    private Button btnSubmit;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_transport);

        session = new SessionManager(this);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        spinnerSemester = findViewById(R.id.spinnerSemester);
        spinnerPayment = findViewById(R.id.spinnerPayment);
        btnSubmit = findViewById(R.id.btnSubmit);

        spinnerSemester.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, semesterLabels));
        spinnerPayment.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, paymentLabels));

        btnSubmit.setOnClickListener(v -> submit());
    }

    private void submit() {
        String semester = semesterValues[spinnerSemester.getSelectedItemPosition()];
        String paymentMethod = paymentValues[spinnerPayment.getSelectedItemPosition()];

        try {
            JSONObject body = new JSONObject();
            body.put("semester", semester);
            body.put("paymentMethod", paymentMethod);

            btnSubmit.setEnabled(false);
            btnSubmit.setText("Submitting...");

            ApiClient.post("/users/" + session.getUserId() + "/transport", body, new ApiClient.Callback() {
                @Override
                public void onSuccess(boolean success, String message, Object data) {
                    btnSubmit.setEnabled(true);
                    btnSubmit.setText("Submit Application");
                    Toast.makeText(ApplyTransportActivity.this, message, Toast.LENGTH_LONG).show();
                    if (success) finish();
                }

                @Override
                public void onError(String message) {
                    btnSubmit.setEnabled(true);
                    btnSubmit.setText("Submit Application");
                    Toast.makeText(ApplyTransportActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
