package com.uapp.useekr.services;

import com.uapp.useekr.models.Transaction;
import com.uapp.useekr.utils.HttpUtil.KeyValue;

import java.util.List;

/**
 * Created by root on 12/2/17.
 */

public class TransactionService extends BaseService<Transaction> {

    private static TransactionService _instance;

    public static TransactionService instance() {
        if (_instance == null) {
            _instance = new TransactionService();
        }
        return _instance;
    }

    private TransactionService() {
        super("transaction");
    }

    public PagedResult<List<Transaction>> getTransactions(long userId) {
        return getList(action("list"),
                identity(userId));
    }

    public PagedResult<Transaction> createTransaction(long userId, Transaction transaction) {
        return postObject(action("createwhole"),
                identity(userId));
    }
}
