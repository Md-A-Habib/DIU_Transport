package com.diu.transportapp.model;

import org.json.JSONObject;

public class Notice {

    public long id;
    public String title;
    public String description;
    public String createdAt;

    public static Notice fromJson(JSONObject o) {
        Notice n = new Notice();
        n.id = o.optLong("id");
        n.title = o.optString("title", "");
        n.description = o.optString("description", "");
        n.createdAt = o.optString("createdAt", "");
        return n;
    }
}
