package com.diu.transportapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.diu.transportapp.R;
import com.diu.transportapp.model.UserResponse;
import com.diu.transportapp.network.ApiClient;
import com.diu.transportapp.util.SessionManager;

import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    private SessionManager session;

    private TextView tvGreeting, tvRoleBadge, tvCardStatus, tvCardExpires;
    private View statusGroup;
    private CardView cardApplyTransport, cardRoutes, cardNotices, cardComplaints,
            cardUpdateProfile, cardChangePassword, cardLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        session = new SessionManager(this);

        tvGreeting = findViewById(R.id.tvGreeting);
        tvRoleBadge = findViewById(R.id.tvRoleBadge);
        tvCardStatus = findViewById(R.id.tvCardStatus);
        tvCardExpires = findViewById(R.id.tvCardExpires);
        statusGroup = findViewById(R.id.statusGroup);

        cardApplyTransport = findViewById(R.id.cardApplyTransport);
        cardRoutes = findViewById(R.id.cardRoutes);
        cardNotices = findViewById(R.id.cardNotices);
        cardComplaints = findViewById(R.id.cardComplaints);
        cardUpdateProfile = findViewById(R.id.cardUpdateProfile);
        cardChangePassword = findViewById(R.id.cardChangePassword);
        cardLogout = findViewById(R.id.cardLogout);

        boolean isDriver = session.isDriver();
        cardApplyTransport.setVisibility(isDriver ? View.GONE : View.VISIBLE);
        statusGroup.setVisibility(isDriver ? View.GONE : View.VISIBLE);

        cardApplyTransport.setOnClickListener(v -> startActivity(new Intent(this, ApplyTransportActivity.class)));
        cardRoutes.setOnClickListener(v -> startActivity(new Intent(this, RoutesActivity.class)));
        cardNotices.setOnClickListener(v -> startActivity(new Intent(this, NoticeActivity.class)));
        cardComplaints.setOnClickListener(v -> startActivity(new Intent(this, ComplaintsActivity.class)));
        cardUpdateProfile.setOnClickListener(v -> startActivity(new Intent(this, UpdateProfileActivity.class)));
        cardChangePassword.setOnClickListener(v -> startActivity(new Intent(this, ChangePasswordActivity.class)));
        cardLogout.setOnClickListener(v -> logout());

        bindFromSession();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshProfile();
    }

    private void bindFromSession() {
        tvGreeting.setText("Hi, " + session.getFullName());
        tvRoleBadge.setText(capitalize(session.getRole()));
        bindCardStatus(session.getCardStatus(), session.getExpiryDate());
    }

    private void refreshProfile() {
        ApiClient.get("/users/" + session.getUserId(), new ApiClient.Callback() {
            @Override
            public void onSuccess(boolean success, String message, Object data) {
                if (success && data instanceof JSONObject) {
                    UserResponse user = UserResponse.fromJson((JSONObject) data);
                    session.save(user);
                    tvGreeting.setText("Hi, " + user.fullName);
                    tvRoleBadge.setText(capitalize(user.role));
                    bindCardStatus(user.cardStatus, user.expiryDate);
                }
            }

            @Override
            public void onError(String message) {
                // Offline - keep showing the cached values already bound.
            }
        });
    }

    private void bindCardStatus(String cardStatus, String expiryDate) {
        boolean active = "ACTIVE".equalsIgnoreCase(cardStatus);
        tvCardStatus.setText(active ? "Active" : "Inactive");
        tvCardStatus.setBackgroundResource(active ? R.drawable.bg_status_active : R.drawable.bg_status_inactive);
        tvCardStatus.setTextColor(getResources().getColor(active ? R.color.status_active : R.color.status_inactive));
        tvCardExpires.setText(expiryDate != null ? "Expires " + expiryDate : "No active transport card");
    }

    private String capitalize(String text) {
        if (text == null || text.isEmpty()) return "";
        return text.charAt(0) + text.substring(1).toLowerCase();
    }

    private void logout() {
        session.clear();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
