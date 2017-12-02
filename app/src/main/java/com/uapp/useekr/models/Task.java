package com.uapp.useekr.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 12/2/17.
 */

public class Task {

    private long id;

    @SerializedName("details")
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
