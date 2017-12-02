package com.uapp.useekr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.uapp.useekr.R;
import com.uapp.useekr.models.Transaction;

import butterknife.BindView;

/**
 * Created by root on 12/2/17.
 */

public class TransactionDetailActivity extends BaseActivity {

    @BindView(R.id.transaction_detail_title)
    TextView txtTransactionTitle;

    @BindView(R.id.transaction_detail_list)
    RecyclerView detailList;

    @Override
    int contentLayout() {
        return R.layout.activity_transaction_detail;
    }

    @Override
    void initialize(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        Transaction transaction = (Transaction)
                intent.getSerializableExtra("transaction");
        txtTransactionTitle.setText(transaction.getTitle());
    }
}
