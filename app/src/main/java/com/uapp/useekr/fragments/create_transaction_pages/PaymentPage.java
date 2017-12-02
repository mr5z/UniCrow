package com.uapp.useekr.fragments.create_transaction_pages;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.uapp.useekr.R;
import com.uapp.useekr.fragments.BaseFragment;
import com.uapp.useekr.models.Transaction;

import butterknife.BindView;

/**
 * Created by root on 12/2/17.
 */

public class PaymentPage extends BasePage {

    @BindView(R.id.edit_transaction_create_payment_amount)
    TextInputEditText editAmount;

    @Override
    protected int contentLayout() {
        return R.layout.fragment_page_payment;
    }

    @Override
    protected int contentTitle() {
        return R.string.payment_details;
    }

    @Override
    protected void initialize() {
        editAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Intent intent = getActivity().getIntent();
                Transaction transaction = (Transaction) intent.getSerializableExtra("transaction");
                String amount = charSequence.toString();
                transaction.setAmount(Double.parseDouble(amount));
                intent.putExtra("transaction", transaction);
                getActivity().setIntent(intent);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    void updateTransaction(View view, Transaction transaction) {
        TextInputEditText editAmount = view.findViewById(R.id.edit_transaction_create_payment_amount);
        String amount = editAmount.getText().toString();
        if (amount.isEmpty()) {
            transaction.setAmount(Double.parseDouble(amount));
        }
    }
}
