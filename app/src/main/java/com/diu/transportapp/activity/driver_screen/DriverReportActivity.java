package com.diu.transportapp.activity.driver_screen;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.diu.transportapp.R;

public class DriverReportActivity extends AppCompatActivity {

    private EditText etDriverReportDetails;
    private Button btnSubmitDriverReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_driver_report);

        // Edge-to-edge window insets padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initializing views
        etDriverReportDetails = findViewById(R.id.etDriverReportDetails);
        btnSubmitDriverReport = findViewById(R.id.btnSubmitDriverReport);

        // Submit Report Click Listener
        btnSubmitDriverReport.setOnClickListener(v -> {
            String reportDetails = etDriverReportDetails.getText().toString().trim();

            if (reportDetails.isEmpty()) {
                etDriverReportDetails.setError("Please write your report details");
                etDriverReportDetails.requestFocus();
            } else {
                Toast.makeText(DriverReportActivity.this, "Report Submitted Successfully", Toast.LENGTH_SHORT).show();
                // TODO: Add backend/database submission logic here if needed
                finish(); // Close activity after successful submission
            }
        });
    }
}