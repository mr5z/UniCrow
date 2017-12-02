package com.uapp.useekr.fragments.create_transaction_pages;

import com.uapp.useekr.R;
import com.uapp.useekr.fragments.BaseFragment;

/**
 * Created by root on 12/2/17.
 */

public class TransactionDetailsPage extends BaseFragment {
    @Override
    protected int contentLayout() {
        return R.layout.fragment_page_transaction_details;
    }

    @Override
    protected int contentTitle() {
        return R.string.transaction_details;
    }
}
