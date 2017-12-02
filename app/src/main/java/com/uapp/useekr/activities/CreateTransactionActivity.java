package com.uapp.useekr.activities;

import android.view.Menu;
import android.view.MenuItem;

import com.uapp.useekr.R;
import com.uapp.useekr.fragments.BaseFragment;
import com.uapp.useekr.fragments.create_transaction_pages.BasicInfoPage;
import com.uapp.useekr.fragments.create_transaction_pages.TransactionDetailsPage;

/**
 * Created by root on 12/2/17.
 */

public class CreateTransactionActivity extends BaseActivity {

    @Override
    int contentLayout() {
        return R.layout.activity_create_transaction;
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
                break;
            case R.id.menu_create_transaction_next:
                showTransactionDetails();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    void changeContent(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.create_transaction_content, fragment)
                .commit();
    }

    void showBasicInfo() {
        changeContent(new BasicInfoPage());
    }

    void showTransactionDetails() {
        changeContent(new TransactionDetailsPage());
    }

}
