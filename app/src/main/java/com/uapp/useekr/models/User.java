package com.uapp.useekr.models;

import java.io.Serializable;

/**
 * Created by root on 12/2/17.
 */

public class User implements Serializable {

    private long id;

    private String displayName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
