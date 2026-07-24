package com.diu.transportapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ApplyTransportActivity extends AppCompatActivity {

    private Spinner spinnerSemester, spinnerPayment;
    private Button btnSubmitApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_transport);

        spinnerSemester = findViewById(R.id.spinnerSemester);
        spinnerPayment = findViewById(R.id.spinnerPayment);
        btnSubmitApplication = findViewById(R.id.btnSubmitApplication);

        // Semester Options
        String[] semesters = {"Spring 2026", "Summer 2026", "Fall 2026"};
        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, semesters);
        spinnerSemester.setAdapter(semesterAdapter);

        // Payment Method Options
        String[] paymentMethods = {"bKash", "Nagad", "Rocket", "Cash (Transport Office)"};
        ArrayAdapter<String> paymentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, paymentMethods);
        spinnerPayment.setAdapter(paymentAdapter);

        // Submit Button Click
        btnSubmitApplication.setOnClickListener(v -> {
            String selectedSemester = spinnerSemester.getSelectedItem().toString();
            String selectedPayment = spinnerPayment.getSelectedItem().toString();

            Toast.makeText(ApplyTransportActivity.this, "Applied successfully for " + selectedSemester, Toast.LENGTH_LONG).show();
            finish();
        });
    }
}