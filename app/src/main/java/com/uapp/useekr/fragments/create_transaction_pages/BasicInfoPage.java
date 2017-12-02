package com.uapp.useekr.fragments.create_transaction_pages;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.uapp.useekr.R;
import com.uapp.useekr.fragments.BaseFragment;
import com.uapp.useekr.models.Transaction;

/**
 * Created by root on 12/2/17.
 */

public class BasicInfoPage extends BasePage {

    @Override
    protected int contentLayout() {
        return R.layout.fragment_page_basic_info;
    }

    @Override
    protected int contentTitle() {
        return R.string.basic_info;
    }

    @Override
    public void onPause() {
        super.onPause();

        View view = getView();
        TextInputEditText editFirstName =
                view.findViewById(R.id.edit_transaction_create_firstname);
        TextInputEditText editLastName =
                view.findViewById(R.id.edit_transaction_create_lastname);
        TextInputEditText editAccountNumber =
                view.findViewById(R.id.edit_transaction_create_account_number);

        String firstName = editFirstName.getText().toString();
        String lastName = editLastName.getText().toString();
        String accountNumber = editAccountNumber.getText().toString();

        Intent intent = getActivity().getIntent();
        Transaction transaction = (Transaction) intent.getSerializableExtra("transaction");
        transaction.setRecFirstName(firstName);
        transaction.setRecLastName(lastName);
        transaction.setAccountNumber(accountNumber);
        intent.putExtra("transaction", transaction);
    }

    @Override
    void updateTransaction(View view, Transaction transaction) {
        TextInputEditText editFirstName =
                view.findViewById(R.id.edit_transaction_create_firstname);
        TextInputEditText editLastName =
                view.findViewById(R.id.edit_transaction_create_lastname);
        TextInputEditText editAccountNumber =
                view.findViewById(R.id.edit_transaction_create_account_number);

        String firstName = editFirstName.getText().toString();
        String lastName = editLastName.getText().toString();
        String accountNumber = editAccountNumber.getText().toString();

        transaction.setRecFirstName(firstName);
        transaction.setRecLastName(lastName);
        transaction.setAccountNumber(accountNumber);
    }


}
