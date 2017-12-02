package com.uapp.useekr.utils;

import android.util.Pair;

import com.google.gson.GsonBuilder;
import com.uapp.useekr.serializer.KeyValueSerializer;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.TimeZone;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by root on 12/2/17.
 */

public class HttpUtil {

    public static class KeyValue extends Pair<String, String> {
        KeyValue(String first, String second) {
            super(first, second);
        }
        public static KeyValue make(String first, String second) {
            Pair<String, String> pair = Pair.create(first, second);
            return new KeyValue(pair.first, pair.second);
        }
    }

    private static final MediaType JSON_CONTENT_TYPE
            = MediaType.parse("application/json; charset=utf-8");

    private static String getLocalTimeZoneId() {
        return TimeZone.getDefault().getID();
    }

    private static Request.Builder requestBuilder() {
        String timeZoneId = getLocalTimeZoneId();
        return new Request.Builder().header("TimeZone-Id", timeZoneId);
    }

    public static String get(String url, KeyValue... params) throws Exception {
        String queryString = queryBuilder(params);
        String requestUrl = url + "?" + queryString;
        Request request = requestBuilder()
                .url(requestUrl)
                .build();
        return executeResponse(request);
    }

    public static String post(String url, KeyValue... params) throws Exception {
        FormBody requestBody = toFormBody(params);
        Request request = requestBuilder()
                .url(url)
                .post(requestBody)
                .build();
        return executeResponse(request);
    }

    public static String post(String url, String formContent, KeyValue... params) throws Exception {
        FormBody requestBody = toFormBody(params);
        RequestBody body = RequestBody.create(JSON_CONTENT_TYPE, formContent);
        Request request = requestBuilder()
                .url(url)
                .method("POST", body)
                .post(requestBody)
                .build();
        return executeResponse(request);
    }

    private static String executeResponse(Request request) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new Exception("request unsuccessful: " + response.message());
        }
        ResponseBody body = response.body();
        if (body == null) {
            throw new NullPointerException("response body is null");
        }
        return body.string();
    }

    private static String toJson(KeyValue... keyValues) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(KeyValueSerializer.class, new KeyValueSerializer());
        return gsonBuilder.create().toJson(keyValues);
    }

    private static FormBody toFormBody(KeyValue... keyValues) {
        FormBody.Builder requestBuilder = new FormBody.Builder();
        for (KeyValue kv : keyValues) {
            requestBuilder.add(kv.first, kv.second);
        }
        return requestBuilder.build();
    }

    private static String queryBuilder(KeyValue... queries) throws UnsupportedEncodingException {
        StringBuilder queryString = new StringBuilder();
        for(KeyValue param : queries) {
            if (queryString.length() > 0) {
                queryString.append('&');
            }
            queryString.append(param.first);
            queryString.append('=');
            queryString.append(URLEncoder.encode(param.second, "UTF-8"));
        }
        return queryString.toString();
    }
}
