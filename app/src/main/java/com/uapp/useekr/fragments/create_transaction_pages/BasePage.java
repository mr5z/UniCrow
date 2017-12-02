package com.uapp.useekr.fragments.create_transaction_pages;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.uapp.useekr.R;
import com.uapp.useekr.fragments.BaseFragment;
import com.uapp.useekr.models.Transaction;

import butterknife.BindView;

/**
 * Created by root on 12/3/17.
 */

public abstract class BasePage extends BaseFragment {

    @Override
    public void onPause() {
        super.onPause();
        Intent intent = getActivity().getIntent();
        Transaction transaction = (Transaction) intent.getSerializableExtra("transaction");
        updateTransaction(getView(), transaction);
        intent.putExtra("transaction", transaction);
    }

    abstract void updateTransaction(View view, Transaction transaction);
}
