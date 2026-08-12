package com.diu.transportapp.network;

import android.os.Handler;
import android.os.Looper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Minimal, dependency-free HTTP client for talking to the Transport backend.
 * Every response is expected to look like the backend's ApiResponse wrapper:
 * { "success": boolean, "message": string, "data": ... }
 * Requests run on a background thread; callbacks always fire on the main thread.
 */
public class ApiClient {

    public static final String BASE_URL = "https://diu-transport-ll73.onrender.com";

    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    private static final Handler MAIN = new Handler(Looper.getMainLooper());

    public interface Callback {
        void onSuccess(boolean success, String message, Object data);
        void onError(String message);
    }

    public static void get(String path, Callback callback) {
        request("GET", path, null, callback);
    }

    public static void post(String path, JSONObject body, Callback callback) {
        request("POST", path, body, callback);
    }

    public static void put(String path, JSONObject body, Callback callback) {
        request("PUT", path, body, callback);
    }

    public static void delete(String path, Callback callback) {
        request("DELETE", path, null, callback);
    }

    private static void request(String method, String path, JSONObject body, Callback callback) {
        EXECUTOR.execute(() -> {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(BASE_URL + path);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod(method);
                conn.setConnectTimeout(20000);
                conn.setReadTimeout(20000);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");

                if (body != null) {
                    conn.setDoOutput(true);
                    try (OutputStream os = conn.getOutputStream()) {
                        os.write(body.toString().getBytes(StandardCharsets.UTF_8));
                    }
                }

                int status = conn.getResponseCode();
                InputStream stream = (status >= 200 && status < 400)
                        ? conn.getInputStream()
                        : conn.getErrorStream();

                String text = readStream(stream);

                if (text == null || text.trim().isEmpty()) {
                    postError(callback, "No response from server. Please try again.");
                    return;
                }

                JSONObject root = new JSONObject(text);
                boolean success = root.optBoolean("success", false);
                String message = extractMessage(root, success, status);
                Object data = root.isNull("data") ? null : root.opt("data");

                postSuccess(callback, success, message, data);

            } catch (JSONException je) {
                postError(callback, "Unexpected server response. Please try again.");
            } catch (IOException ioe) {
                postError(callback, "Could not connect to server. Check your internet connection.");
            } catch (Exception e) {
                postError(callback, "Something went wrong. Please try again.");
            } finally {
                if (conn != null) conn.disconnect();
            }
        });
    }

    /**
     * The backend's own ApiResponse wrapper is used for normal success/failure cases.
     * Bean-validation errors (bad email format, blank field, etc.) fall outside that
     * wrapper and arrive as Spring's default error body instead, so we fall back to
     * pulling a readable message out of that shape too.
     */
    private static String extractMessage(JSONObject root, boolean success, int status) {
        if (root.has("message") && !root.isNull("message")) {
            String msg = root.optString("message");
            if (!msg.isEmpty()) return msg;
        }
        if (root.has("errors")) {
            JSONArray errors = root.optJSONArray("errors");
            if (errors != null && errors.length() > 0) {
                JSONObject first = errors.optJSONObject(0);
                if (first != null) {
                    String defaultMessage = first.optString("defaultMessage", "");
                    if (!defaultMessage.isEmpty()) return defaultMessage;
                }
            }
        }
        if (root.has("detail") && !root.isNull("detail")) {
            String detail = root.optString("detail");
            if (!detail.isEmpty()) return detail;
        }
        if (status >= 400) return "Request failed (" + status + "). Please try again.";
        return success ? "Success" : "Something went wrong.";
    }

    private static String readStream(InputStream in) throws IOException {
        if (in == null) return "";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        in.close();
        return out.toString("UTF-8");
    }

    private static void postSuccess(Callback callback, boolean success, String message, Object data) {
        if (callback == null) return;
        MAIN.post(() -> callback.onSuccess(success, message, data));
    }

    private static void postError(Callback callback, String message) {
        if (callback == null) return;
        MAIN.post(() -> callback.onError(message));
    }
}
