package com.uapp.useekr.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by root on 12/2/17.
 */

public class Transaction implements Serializable {

    private static SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyy", Locale.getDefault());

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(long dateUpdated) {
        this.dateUpdated = dateUpdated;
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

    private String description;

    @SerializedName("status")
    private TransactionStatus transactionStatus;

    private double amount;

    @SerializedName("created_at")
    private long dateCreated;

    @SerializedName("updated_at")
    private long dateUpdated;

    @SerializedName("acct_no")
    private String accountNumber;

    @SerializedName("rec_firstname")
    private String recFirstName;

    @SerializedName("rec_lastname")
    private String recLastName;

    @SerializedName("rec_email")
    private String recEmail;

    @SerializedName("tasks")
    private List<Task> taskList;

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

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getRecFirstName() {
        return recFirstName;
    }

    public void setRecFirstName(String recFirstName) {
        this.recFirstName = recFirstName;
    }

    public String getRecLastName() {
        return recLastName;
    }

    public void setRecLastName(String recLastName) {
        this.recLastName = recLastName;
    }

    public String getRecEmail() {
        return recEmail;
    }

    public void setRecEmail(String recEmail) {
        this.recEmail = recEmail;
    }

    public String getFormattedDateCreated() {
        return sdf.format(new Date(dateCreated));
    }
}
