package com.uapp.useekr.converter;

import com.uapp.useekr.models.Transaction;

/**
 * Created by root on 12/2/17.
 */

public class TransactionStatusConverter
        implements BaseConverter<Transaction.TransactionStatus, Integer> {

    @Override
    public Transaction.TransactionStatus fromValue(Integer value) {
        return Transaction.TransactionStatus.values()[value];
    }

    @Override
    public Integer toValue(Transaction.TransactionStatus value) {
        return value.ordinal();
    }
}
