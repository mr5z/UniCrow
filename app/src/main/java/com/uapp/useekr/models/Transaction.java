package com.uapp.useekr.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 12/2/17.
 */

public class Transaction implements Serializable {

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

    private double amount;

    private String accountNumber;

    private String recFirstName;

    private String recLastName;

    private String recEmail;

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
}
