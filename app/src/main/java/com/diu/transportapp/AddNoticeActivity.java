package com.diu.transportapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddNoticeActivity extends AppCompatActivity {

    private TextInputEditText etNoticeTitle, etNoticeDescription;
    private MaterialButton btnPostNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);

        // ID Binding
        etNoticeTitle = findViewById(R.id.etNoticeTitle);
        etNoticeDescription = findViewById(R.id.etNoticeDescription);
        btnPostNotice = findViewById(R.id.btnPostNotice);

        // Post Notice Button Click Listener
        btnPostNotice.setOnClickListener(v -> {
            String title = etNoticeTitle.getText().toString().trim();
            String description = etNoticeDescription.getText().toString().trim();

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(AddNoticeActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ekhane database-e notice save korar logic hobe
            Toast.makeText(AddNoticeActivity.this, "Notice Posted Successfully!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}