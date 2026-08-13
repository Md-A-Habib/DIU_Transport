package com.diu.transportapp.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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

    private AutoCompleteTextView autoCompleteSemester, autoCompletePayment;
    private Button btnSubmit;
    private SessionManager session;

    private int selectedSemesterIndex = 0;
    private int selectedPaymentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_transport);

        session = new SessionManager(this);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        autoCompleteSemester = findViewById(R.id.autoCompleteSemester);
        autoCompletePayment = findViewById(R.id.autoCompletePayment);
        btnSubmit = findViewById(R.id.btnSubmit);

        // সেমিস্টার অ্যাডাপ্টার সেটআপ
        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, semesterLabels);
        autoCompleteSemester.setAdapter(semesterAdapter);
        autoCompleteSemester.setText(semesterLabels[0], false); // ডিফল্ট প্রথমটা সিলেক্ট থাকবে
        autoCompleteSemester.setOnItemClickListener((parent, view, position, id) -> selectedSemesterIndex = position);

        // পেমেন্ট মেথড অ্যাডাপ্টার সেটআপ
        ArrayAdapter<String> paymentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, paymentLabels);
        autoCompletePayment.setAdapter(paymentAdapter);
        autoCompletePayment.setText(paymentLabels[0], false); // ডিফল্ট প্রথমটা সিলেক্ট থাকবে
        autoCompletePayment.setOnItemClickListener((parent, view, position, id) -> selectedPaymentIndex = position);

        btnSubmit.setOnClickListener(v -> submit());
    }

    private void submit() {
        // সিলেক্ট করা ইনডেক্স অনুযায়ী ব্যাকএন্ডের আসল ভ্যালু নেওয়া হচ্ছে
        String semester = semesterValues[selectedSemesterIndex];
        String paymentMethod = paymentValues[selectedPaymentIndex];

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
            btnSubmit.setEnabled(true);
            btnSubmit.setText("Submit Application");
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}