package com.uapp.useekr.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uapp.useekr.R;
import com.uapp.useekr.activities.TransactionDetailActivity;
import com.uapp.useekr.adapters.TransactionAdapter;
import com.uapp.useekr.models.Transaction;
import com.uapp.useekr.services.PagedResult;
import com.uapp.useekr.services.TransactionService;
import com.uapp.useekr.utils.Debug;
import com.uapp.useekr.utils.TaskWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by root on 12/2/17.
 */

public class TransactionsFragment extends BaseFragment
        implements TaskWrapper.Task<PagedResult<List<Transaction>>>,TransactionAdapter.ItemClickListener {

    @BindView(R.id.transaction_list)
    RecyclerView recyclerView;

    private TaskWrapper<PagedResult<List<Transaction>>> transactionTask() {
        return new TaskWrapper<>(this);
    }

    @Override
    protected int contentLayout() {
        return R.layout.fragment_useekr_transact;
    }

    @Override
    protected int contentTitle() {
        return R.string.transactions;
    }

    @Override
    void initialize() {
        transactionTask().execute();
    }

    void configureRecyclerView(List<Transaction> dataSet) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TransactionAdapter adapter = new TransactionAdapter(dataSet);
        adapter.setItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onTaskBegin() {
        showLoader(true);
    }

    @Override
    public PagedResult<List<Transaction>> onTaskExecute() {
        long userId = settings().getUserId();
        return TransactionService.instance().getTransactions(userId);
    }

    @Override
    public void onTaskEnd(PagedResult<List<Transaction>> result) {
        showLoader(false);
        if (result.isSuccess()) {
            configureRecyclerView(result.data);
        }
        else {
            Debug.log("error: %s", result.errorMessage);
        }
    }

    @Override
    public void onItemClick(int position) {
        TransactionAdapter adapter = (TransactionAdapter) recyclerView.getAdapter();
        List<Transaction> dataSet = adapter.getDataSet();
        Transaction transaction = dataSet.get(position);
        Intent intent = new Intent(getContext(), TransactionDetailActivity.class);
        intent.putExtra("transaction", transaction);
        startActivity(intent);
    }
}
