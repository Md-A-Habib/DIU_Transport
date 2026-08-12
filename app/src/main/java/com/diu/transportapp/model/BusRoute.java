package com.diu.transportapp.model;

import org.json.JSONException;
import org.json.JSONObject;

public class BusRoute {

    public long id;
    public String routeName;
    public String startLocation;
    public String endLocation;
    public String departureTime;

    public static BusRoute fromJson(JSONObject o) {
        BusRoute r = new BusRoute();
        r.id = o.optLong("id");
        r.routeName = o.optString("routeName", "");
        r.startLocation = o.optString("startLocation", "");
        r.endLocation = o.optString("endLocation", "");
        r.departureTime = o.optString("departureTime", "");
        return r;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject o = new JSONObject();
        o.put("routeName", routeName);
        o.put("startLocation", startLocation);
        o.put("endLocation", endLocation);
        o.put("departureTime", departureTime);
        return o;
    }
}
