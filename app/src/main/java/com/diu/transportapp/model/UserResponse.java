package com.diu.transportapp.model;

import org.json.JSONObject;

/**
 * Mirrors the backend's UserResponse DTO.
 */
public class UserResponse {

    public long id;
    public String fullName;
    public String diuId;
    public String email;
    public String phone;
    public String role;
    public boolean transportRegistered;
    public String paymentMethod;
    public boolean paymentStatus;
    public String cardStatus;
    public String semester;
    public String expiryDate;
    public String createdAt;

    public static UserResponse fromJson(JSONObject o) {
        UserResponse u = new UserResponse();
        if (o == null) return u;
        u.id = o.optLong("id");
        u.fullName = o.optString("fullName", "");
        u.diuId = o.optString("diuId", "");
        u.email = o.optString("email", "");
        u.phone = o.optString("phone", "");
        u.role = o.optString("role", "");
        u.transportRegistered = o.optBoolean("transportRegistered", false);
        u.paymentMethod = o.isNull("paymentMethod") ? null : o.optString("paymentMethod", null);
        u.paymentStatus = o.optBoolean("paymentStatus", false);
        u.cardStatus = o.isNull("cardStatus") ? null : o.optString("cardStatus", null);
        u.semester = o.isNull("semester") ? null : o.optString("semester", null);
        u.expiryDate = o.isNull("expiryDate") ? null : o.optString("expiryDate", null);
        u.createdAt = o.optString("createdAt", "");
        return u;
    }
}
