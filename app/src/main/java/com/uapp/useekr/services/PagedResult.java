package com.uapp.useekr.services;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 12/2/17.
 */

public class PagedResult<T> {

    public enum Status {
        @SerializedName("success")
        SUCCESS,
        @SerializedName("failure")
        FAILURE
    }

    @SerializedName("status")
    private final Status status;

    @NonNull
    public final T data;

    public final long totalCount;

    @Nullable
    public final String errorMessage;

    public PagedResult(@NonNull T data, long totalCount) {
        this.status = Status.SUCCESS;
        this.data = data;
        this.totalCount = totalCount;
        this.errorMessage = null;
    }

    @SuppressWarnings("unchecked")
    public PagedResult(@NonNull String errorMessage) {
        this.status = Status.FAILURE;
        this.data = (T) new Object();
        this.totalCount = 0;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return status == Status.SUCCESS;
    }

}