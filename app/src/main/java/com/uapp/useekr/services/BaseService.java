package com.uapp.useekr.services;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uapp.useekr.utils.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 12/2/17.
 */

public abstract class BaseService<T> {

    public static final String DOMAIN_NAME = "http://4m.tataselo.com/index.php/uni/";

    private String servicePath;

    BaseService(String servicePath) {
        this.servicePath = DOMAIN_NAME + servicePath;
    }

    PagedResult<T> getObject(HttpUtil.KeyValue ...params) {
        try {
            String rawResponse = HttpUtil.get(servicePath, params);
            return serializeObject(rawResponse);
        }
        catch (Exception e) {
            return new PagedResult<>(e.getMessage());
        }
    }

    PagedResult<List<T>> getList(HttpUtil.KeyValue ...params) {
        try {
            String rawResponse = HttpUtil.get(servicePath, params);
            return serializeList(rawResponse);
        }
        catch (Exception e) {
            return new PagedResult<>(e.getMessage());
        }
    }

    PagedResult<T> postObject(HttpUtil.KeyValue ...params) {
        try {
            String rawResponse = HttpUtil.post(servicePath, params);
            return serializeObject(rawResponse);
        }
        catch (Exception e) {
            return new PagedResult<>(e.getMessage());
        }
    }

    private PagedResult<T> serializeObject(String rawResponse) throws JSONException, IllegalStateException {
        JSONObject jsonResponse = new JSONObject(rawResponse);
        String status = jsonResponse.getString("status");
        if (status.equals("success")) {
            T data = toObject(jsonResponse.getString("data"));
            return new PagedResult<>(PagedResult.Status.SUCCESS, data, 1);
        } else {
            return new PagedResult<>(jsonResponse.getString("data"));
        }
    }

    private PagedResult<List<T>> serializeList(String rawResponse) throws JSONException, IllegalStateException {
        JSONObject jsonResponse = new JSONObject(rawResponse);
        String status = jsonResponse.getString("status");
        if (status.equals("success")) {
            List<T> data = toList(jsonResponse.getString("data"));
            long totalCount = jsonResponse.getLong("totalCount");
            return new PagedResult<>(PagedResult.Status.SUCCESS, data, totalCount);
        } else {
            return new PagedResult<>(jsonResponse.getString("data"));
        }
    }

    HttpUtil.KeyValue identity(long userId) {
        return HttpUtil.KeyValue.make("user_id", String.valueOf(userId));
    }

    HttpUtil.KeyValue action(String value) {
        return HttpUtil.KeyValue.make("action", value);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    private T toObject(String responseData) throws IllegalStateException {
        if (responseData == null || responseData.isEmpty()) {
            return (T) new Object();
        }
        return new Gson().fromJson(responseData, getType());
    }

    @NonNull
    private List<T> toList(String responseData) throws IllegalStateException {
        if (responseData == null || responseData.isEmpty()) {
            return new ArrayList<>();
        }
        Class<T> typeOfT = getGenericTypeClass();
        Type type = TypeToken.getParameterized(List.class, typeOfT).getType();
        return new Gson().fromJson(responseData, type);
    }

    private Type getType() {
        return ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    private Class<T> getGenericTypeClass() throws IllegalStateException {
        try {
            String className = getType().toString();
            Class<?> clazz = Class.forName(className.replace("class ", ""));
            return (Class<T>) clazz;
        } catch (Exception e) {
            throw new IllegalStateException("Class is not parametrized with generic type! Please use extends <>");
        }
    }
}