package com.uapp.useekr.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.repsly.library.timelineview.TimelineView;
import com.uapp.useekr.R;
import com.uapp.useekr.fragments.BaseFragment;
import com.uapp.useekr.fragments.create_transaction_pages.BasicInfoPage;
import com.uapp.useekr.fragments.create_transaction_pages.PaymentPage;
import com.uapp.useekr.fragments.create_transaction_pages.TransactionDetailsPage;
import com.uapp.useekr.models.Transaction;
import com.uapp.useekr.services.PagedResult;
import com.uapp.useekr.services.TransactionService;
import com.uapp.useekr.utils.TaskWrapper;

import butterknife.BindView;

/**
 * Created by root on 12/2/17.
 */

public class CreateTransactionActivity extends BaseActivity implements BaseActivity.ConfirmDialogListener, TaskWrapper.Task<PagedResult<Transaction>> {

    private static final int MAX_PAGE = 3;

    private TaskWrapper<PagedResult<Transaction>> transactionTask() {
        return new TaskWrapper<>(this);
    }

    @BindView(R.id.timeline)
    TimelineView timelineView;

    private int currentPage;

    @Override
    int contentLayout() {
        return R.layout.activity_create_transaction;
    }

    @Override
    void initialize(@Nullable Bundle savedInstanceState) {
        showTransactionDetails();
        updateTimelineNumber(currentPage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_create_transactions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                item.setTitle(R.string.next);
                popContent();
                break;
            case R.id.menu_create_transaction_next:
                switch (currentPage) {
                    case 1:
                        showBasicInfo();
                        break;
                    case 2:
                        item.setTitle(R.string.done);
                        showPayment();
                        break;
                    case 3:
                        showMainActivity();
                        break;
                }
                break;
        }

        updateTimelineNumber(currentPage);

        return super.onOptionsItemSelected(item);
    }

    void pushContent(BaseFragment fragment) {
        currentPage = Math.min(currentPage + 1, MAX_PAGE);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.create_transaction_content, fragment)
                .addToBackStack(null)
                .commit();
    }

    void popContent() {
        currentPage = Math.max(currentPage - 1, 0);
        getSupportFragmentManager().popBackStack();
    }

    void updateTimelineNumber(int number) {
        timelineView.setNumber(number);
    }

    void showTransactionDetails() {
        pushContent(new TransactionDetailsPage());
    }

    void showBasicInfo() {
        pushContent(new BasicInfoPage());
    }

    void showPayment() {
        pushContent(new PaymentPage());
    }

    void showMainActivity() {
        showConfirmDialog(this, getString(R.string.confirm_create_transaction));
    }

    @Override
    public void onConfirm() {
        transactionTask().execute();
    }

    @Override
    public void onTaskBegin() {
        showLoader(true);
    }

    @Override
    public PagedResult<Transaction> onTaskExecute() {
        Transaction transaction = new Transaction();
        return TransactionService.instance().createTransaction(transaction);
    }

    @Override
    public void onTaskEnd(PagedResult<Transaction> result) {
        showLoader(false);
        if (result.isSuccess()) {
            finish();
        }
        else {
            showSnackbar(result.errorMessage);
        }
    }
}
