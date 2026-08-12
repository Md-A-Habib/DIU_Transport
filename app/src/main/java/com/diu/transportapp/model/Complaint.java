package com.diu.transportapp.model;

import org.json.JSONObject;

public class Complaint {

    public long id;
    public long userId;
    public String title;
    public String description;
    public String createdAt;

    public static Complaint fromJson(JSONObject o) {
        Complaint c = new Complaint();
        c.id = o.optLong("id");
        c.userId = o.optLong("userId");
        c.title = o.optString("title", "");
        c.description = o.optString("description", "");
        c.createdAt = o.optString("createdAt", "");
        return c;
    }
}
