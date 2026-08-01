package com.diu.transportapp.activity.notice;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diu.transportapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class DeleteNoticeActivity extends AppCompatActivity {

    private TextInputEditText etDeleteNoticeTitle;
    private MaterialButton btnRemoveNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_notice);

        // ID Binding
        etDeleteNoticeTitle = findViewById(R.id.etDeleteNoticeTitle);
        btnRemoveNotice = findViewById(R.id.btnRemoveNotice);

        // Delete Notice Button Click Listener
        btnRemoveNotice.setOnClickListener(v -> {
            String noticeTitle = etDeleteNoticeTitle.getText().toString().trim();

            if (noticeTitle.isEmpty()) {
                Toast.makeText(DeleteNoticeActivity.this, "Please enter Notice Title", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ekhane database theke notice delete korar logic hobe
            Toast.makeText(DeleteNoticeActivity.this, "Notice Deleted Successfully!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}