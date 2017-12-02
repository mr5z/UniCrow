package com.uapp.useekr.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by root on 12/2/17.
 */

public class Transaction implements Serializable {

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public enum TransactionStatus {
        @SerializedName("0")
        PENDING,
        @SerializedName("1")
        ON_GOING,
        @SerializedName("2")
        COMPLETED,
        @SerializedName("3")
        CANCELLED
    }

    private long id;

    private String title;

    @SerializedName("status")
    private TransactionStatus transactionStatus;

    public Transaction(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
