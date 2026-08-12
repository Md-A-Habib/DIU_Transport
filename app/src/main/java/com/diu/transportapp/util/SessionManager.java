package com.diu.transportapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.diu.transportapp.model.UserResponse;

/**
 * The backend has no login token/session of its own, so the app keeps the
 * logged-in user's profile in local SharedPreferences and sends the user id
 * with every request that needs one.
 */
public class SessionManager {

    private static final String PREFS = "diu_transport_session";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "fullName";
    private static final String KEY_DIU_ID = "diuId";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_ROLE = "role";
    private static final String KEY_TRANSPORT_REGISTERED = "transportRegistered";
    private static final String KEY_PAYMENT_METHOD = "paymentMethod";
    private static final String KEY_CARD_STATUS = "cardStatus";
    private static final String KEY_SEMESTER = "semester";
    private static final String KEY_EXPIRY = "expiryDate";

    private final SharedPreferences prefs;

    public SessionManager(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public void save(UserResponse user) {
        prefs.edit()
                .putLong(KEY_ID, user.id)
                .putString(KEY_NAME, user.fullName)
                .putString(KEY_DIU_ID, user.diuId)
                .putString(KEY_EMAIL, user.email)
                .putString(KEY_PHONE, user.phone)
                .putString(KEY_ROLE, user.role)
                .putBoolean(KEY_TRANSPORT_REGISTERED, user.transportRegistered)
                .putString(KEY_PAYMENT_METHOD, user.paymentMethod)
                .putString(KEY_CARD_STATUS, user.cardStatus)
                .putString(KEY_SEMESTER, user.semester)
                .putString(KEY_EXPIRY, user.expiryDate)
                .apply();
    }

    public boolean isLoggedIn() {
        return prefs.contains(KEY_ID);
    }

    public long getUserId() {
        return prefs.getLong(KEY_ID, -1);
    }

    public String getFullName() {
        return prefs.getString(KEY_NAME, "");
    }

    public String getDiuId() {
        return prefs.getString(KEY_DIU_ID, "");
    }

    public String getEmail() {
        return prefs.getString(KEY_EMAIL, "");
    }

    public String getPhone() {
        return prefs.getString(KEY_PHONE, "");
    }

    public String getRole() {
        return prefs.getString(KEY_ROLE, "");
    }

    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(getRole());
    }

    public boolean isDriver() {
        return "DRIVER".equalsIgnoreCase(getRole());
    }

    public boolean isTransportRegistered() {
        return prefs.getBoolean(KEY_TRANSPORT_REGISTERED, false);
    }

    public String getCardStatus() {
        return prefs.getString(KEY_CARD_STATUS, null);
    }

    public String getSemester() {
        return prefs.getString(KEY_SEMESTER, null);
    }

    public String getExpiryDate() {
        return prefs.getString(KEY_EXPIRY, null);
    }

    public void clear() {
        prefs.edit().clear().apply();
    }
}
